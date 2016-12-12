package BeeHiveStuff;
import BeeHiveStuff.Node;
import tinshoes.input.SafeScanner;
//import java.util.Stack;
import java.util.concurrent.TimeUnit;
import java.util.ArrayDeque;
import java.util.Scanner;
import java.util.Random;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.io.File;
/* Yes, I know there are a lot of spelling mistakes.
 * Don't blame me.
 * -David
 */
class Driver{
	public Scanner sc;// = new Scanner(System.in);
	public int dimention;
	
	public Node[][][] cube;
	public int volume;
	public Node[] hives = new Node[15];
	public Node[] bees = new Node[15];
	public Driver(int dimention) {
		this.dimention = dimention;
		cube = new Node[dimention][dimention][dimention];
		volume = (int)Math.pow(dimention, 3);
		sc = new Scanner(System.in);
	}
	public static void main(String[] args){
		Scanner usc = new Scanner(System.in);
		SafeScanner sc = new SafeScanner(usc);
		if (sc.yn("Would you like to input from a file?", "That is not valid input.")) {
			System.out.println("What is the name of the file?");
			String fileName = usc.next();
			File file = new File(fileName);
			if (!file.exists() || file.isDirectory() || !file.canRead()) {
				System.out.println("That file doesn't exist/is a directory/is unreadable.  Exiting...");
				return;
			}
			Scanner fileReader;
			try {
				fileReader = new Scanner(file);
			} catch (Exception e) {
				System.out.println("Exception in reading file: " + e.getMessage());
				return;
			}
			System.out.println("File Header: " + fileReader.nextLine());
			int dimention = Integer.parseInt(fileReader.nextLine().split(",")[0]);
			Driver d = new Driver(dimention);
			d.initCubeFromInput(fileReader);
			System.out.println("Starting...");
			//int total = 0;
			Node[] beePriority = new Node[15];
			int[] beePriorityVals = new int[15];
			List<HashMap<Node, Integer>> beeDists = new ArrayList<HashMap<Node, Integer>>();
			System.arraycopy(d.bees, 0, beePriority, 0, 15);
			Node[] beeHivePair = new Node[15];
			for (int i = 0; i < 15; i++) {
				HashMap<Node, Integer> hm = d.floodFill(d.bees[i]);
				beeDists.add(hm);
				Integer furthestHiveLen = null, closestHiveLen = null;
				for (Node h : d.hives) {
					Integer curr = hm.get(h);
					if (furthestHiveLen == null) {
						furthestHiveLen = curr;
					} else if (furthestHiveLen < curr) {
						furthestHiveLen = curr;
					}
					if (closestHiveLen == null) {
						closestHiveLen = hm.get(h);
					} else if (closestHiveLen > curr) {
						closestHiveLen = curr;
					}
				}
				beePriorityVals[i] = furthestHiveLen == null || closestHiveLen == null ? 0 : furthestHiveLen - closestHiveLen;
			}
			boolean flag = true;
			while (flag) {
				flag = false;
				for (int j = 0; j < 14; j++) {
					if (beePriorityVals[j] < beePriorityVals[j + 1]) {
						int temp = beePriorityVals[j];
						beePriorityVals[j] = beePriorityVals[j + 1];
						beePriorityVals[j + 1] = temp;
						Node temp2 = beePriority[j];
						beePriority[j] = beePriority[j + 1];
						beePriority[j + 1] = temp2;
						HashMap<Node, Integer> temp3 = beeDists.get(j);
						beeDists.set(j, beeDists.get(j+1));
						beeDists.set(j+1, temp3);
						flag = true;
					}
				}
			}
			for (int j = 0; j < 15; j++) {
				HashMap<Node, Integer> h = beeDists.get(j);
				Integer closestHive = null, closestDistance = null;
				for (int i = 0; i < 15; i++) {
					Integer currDist = h.get(d.hives[i]);
					if (currDist != null && !d.hives[i].isBee) {
						if (closestHive == null || closestDistance == null) {
							closestHive = i;
							closestDistance = currDist;
						} else if (currDist < closestDistance) { //Only in a different if statement because null < int throws an error
							closestHive = i;
							closestDistance = currDist;
						}
					}
				}
				//total += closestDistance == null ? 0 : closestDistance;
				d.hives[closestHive].numberOfMoves = closestDistance;
				beeHivePair[beePriority[j].beeNumber] = closestHive == null ? null : d.hives[closestHive];
				//System.out.println("Bee #" + (beePriority[j].beeNumber + 1) + (closestHive == null ? " is unreachable." : " reached Hive #" + (closestHive + 1) + " in " + closestDistance + " moves."));
				d.cube[d.bees[j].X][d.bees[j].X][d.bees[j].X].isBee = false;
				//d.bees[j] = d.hives[closestHive];
				//System.out.println(d.cube[d.hives[closestHive].X][d.hives[closestHive].Y][d.hives[closestHive].Z]);
				d.cube[d.hives[closestHive].X][d.hives[closestHive].Y][d.hives[closestHive].Z].isSolid = true;
				d.cube[d.hives[closestHive].X][d.hives[closestHive].Y][d.hives[closestHive].Z].isBee = true;
			}
			int total = 0;
			for (int i = 0; i < 15; i++) {
				System.out.println("Bee #" + (i + 1) + (beeHivePair[i] == null ? " is unreachable." : " reached Hive #" + (beeHivePair[i].hiveNumber + 1) + " in " + beeHivePair[i].numberOfMoves + " moves."));
				total += beeHivePair[i].numberOfMoves;
			}
			System.out.println("Done!");
			System.out.println("Total moves: " + total);

		} else {
			System.out.println("Please enter a number from 25 - 35");
			int dimention = usc.nextInt();
			Driver d = new Driver(dimention);
			
			d.initCube();	
			System.out.println("Starting...");
			//HashMap<Node, Integer> test = new HashMap<Node, Integer>();
			//test = floodFill(cube[0][0][0], test, -1);
			//HashMap<Node, Integer> test = d.floodFill(d.cube[0][0][0]);
			//System.out.println("Done!");
			//System.out.println(test.get(d.cube[0][0][3]));
			//ArrayList<Node> beeList = new ArrayList<Node>(Arrays.asList(d.bees));
			int total = 0;
			for (int j = 0; j < 15; j++) {
				HashMap<Node, Integer> h = d.floodFill(d.bees[j]);
				Integer closestHive = null, closestDistance = null;
				for (int i = 0; i < 15; i++) {
					Integer currDist = h.get(d.hives[i]);
					if (currDist != null) {
						if (closestHive == null || closestDistance == null) {
							closestHive = i;
							closestDistance = currDist;
						} else if (currDist < closestDistance) { //Only in a different if statement because null < int throws an error
							closestHive = i;
							closestDistance = currDist;
						}
					}
				}
				total += closestDistance == null ? 0 : closestDistance;
				System.out.println("Bee #" + (j+1) + (closestHive == null ? " is unreachable." : " reached Hive #" + (closestHive + 1) + " in " + closestDistance + " moves."));
				d.cube[d.bees[j].X][d.bees[j].X][d.bees[j].X].isBee = false;
				//d.bees[j] = d.hives[closestHive];
				d.cube[d.hives[closestHive].X][d.hives[closestHive].Y][d.hives[closestHive].Z].isSolid = true;
				d.cube[d.hives[closestHive].X][d.hives[closestHive].Y][d.hives[closestHive].Z].isBee = true;
			}
			System.out.println("Done!");
			System.out.println("Total moves: " + total);
		}
		//Node f = test.get(d.cube[0][0][2]);
		//System.out.println(d.cube[0][0][0].getNextTo().length);
		/*for (Node j : d.cube[0][0][0].getNextTo()) {
			if (j != null) {
				System.out.println("(" + j.X + ",\t" + j.Y + ",\t" + j.Z + ")");
			}
		}
		do {
			System.out.println("(" + f.X + ",\t" + f.Y + ",\t" + f.Z + ") ->");
			f = test.pointed.get(f);
		} while (f != d.cube[0][0][0]);*/
		//System.out.println(d.cube[0][0][3].isSolid);
		//System.out.println(test.get(cube[0][0][1]));
//		System.out.println("Please type \"yes\"" to continue with calculation");
	}
		
	//MARK: - Path Finding

	public HashMap<Node, Integer> floodFill(Node n) {
		/*if (n.isSolid)
			return h;
		int d = c;
		d++;
		if (h.containsKey(n)) {
			if (h.get(n) < d)
				return h;
		}
		HashMap<Node, Integer> temp = h;
		n.calculateNextTo();
		temp.put(n, d);
		for (Node j : n.getNextTo()) {
			if (j != null)
				temp = floodFill(j, temp, d);
		}
		return temp;
		*/

		HashMap<Node, Integer> paths = new HashMap<Node, Integer>();
		//HashMap<Node, Node> pointed = new HashMap<Node, Node>();
		//HashMap<Node, Node> potentialPointed = new HashMap<Node, Node>();
		ArrayDeque<Node[]> toDo = new ArrayDeque<Node[]>();
		paths.put(n, 0);
		n.calculateNextTo();
		for (Node j : n.getNextTo()) {
			if (j != null) {
				toDo.add(new Node[] {j, n});
				//potentialPointed.put(j, n);
			}
		}
		while (!toDo.isEmpty()) {
			Node[] curr = toDo.poll();
			if (curr[0].isSolid)
				continue;
			int currLen = paths.get(curr[1]) + 1; //The length of the previous path plus 1
			if (paths.containsKey(curr[0])) {
				if (paths.get(curr[0]) <= currLen) {
					continue;

				}
			}
			//pointed.put(curr, potentialPointed.get(curr));
			curr[0].calculateNextTo();
			paths.put(curr[0], currLen);
			//System.out.println(curr.X + "\t" + curr.Y + "\t" + curr.Z + "\t" + pointed.get(curr).X + "\t" + pointed.get(curr).Y + "\t" + pointed.get(curr).Z + "\t" + currLen);

			/*try {
				TimeUnit.SECONDS.sleep(1);
			} catch (Exception e) {
				System.err.println("Something went wrong while sleeping.");
			}*/
			for (Node j : curr[0].getNextTo()) {
				if (j != null) {
					toDo.add(new Node[] {j, curr[0]});
					//potentialPointed.put(j, curr);
				}
			}
		}
		return paths;
	}
	//REDO AND LOGIC TEST ^^
	
	
	
	
	
	
	
	
	
	public void fillCube() {
		int count = 0;
		for (int i = 0; i < dimention; i ++){
			for (int j = 0; j < dimention; j ++){
				for (int k = 0; k < dimention; k ++){
					cube[i][j][k] = new Node(i, j, k, cube); //Note: Should pass reference to cube, so changes in cube will affect Nodes
					count ++;
				}
			}
		}
	}
	public void initCubeFromInput(Scanner in) {
		fillCube();
		//Hives
		for (int i = 0; i < 15; i++) {
			String[] curr = in.nextLine().split(",");
			int[] coords = new int[3];
			for (int x = 0; x < 3; x++) {
				coords[x] = Integer.parseInt(curr[x]);
			}
			cube[coords[0]][coords[1]][coords[2]].makeHive(i);
			hives[i] = cube[coords[0]][coords[1]][coords[2]];
		}
		//Bees
		for (int i = 0; i < 15; i++) {
			String[] curr = in.nextLine().split(",");
			int[] coords = new int[3];
			for (int x = 0; x < 3; x++) {
				coords[x] = Integer.parseInt(curr[x]);
			}
			cube[coords[0]][coords[1]][coords[2]].makeBee(i);
			bees[i] = cube[coords[0]][coords[1]][coords[2]];
		}
		int numOfSolids = Integer.parseInt(in.nextLine());
		System.out.println("There are " + numOfSolids + " solid blocks.");
		for (int i = 0; i < numOfSolids; i++) {
			String[] curr = in.nextLine().split(",");
			int[] coords = new int[3];
			for (int x = 0; x < 3; x++) {
				coords[x] = Integer.parseInt(curr[x]);
			}
			cube[coords[0]][coords[1]][coords[2]].makeSolid();
		}
	}
	//MARK: - Init Cube
	
	//creates the blocks in a cube as solid, hive and bee
	public void initCube(){
		//Makes each a new Node
		fillCube();
		
		// sets the hive at 2,2,2 - 2,2,17
//		for (int i = 0; i < 15; i++){
//			cube[2][2][i + 2].makeHive(i);
//		}
		
		//randomly makes a hive anywhere
		while(true){
			Random rn = new Random();
			int x = rn.nextInt(dimention + 1);
			int y = rn.nextInt(dimention + 1);
			int z = rn.nextInt(dimention + 1);
			int a = rn.nextInt(3);
			//System.out.println(a + " X: " + x + " Y: " + y + " Z:" + z);
			boolean does = false;
			switch(a){
			case 0: does = existsinCube(x + 14, y, z); break;
			case 1: does = existsinCube(x, y + 14, z); break;
			case 2: does = existsinCube(x, y, z + 14); break;
			default: break;
			}
			if(existsinCube(x, y, z) && does){
				for (int i = 0; i < 15; i++){
					switch(a){
					case 0: 
						cube[x + i][y][z].makeHive(i); 
						hives[i] = cube[x + i][y][z]; 
						break; 
					case 1: 
						cube[x][y + i][z].makeHive(i); 
						hives[i] = cube[x][y + i][z];
						break;
					case 2: 
						cube[x][y][z + i].makeHive(i);
						hives[i] = cube[x][y][z + i];
						break;
					}
				}
				break;
			}
		}


		//makes 15 of the blocks bees
		for (int i = 0; i < 15; i++){
			while(true){
				Random rn = new Random();
				int x = rn.nextInt(dimention);
				int y = rn.nextInt(dimention);
				int z = rn.nextInt(dimention);
				if (cube[x][y][z].makeBee(i)){
					bees[i] = cube[x][y][z]; 
					break;
				}
			}
		}

		//sets 0.3 of the blocks into solid
		int numOfSolids = (int) (0.3 * volume);
		System.out.println("There are " + numOfSolids + " solid trash blocks");
		for (int i = 0; i <= numOfSolids; i++){
			while(true){
				Random rn = new Random();
				int x = rn.nextInt(dimention);
				int y = rn.nextInt(dimention);
				int z = rn.nextInt(dimention);
				if (cube[x][y][z].makeSolid()){
					break;
				}
			}
		}


	}
	
	public boolean existsinCube(int a, int b, int c){
	    try{
	      Node d = cube[a][b][c];
	      return true;
	    } catch(ArrayIndexOutOfBoundsException e){
	      return false;
	    }
	}
	
		
}








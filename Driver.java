package BeeHiveStuff;
import BeeHiveStuff.Node;
import java.util.Scanner;
import java.util.Random;
import java.util.HashMap;
/* Yes, I know there are a lot of spelling mistakes.
 * Don't blame me.
 * -David
 */
class Driver{
	
	public static Scanner sc = new Scanner(System.in);
	static int dimention;
	
	public static Node[][][] cube;
	public static int volume;
	public static Node[] hives = new Node[15];
	public static Node[] bees = new Node[15];
	
	public static void main(String[] args){
		
		System.out.println("Please enter a number from 25 - 35");
		dimention = sc.nextInt();
		cube = new Node[dimention][dimention][dimention];
		volume = (int)Math.pow(dimention, 3);
		
		initCube();	
		System.out.println("Starting...");
		HashMap<Node, Integer> test = new HashMap<Node, Integer>();
		test = floodFill(cube[0][0][0], test, -1);
		System.out.println("Done!");
		System.out.println(test.get(cube[0][0][1]));
//		System.out.println("Please type \"yes\"" to continue with calculation");
	}
		
	//MARK: - Path Finding

	public static HashMap<Node, Integer> floodFill(Node n, HashMap<Node, Integer> h, int c) {
		if (n.isSolid)
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
		/*if (h.containsKey(n)) {
			if (h.get(n) < c) {
				HashMap<Node, int> temp = h;
				n.calculateNextTo();
				for (Node j : n.getNextTo()) {
					temp = floodFill(n, temp, c);
				}
			}
			return temp;
		}
		h.put(n, c);
		HashMap<Node, int> temp = h;
		n.calculateNextTo();
		for (Node j : n.getNextTo()) {
			temp = floodFill(n, temp, c);
		}
		return temp;*/
	}
	//REDO AND LOGIC TEST ^^
	
	
	
	
	
	
	
	
	
	
	
	//MARK: - Init Cube
	
	//creates the blocks in a cube as solid, hive and bee
	static void initCube(){
		//Makes each a new Node
		int count = 0;
		for (int i = 0; i < dimention; i ++){
			for (int j = 0; j < dimention; j ++){
				for (int k = 0; k < dimention; k ++){
					cube[i][j][k] = new Node(i, j, k);
					count ++;
				}
			}
		}
		
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
	
	public static boolean existsinCube(int a, int b, int c){
	    try{
	      Node d = cube[a][b][c];
	      return true;
	    } catch(ArrayIndexOutOfBoundsException e){
	      return false;
	    }
	}
	
		
}








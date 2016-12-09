package BeeHiveStuff;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

class Driver{
	
	public static Scanner sc = new Scanner(System.in);
	static int dimention;
	
	public static Node[][][] cube;
	public static int volume;
	public static int[][] hive = new int[15][3];
	public static int[][] bees = new int[15][3];
	
	//static int totalMoves = 0;
	static int[] movesPerBee1 = new int[15];
	static int[] movesPerBee2 = new int[15];
	
	public static void main(String[] args){
		System.out.println("Do you want to enter in maually?");
		String answer = sc.next();
		
		if (answer.toLowerCase().equals("yes")){
			manuallyInitCube();
		}else{
			initCube();
		}
		
		calculateNext();
		
		//calculates answers and prints them
		int totalMovesPath1 = findPath1();
		int totalMovesPath2 = findPath2();
		boolean num1IsLower = (totalMovesPath1 < totalMovesPath2) ? true : false;
		if (num1IsLower){
			printAnswers(totalMovesPath1, movesPerBee1, totalMovesPath2);
		}else{
			printAnswers(totalMovesPath2, movesPerBee2, totalMovesPath1);
		}
	}
	
	public static void printAnswers(int totalMoves, int[] movesPerBee, int alternatePath){
		System.out.print("\n");
		for (int i = 0; i < 15; i++){
			System.out.println("Bee #" + (i + 1) + " moved " + movesPerBee[i]);
		}
		System.out.println("The total amount of moves were " + totalMoves);
		System.out.println("\nAlternate Paths : " + alternatePath);

	}
		
	
	
	//MARK: - Path Finding

	static void allToNull(){
		for (int i = 0; i < dimention; i ++){
			for (int j = 0; j < dimention; j ++){
				for (int k = 0; k < dimention; k ++){
					for (int l = 0; l < 15; l++){
						cube[i][j][k].toNull();
					}
				}
			}
		}
	}
	
	static int findPath1(){
		allToNull();
		
		int totalMoves = 0;
		//loop per hive Number
		for (int i = 0; i < 15; i ++){
			
			List<ArrayList<int[]>> openList = new ArrayList<ArrayList<int[]>>();
			boolean noBeeFound = true;
			
			//adds hive point to openList
			int[] startPoint = {hive[i][0], hive[i][1], hive[i][2]};
			ArrayList<int[]> thisSomething = new ArrayList<int[]>();
			thisSomething.add(startPoint);
			openList.add(thisSomething);

			int count = 1;

			outerloop:
				//Loop through the layers of count
				while (noBeeFound){

					ArrayList<int[]> thisGroup = openList.get(count - 1);
					
					//loopes through nodes in the layer
					for (int j = 0; j < thisGroup.size(); j++){
						
						int x = thisGroup.get(j)[0];
						int y = thisGroup.get(j)[1];
						int z = thisGroup.get(j)[2];

						Node thisNode = cube[x][y][z];

						//checks if is bee
						if (thisNode.isBee && !(thisNode.finished)){
							totalMoves += count;
							movesPerBee1[thisNode.beeNumber] = count;
							cube[x][y][z].finished = true;
							cube[x][y][z].hiveNumber = i;
							openList.clear();
							break outerloop;
						}

						//adds all nodes next to this node that have not been added already
						for (int f = 0; f < 26; f ++){
							if(thisNode.next[f] != null){
								int[] thisNext = {thisNode.next[f].X, thisNode.next[f].Y, thisNode.next[f].Z};
								if (cube[thisNext[0]][thisNext[1]][thisNext[2]].distance[i] == null){
									try{
										openList.get(count).add(thisNext);
										cube[thisNext[0]][thisNext[1]][thisNext[2]].distance[i] = count; 
									}catch(Exception e){
										cube[thisNext[0]][thisNext[1]][thisNext[2]].distance[i] = count;
										ArrayList<int[]> thisArray = new ArrayList<int[]>();
										thisArray.add(thisNext);
										openList.add(thisArray);
									}
								}
							}
						}
					}
					count++;
				}
		}
		
		return totalMoves;
	}
	
	static int findPath2(){
		allToNull();
		
		int totalMoves = 0;
		for (int i = 14; i >= 0; i--){
			
			List<ArrayList<int[]>> openList = new ArrayList<ArrayList<int[]>>();

			boolean noBeeFound = true;

			int[] startPoint = {hive[i][0], hive[i][1], hive[i][2]};
			ArrayList<int[]> thisSomething = new ArrayList<int[]>();
			thisSomething.add(startPoint);
			openList.add(thisSomething);

			int count = 1;

			outerloop:
				while (noBeeFound){
					ArrayList<int[]> thisGroup = openList.get(count - 1);

					for (int j = 0; j < thisGroup.size(); j++){

						int x = thisGroup.get(j)[0];
						int y = thisGroup.get(j)[1];
						int z = thisGroup.get(j)[2];

						Node thisNode = cube[x][y][z];

						if (thisNode.isBee && !(thisNode.finished)){
							totalMoves += count;
							movesPerBee2[thisNode.beeNumber] = count;
							cube[x][y][z].finished = true;
							cube[x][y][z].hiveNumber = i;
							openList.clear();
							break outerloop;
						}

						for (int f = 0; f < 26; f ++){
							if(thisNode.next[f] != null){
								int[] thisNext = {thisNode.next[f].X, thisNode.next[f].Y, thisNode.next[f].Z};
								//NEED A WAY TO CHECK THE FOLLOWING
								if (cube[thisNext[0]][thisNext[1]][thisNext[2]].distance[i] == null){
									try{
										openList.get(count).add(thisNext);
										cube[thisNext[0]][thisNext[1]][thisNext[2]].distance[i] = count; 
									}catch(Exception e){
										cube[thisNext[0]][thisNext[1]][thisNext[2]].distance[i] = count;
										ArrayList<int[]> thisArray = new ArrayList<int[]>();
										thisArray.add(thisNext);
										openList.add(thisArray);
									}
								}
							}
						}
					}
					count++;
				}
		}
		return totalMoves;
	}

	
	
	//MARK: - Init Cube
	
	
	public static void makeNodeArr(){
		//Makes each a new Node
		for (int i = 0; i < dimention; i ++){
			for (int j = 0; j < dimention; j ++){
				for (int k = 0; k < dimention; k ++){
					cube[i][j][k] = new Node(i, j, k);
				}
			}
		}
	}
	
	public static void manuallyInitCube(){
		try {
			
			System.out.println("Which file do you choose (1, 2, or 3)?");
			
			String fileString;
			
			switch(sc.next()){
			case "1": fileString = "beesetup1.txt"; break;
			case "2": fileString = "beesetup2.txt"; break;
			case "3": fileString = "beesetup3.txt"; break;
			default : 
				fileString = "beesetup1.txt"; 
				System.out.println("\nNo recognizable answer. Defaults to 1.\n"); 
				break;
			}
			
			File thisFile = new File(fileString);
			Scanner fileSc = new Scanner(thisFile);
			dimention = Integer.parseInt(fileSc.next().split(",")[0]);
			cube = new Node[dimention][dimention][dimention];
			volume = dimention * dimention * dimention;
			
			makeNodeArr();
			
			//Hives
			for (int i = 0; i < 15; i++){
				String[] hiveCoord = fileSc.next().split(",");
				int x = Integer.parseInt(hiveCoord[0]);
				int y = Integer.parseInt(hiveCoord[1]);
				int z = Integer.parseInt(hiveCoord[2]);
				cube[x][y][z].makeHive(i);
				hive[i] = new int[] {x, y, z};
			}
			
			//Bees
			for (int i = 0; i < 15; i++){
				String[] beeCord = fileSc.next().split(",");
				int x = Integer.parseInt(beeCord[0]);
				int y = Integer.parseInt(beeCord[1]);
				int z = Integer.parseInt(beeCord[2]);
				cube[x][y][z].makeBee(i);
				bees[i] = new int[] {x, y, z};
			}
			
			int numberOfSolids = Integer.parseInt(fileSc.next());
			System.out.println("\nThere are " + numberOfSolids + " solid trash blocks");
			
			//Solids
			for (int i = 0; i < numberOfSolids; i++){
				String[] solidCord = fileSc.next().split(",");
				int x = Integer.parseInt(solidCord[0]);
				int y = Integer.parseInt(solidCord[1]);
				int z = Integer.parseInt(solidCord[2]);
				cube[x][y][z].makeSolid();
			}
			
			
		} catch (FileNotFoundException e) {
			System.out.println("\nFile could not be found Check Code try again");
		}
		
		
	}
	
	
	
	public static void initCube(){
		System.out.println("Please enter a number from 25 - 35");
		dimention = sc.nextInt();
		cube = new Node[dimention][dimention][dimention];
		volume = dimention * dimention * dimention;
		
		makeNodeArr();
		
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
						hive[i] = new int[] {x + 1, y, z};  
						break; 
					case 1: 
						cube[x][y + i][z].makeHive(i); 
						hive[i] = new int[] {x, y + i, z}; 
						break;
					case 2: 
						cube[x][y][z + i].makeHive(i);
						hive[i] = new int[] {x, y, z + 1}; 
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
					bees[i] = new int[] {x, y, z}; 
					break;
				}
			}
		}

		//sets 0.3 of the blocks into solid
		int numOfSolids = (int) (0.3 * volume);
		System.out.println("\nThere are " + numOfSolids + " solid trash blocks");
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
	
	public static void calculateNext(){
		for (int i = 0; i < dimention; i ++){
			for (int j = 0; j < dimention; j ++){
				for (int k = 0; k < dimention; k ++){
					cube[i][j][k].calculateNextTo();
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








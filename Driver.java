package BeeHiveStuff;
import BeeHiveStuff.Node.keys;
import java.util.Scanner;
import java.util.Random;

class Driver{
	
	public static Scanner sc = new Scanner(System.in);
	static int dimention;
	
	public static Node[][][] cube;
	public static int volume;
	
	public static void main(String[] args){
		
		System.out.println("Please enter a number from 25 - 35");
		dimention = sc.nextInt();
		cube = new Node[dimention][dimention][dimention];
		volume = dimention * dimention * dimention;
		
		initCube();
		
		
	}
		
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
			boolean pm = rn.nextBoolean();
			System.out.println(pm + " " + a + " X: " + x + " Y: " + y + " Z:" + z);
			boolean does = false;
			switch(a){
			case 0: does = (pm) ? existsinCube(x + 14, y, z) : existsinCube(x - 14, y, z); break;
			case 1: does = (pm) ? existsinCube(x, y + 14, z) : existsinCube(x, y - 14, z); break;
			case 2: does = (pm) ? existsinCube(x, y, z + 14) : existsinCube(x, y, z - 14); break;
			default: break;
			}
			if(existsinCube(x, y, z) && does){
				for (int i = 0; i < dimention; i++){
					System.out.println(cube[x][y][z].get(keys.Z));
					System.out.println(z);
					switch(a){
					case 0: if(pm){cube[x + i][y][z].makeHive(i); }else{ cube[x - i][y][z].makeHive(i); } break; 
					case 1: if(pm){cube[x][y + i][z].makeHive(i); }else{ cube[x][y - i][z].makeHive(i); } break;
					case 2: if(pm){cube[x][y][z + i].makeHive(i); }else{ cube[x][y][z - i].makeHive(i); } break;
					}
				}
				break;
			}
		}
		
		
		//sets 0.3 of the blocks into solid
		int numOfSolids = (int) (0.3 * volume);
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
		
		//makes 15 of the blocks bees
		for (int i = 0; i < 15; i++){
			while(true){
				Random rn = new Random();
				int x = rn.nextInt(dimention);
				int y = rn.nextInt(dimention);
				int z = rn.nextInt(dimention);
				if (cube[x][y][z].makeBee(i)){
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








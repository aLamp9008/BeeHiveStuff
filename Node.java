package BeeHiveStuff;
import java.util.ArrayList;
//import BeeHiveStuff.Driver;
public class Node{
	
	public int X,Y,Z;
	public boolean isSolid;
	public boolean isHive;
	public boolean isBee;
	private boolean finished;
	private int beeNumber;
	private int numberOfMoves;
	private int hiveNumber;
	//public HashMap distances;
	private int[] distance = new int[15];
	private Node[][][] cube;
	private Node[] next = new Node[26];
	
	public Node(int X,int Y, int Z, Node[][][] cube){
		this.X = X;
		this.Y = Y;
		this.Z = Z;
		this.cube = cube;
		isSolid = false;
		isHive = false;
		isBee = false;
		finished = false;
		numberOfMoves = 0;
	}
	//Blame Alden and my apathy for this monstrosity.
	public void calculateNextTo(){
        /*
        next[0] = cube[X][Y][Z + 1];
        next[1] = cube[X][Y][Z - 1];
        next[2] = cube[X][Y + 1][Z];
        next[3] = cube[X][Y - 1][Z];
        next[4] = cube[X][Y + 1][Z + 1];
        next[5] = cube[X][Y + 1][Z - 1];
        next[6] = cube[X][Y - 1][Z + 1];
        next[7] = cube[X][Y - 1][Z - 1];
        next[8] = cube[X + 1][Y][Z];
        next[9] = cube[X - 1][Y][Z];
        next[10] = cube[X + 1][Y + 1][Z + 1];
        next[11] = cube[X + 1][Y + 1][Z - 1];
        next[12] = cube[X + 1][Y - 1][Z + 1];
        next[13] = cube[X + 1][Y - 1][Z - 1];
        next[14] = cube[X - 1][Y + 1][Z + 1];
        next[15] = cube[X - 1][Y + 1][Z - 1];
        next[16] = cube[X - 1][Y - 1][Z + 1];
        next[17] = cube[X - 1][Y - 1][Z - 1];
        next[18] = cube[X - 1][Y + 1][Z];
        next[19] = cube[X - 1][Y - 1][Z];
        next[20] = cube[X - 1][Y][Z + 1];
        next[21] = cube[X - 1][Y][Z - 1];
        next[22] = cube[X + 1][Y + 1][Z];
        next[23] = cube[X + 1][Y - 1][Z];
        next[24] = cube[X + 1][Y][Z + 1];
        next[25] = cube[X + 1][Y][Z - 1];
        */
        ///*
        /*try{
         next[0] = (cube[X][Y][Z + 1].isSolid) ? null : cube[X][Y][Z + 1];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[1] = (cube[X][Y][Z - 1].isSolid) ? null : cube[X][Y][Z - 1];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[2] = (cube[X][Y + 1][Z].isSolid) ? null : cube[X][Y + 1][Z];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[3] = (cube[X][Y - 1][Z].isSolid) ? null : cube[X][Y - 1][Z];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[4] = (cube[X][Y + 1][Z + 1].isSolid) ? null : cube[X][Y + 1][Z + 1];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[5] = (cube[X][Y + 1][Z - 1].isSolid) ? null : cube[X][Y + 1][Z - 1];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[6] = (cube[X][Y - 1][Z + 1].isSolid) ? null : cube[X][Y - 1][Z + 1];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[7] = (cube[X][Y - 1][Z - 1].isSolid) ? null : cube[X][Y - 1][Z - 1];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[8] = (cube[X + 1][Y][Z].isSolid) ? null : cube[X + 1][Y][Z];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[9] = (cube[X - 1][Y][Z].isSolid) ? null : cube[X - 1][Y][Z];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[10] = (cube[X + 1][Y + 1][Z + 1].isSolid) ? null : cube[X + 1][Y + 1][Z + 1];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[11] = (cube[X + 1][Y + 1][Z - 1].isSolid) ? null : cube[X + 1][Y + 1][Z - 1];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[12] = (cube[X + 1][Y - 1][Z + 1].isSolid) ? null : cube[X + 1][Y - 1][Z + 1];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[13] = (cube[X + 1][Y - 1][Z - 1].isSolid) ? null : cube[X + 1][Y - 1][Z - 1];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[14] = (cube[X - 1][Y + 1][Z + 1].isSolid) ? null : cube[X - 1][Y + 1][Z + 1];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[15] = (cube[X - 1][Y + 1][Z - 1].isSolid) ? null : cube[X - 1][Y + 1][Z - 1];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[16] = (cube[X - 1][Y - 1][Z + 1].isSolid) ? null : cube[X - 1][Y - 1][Z + 1];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[17] = (cube[X - 1][Y - 1][Z - 1].isSolid) ? null : cube[X - 1][Y - 1][Z - 1];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[18] = (cube[X - 1][Y + 1][Z].isSolid) ? null : cube[X - 1][Y + 1][Z];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[19] = (cube[X - 1][Y - 1][Z].isSolid) ? null : cube[X - 1][Y - 1][Z];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[20] = (cube[X - 1][Y][Z + 1].isSolid) ? null : cube[X - 1][Y][Z + 1];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[21] = (cube[X - 1][Y][Z - 1].isSolid) ? null : cube[X - 1][Y][Z - 1];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[22] = (cube[X + 1][Y + 1][Z].isSolid) ? null : cube[X + 1][Y + 1][Z];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[23] = (cube[X + 1][Y - 1][Z].isSolid) ? null : cube[X + 1][Y - 1][Z];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[24] = (cube[X + 1][Y][Z + 1].isSolid) ? null : cube[X + 1][Y][Z + 1];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[25] = (cube[X + 1][Y][Z - 1].isSolid) ? null : cube[X + 1][Y][Z - 1];
        }catch(ArrayIndexOutOfBoundsException e){}
        */
        ArrayList<Node> a = new ArrayList<Node>();
        for (int i = -1; i < 2; i++) {
        	for (int j = -1; j < 2; j++) {
        		for (int k = -1; k < 2; k++) {
        			if (!(i == 0 && j == 0 && k == 0) && !(i + X > cube.length - 1 || i + X < 0 || j + Y > cube.length - 1 || j + Y < 0 || k + Z > cube.length - 1 || k + Z < 0)) {
        				if (!cube[X + i][Y + j][Z + k].isSolid) a.add(cube[X + i][Y + j][Z + k]);
        			}
        		}
        	}
         //*/
        }
        next = a.toArray(new Node[a.size()]);
    }
	
	public Node[] getNextTo(){
		return next;
	}
	
	//public enum keys{
	//	X, Y, Z, isSolid, isHive, isBee, beeNumber, finished, numberOfMoves, hiveNumber
	//}
	//Blame all these awful "make" methods on Alden, jk lol
	public void makeHive(int num){
		isHive = true;
		hiveNumber = num;
		if (hiveNumber == 0){
			System.out.println("Start hive node (x, y, z) : (" + X + ", " + Y + ", " + Z + ")");
		}else if (hiveNumber == 14){
			System.out.println("Ending hive node (x, y, z) : (" + X + ", " + Y + ", " + Z + ")");
		}
	}
	//Bees are only not solid because they move one by one.
	public boolean makeBee(int beeNum){
		if (!isSolid && !isBee){
			if (!isHive){
				isBee = true;
				beeNumber = beeNum;
				System.out.println("Bee #" + beeNumber + " (x, y, z) : (" + X + ", " + Y + ", " + Z + ")");
				return true;
			}//else{
			//finished = true;
			//}
		}
		return false;
	}

	public boolean makeSolid(){
		if(!isSolid && !isBee){
			if(!isHive){
				isSolid = true;
				//System.out.println("Trash / Solid Block (x, y, z) : (" + X + ", " + Y + ", " + Z + ")");
				return true;
			}
			//else{
			//finished = true;
			//}
		}
		return false;
	}
	//Blame Alden for this monstrosity.
	/*public String get(keys key){
		switch (key){
			case X : return Integer.toString(this.X);
			case Y : return Integer.toString(this.Y);
			case Z : return Integer.toString(this.Z);
			case beeNumber : return (isBee) ? Integer.toString(beeNumber) : null;
			case isSolid : return (isSolid) ? "true" : "false";
			case isBee: return (isBee) ? "true" : "false";
			case isHive : return (isHive) ? "true" : "false";
			case finished : return (finished) ? "true" : "false";
			case numberOfMoves: return Integer.toString(numberOfMoves);
			case hiveNumber : return Integer.toString(hiveNumber);
			//case nodeNumber : return Integer.toString(nodeNumber);
			default : return "";
		}
	}*/
	
	
}
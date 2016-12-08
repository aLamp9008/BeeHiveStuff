package BeeHiveStuff;
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

	private Node[] next = new Node[26];
	
	public Node(int X,int Y, int Z){
		this.X = X;
		this.Y = Y;
		this.Z = Z;
		isSolid = false;
		isHive = false;
		isBee = false;
		finished = false;
		numberOfMoves = 0;
	}
	//Blame Alden and my apathy for this monstrosity.
	public void calculateNextTo(){
        /*
        next[0] = Driver.cube[X][Y][Z + 1];
        next[1] = Driver.cube[X][Y][Z - 1];
        next[2] = Driver.cube[X][Y + 1][Z];
        next[3] = Driver.cube[X][Y - 1][Z];
        next[4] = Driver.cube[X][Y + 1][Z + 1];
        next[5] = Driver.cube[X][Y + 1][Z - 1];
        next[6] = Driver.cube[X][Y - 1][Z + 1];
        next[7] = Driver.cube[X][Y - 1][Z - 1];
        next[8] = Driver.cube[X + 1][Y][Z];
        next[9] = Driver.cube[X - 1][Y][Z];
        next[10] = Driver.cube[X + 1][Y + 1][Z + 1];
        next[11] = Driver.cube[X + 1][Y + 1][Z - 1];
        next[12] = Driver.cube[X + 1][Y - 1][Z + 1];
        next[13] = Driver.cube[X + 1][Y - 1][Z - 1];
        next[14] = Driver.cube[X - 1][Y + 1][Z + 1];
        next[15] = Driver.cube[X - 1][Y + 1][Z - 1];
        next[16] = Driver.cube[X - 1][Y - 1][Z + 1];
        next[17] = Driver.cube[X - 1][Y - 1][Z - 1];
        next[18] = Driver.cube[X - 1][Y + 1][Z];
        next[19] = Driver.cube[X - 1][Y - 1][Z];
        next[20] = Driver.cube[X - 1][Y][Z + 1];
        next[21] = Driver.cube[X - 1][Y][Z - 1];
        next[22] = Driver.cube[X + 1][Y + 1][Z];
        next[23] = Driver.cube[X + 1][Y - 1][Z];
        next[24] = Driver.cube[X + 1][Y][Z + 1];
        next[25] = Driver.cube[X + 1][Y][Z - 1];
        */
        ///*
        try{
         next[0] = (Driver.cube[X][Y][Z + 1].isSolid) ? null : Driver.cube[X][Y][Z + 1];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[1] = (Driver.cube[X][Y][Z - 1].isSolid) ? null : Driver.cube[X][Y][Z - 1];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[2] = (Driver.cube[X][Y + 1][Z].isSolid) ? null : Driver.cube[X][Y + 1][Z];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[3] = (Driver.cube[X][Y - 1][Z].isSolid) ? null : Driver.cube[X][Y - 1][Z];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[4] = (Driver.cube[X][Y + 1][Z + 1].isSolid) ? null : Driver.cube[X][Y + 1][Z + 1];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[5] = (Driver.cube[X][Y + 1][Z - 1].isSolid) ? null : Driver.cube[X][Y + 1][Z - 1];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[6] = (Driver.cube[X][Y - 1][Z + 1].isSolid) ? null : Driver.cube[X][Y - 1][Z + 1];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[7] = (Driver.cube[X][Y - 1][Z - 1].isSolid) ? null : Driver.cube[X][Y - 1][Z - 1];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[8] = (Driver.cube[X + 1][Y][Z].isSolid) ? null : Driver.cube[X + 1][Y][Z];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[9] = (Driver.cube[X - 1][Y][Z].isSolid) ? null : Driver.cube[X - 1][Y][Z];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[10] = (Driver.cube[X + 1][Y + 1][Z + 1].isSolid) ? null : Driver.cube[X + 1][Y + 1][Z + 1];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[11] = (Driver.cube[X + 1][Y + 1][Z - 1].isSolid) ? null : Driver.cube[X + 1][Y + 1][Z - 1];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[12] = (Driver.cube[X + 1][Y - 1][Z + 1].isSolid) ? null : Driver.cube[X + 1][Y - 1][Z + 1];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[13] = (Driver.cube[X + 1][Y - 1][Z - 1].isSolid) ? null : Driver.cube[X + 1][Y - 1][Z - 1];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[14] = (Driver.cube[X - 1][Y + 1][Z + 1].isSolid) ? null : Driver.cube[X - 1][Y + 1][Z + 1];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[15] = (Driver.cube[X - 1][Y + 1][Z - 1].isSolid) ? null : Driver.cube[X - 1][Y + 1][Z - 1];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[16] = (Driver.cube[X - 1][Y - 1][Z + 1].isSolid) ? null : Driver.cube[X - 1][Y - 1][Z + 1];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[17] = (Driver.cube[X - 1][Y - 1][Z - 1].isSolid) ? null : Driver.cube[X - 1][Y - 1][Z - 1];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[18] = (Driver.cube[X - 1][Y + 1][Z].isSolid) ? null : Driver.cube[X - 1][Y + 1][Z];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[19] = (Driver.cube[X - 1][Y - 1][Z].isSolid) ? null : Driver.cube[X - 1][Y - 1][Z];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[20] = (Driver.cube[X - 1][Y][Z + 1].isSolid) ? null : Driver.cube[X - 1][Y][Z + 1];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[21] = (Driver.cube[X - 1][Y][Z - 1].isSolid) ? null : Driver.cube[X - 1][Y][Z - 1];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[22] = (Driver.cube[X + 1][Y + 1][Z].isSolid) ? null : Driver.cube[X + 1][Y + 1][Z];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[23] = (Driver.cube[X + 1][Y - 1][Z].isSolid) ? null : Driver.cube[X + 1][Y - 1][Z];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[24] = (Driver.cube[X + 1][Y][Z + 1].isSolid) ? null : Driver.cube[X + 1][Y][Z + 1];
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
        next[25] = (Driver.cube[X + 1][Y][Z - 1].isSolid) ? null : Driver.cube[X + 1][Y][Z - 1];
        }catch(ArrayIndexOutOfBoundsException e){}
         //*/
    }
	
	public Node[] getNextTo(){
		return next;
	}
	
	//public enum keys{
	//	X, Y, Z, isSolid, isHive, isBee, beeNumber, finished, numberOfMoves, hiveNumber
	//}
	//Blame all these awful "make" methods on Alden
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
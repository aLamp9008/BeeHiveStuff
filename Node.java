package BeeHiveStuff;
//import BeeHiveStuff.Driver;
public class Node{
	
	public int X,Y,Z;
	public boolean isSolid;
	private boolean isHive;
	public boolean isBee;
	public boolean finished;
	public int beeNumber;
	private int numberOfMoves;
	public int hiveNumber;
	
	public Integer[] distance = new Integer[15];
	
	public Node[] next = new Node[26];
	
	public Node(int X,int Y, int Z){
		this.X = X;
		this.Y = Y;
		this.Z = Z;
		isSolid = false;
		isHive = false;
		isBee = false;
		finished = false;
		numberOfMoves = 0;
		for (int i = 0; i < 15; i++){
			this.distance[i] = null;
		}
	}
	
	public void toNull(){
		for (int i = 0; i < 15; i++){
			this.distance[i] = null;
			this.finished = false;
		}
	}
	
	private void getNextTo(int number, int a, int b, int c) {
		try{
			 next[number] = (Driver.cube[a][b][c].isSolid) ? null : Driver.cube[a][b][c];
		}catch(ArrayIndexOutOfBoundsException e){
			
		}
	}
	
	public void calculateNextTo(){
		getNextTo(0, X, Y, Z + 1);
		getNextTo(1, X, Y, Z - 1);
		getNextTo(2, X, Y + 1, Z);
		getNextTo(3, X, Y - 1, Z);
		getNextTo(4, X, Y + 1, Z + 1);
		getNextTo(5, X, Y + 1, Z - 1);
		getNextTo(6, X, Y - 1, Z + 1);
		getNextTo(7, X, Y - 1, Z - 1);
		getNextTo(8, X + 1, Y, Z);
		getNextTo(9, X - 1, Y, Z);
		getNextTo(10, X + 1, Y + 1, Z + 1);
		getNextTo(11, X + 1, Y + 1, Z - 1);
		getNextTo(12, X + 1, Y - 1, Z + 1);
		getNextTo(13, X + 1, Y - 1, Z - 1);
		getNextTo(14, X - 1, Y + 1, Z + 1);
		getNextTo(15, X - 1, Y + 1, Z - 1);
		getNextTo(16, X - 1, Y - 1, Z + 1);
		getNextTo(17, X - 1, Y - 1, Z - 1);
		getNextTo(18, X - 1, Y + 1, Z);
		getNextTo(19, X - 1, Y - 1, Z);
		getNextTo(20, X - 1, Y, Z + 1);
		getNextTo(21, X - 1, Y, Z - 1);
		getNextTo(22, X + 1, Y + 1, Z);
		getNextTo(23, X + 1, Y - 1, Z);
		getNextTo(24, X + 1, Y, Z + 1);
		getNextTo(25, X + 1, Y, Z - 1);
	}
	
	
	
	
	public Node[] getNextTo(){
		return next;
	}
	
	enum keys{
		X, Y, Z, isSolid, isHive, isBee, beeNumber, finished, numberOfMoves, hiveNumber
	}
	
	public void makeHive(int num){
		isHive = true;
		hiveNumber = num;
		if (hiveNumber == 0){
			System.out.println("Start hive node (x, y, z) : (" + X + ", " + Y + ", " + Z + ")");
		}else if (hiveNumber == 14){
			System.out.println("Ending hive node (x, y, z) : (" + X + ", " + Y + ", " + Z + ")");
		}
	}

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
		}
		return false;
	}
}
package BeeHiveStuff;
import java.util.HashMap;
public class Result {
	public HashMap<Node, Integer> paths;
	public HashMap<Node, Node> pointed;
	public Result(HashMap<Node, Integer> paths, HashMap<Node, Node> pointed) {
		this.paths = paths;
		this.pointed = pointed;
	}
}
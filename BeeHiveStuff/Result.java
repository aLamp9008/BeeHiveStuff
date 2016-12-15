package BeeHiveStuff;
import java.util.HashMap;
import java.util.List;
public class Result {
	public List<HashMap<Node, Integer>> paths;
	public Node[] destinations;
	public Result(List<HashMap<Node, Integer>> paths, Node[] destinations) {
		this.paths = paths;
		this.destinations = destinations;
	}
}
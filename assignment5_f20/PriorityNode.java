package assignment5_f20;

public class PriorityNode implements Comparable<PriorityNode> {

	private long priority;
	private Node node;
	
	public PriorityNode(long prior, Node n)
	{
		this.priority = prior;
		this.node = n;
	}

	@Override
	public int compareTo(PriorityNode o) { //necessary for comparable and managing our priority queue which uses objects
		return Integer.compare((int) this.priority, (int)o.priority);
	}
	
	public Node getNode() {
		return this.node;
	}
	
	public long getPriority() {
		return this.priority;
	}
	
}

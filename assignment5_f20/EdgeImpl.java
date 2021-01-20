package assignment5_f20;

public class EdgeImpl implements Edge {
	private Node destNode;
	private long weight;
	private Node sourceNode;
	private long UID;
	private String edgeLabel;
	
	public EdgeImpl(long id, Node source, Node dest, long weight, String label) //initalization
	{
		this.UID = id;
		this.sourceNode = source;
		this.destNode = dest;
		this.weight = weight;
		this.edgeLabel = label;
	}
	
	@Override
	public long getWeight() { //get the weight of edge
		return weight;
	}
	@Override
	public Node getDestNode() { //get the destination node
		return destNode;
	}
	@Override
	public void setDestNode(Node dest) { //set the destination node
		this.destNode = dest;
		
	}
	@Override
	public Node getSourceNode() { //get the source node
		return this.sourceNode;
	}
	@Override
	public void setSourceNode(Node source) { //set the source node of an edge
		this.sourceNode = source;
		
	}

	@Override
	public long getUID() {
		return UID;
	}

	@Override
	public String getEdgeLabel() {
		return edgeLabel;
	}
	
}

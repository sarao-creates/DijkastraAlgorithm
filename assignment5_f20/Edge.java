package assignment5_f20;

public interface Edge {
	public long getWeight();
	public Node getDestNode();
	public void setDestNode(Node dest);
	public Node getSourceNode();
	public void setSourceNode(Node source);
	public long getUID();
	public String getEdgeLabel();
}

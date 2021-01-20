package assignment5_f20;

public interface Node {
	public String getNodeLabel();
	public Edge getEdgeOut(String dlabel);
	public void setEdgeOut(String dlabel, Edge newEdge);
	public void setEdgeIn(String slabel, Edge newEdge);
	public void removeEdgesIn(String sLabel);
	public void removeEdgesOut(String dLabel);
	public void updateAdjacentNodesOnDelete();
	public long getEdgeCount();
	public void setKnown(boolean value);
	public Edge[] getListOfEdges();	
	public boolean getKnown();
	public void setDistance(long value);
	public long getDistance();
	public void setPathNode(Node n);
	public Node getPathNode();


}

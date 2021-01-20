package assignment5_f20;

import java.util.HashMap;

public class NodeImpl implements Node {
	
	private String nodeLabel;
	private long UID;
	HashMap<String, Edge> edgesIn = new HashMap<String, Edge>();
	HashMap<String, Edge> edgesOut = new HashMap<String, Edge>();
	private long edgeCount;
	private boolean known;
	private Node pathNode;
	private long distance;

	
	public NodeImpl(long id, String label) //initialization
	{
		this.nodeLabel = label;
		this.UID = id;
		this.edgeCount = 0;
		this.known = false;
	}
	
	@Override
	public String getNodeLabel() { //returns the name of the node
		return nodeLabel;
	}
	
	public Edge getEdgeOut(String dlabel) //returns an outgoing Edge if available
	{
		if (edgesOut.get(dlabel) == null)
		{
			return null;
		}
		
		return edgesOut.get(dlabel);
	}
	
	public void setEdgeOut(String dlabel, Edge newEdge) //adds an outgoing edge to edgesOut HashMap
	{
		edgesOut.put(dlabel, newEdge);
		edgeCount++;
	}
	
	public void setEdgeIn(String slabel, Edge newEdge) //adds an incoming edge to edgesIn HashMap
	{
		edgesIn.put(slabel, newEdge);
		edgeCount++;
	}
	
	public void removeEdgesIn(String sLabel) //removes an incoming edge from edgesIn HashMap
	{
		edgesIn.remove(sLabel);
		edgeCount--;
	}
	
	public void removeEdgesOut(String dLabel) //removes an outgoing edge from edgesOut HashMap
	{
		edgesOut.remove(dLabel);
		edgeCount--;
	}
	
	public void updateAdjacentNodesOnDelete() //this method is used to update the edgesIn and edgesOut of adjacent nodes
	{
		for (Edge value : edgesOut.values())
		{
			value.getDestNode().removeEdgesIn(nodeLabel);
		}
		
		for (Edge value : edgesIn.values())
		{
			value.getSourceNode().removeEdgesOut(nodeLabel);
		}
	}
	
	public long getEdgeCount() //returns the node's edge count.
	{
		return edgeCount;
	}
	
	public void setKnown(boolean value) //sets the known to whatever boolean requested
	{
		this.known = value;
	}
	
	public boolean getKnown() //gets the known
	{
		return this.known;
	}
	
	public Edge[] getListOfEdges() //this returns a list of outgoing edges as an array
	{
		Edge[] edgeList = new Edge[(int)edgeCount];
		int i = 0;
		for (Edge value : edgesOut.values())
		{
			edgeList[i] = value;
			i++;
		}
		
		return edgeList;
	}
	
	public void setDistance(long value) //set the distance of the node (from some other node)
	{
		this.distance = value;
	}
	
	public long getDistance() // get distance of node from some other node
	{
		return this.distance;
	}
	
	public void setPathNode(Node n) //set the path node to trace back
	{
		this.pathNode = n;
	}
	
	public Node getPathNode() //get the path node
	{
		return this.pathNode;
	}
}

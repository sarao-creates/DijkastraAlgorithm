package assignment5_f20;
import java.util.HashMap;
import java.util.PriorityQueue;

import bus.uigen.ValueChangedEvent;


public class DiGraph implements DiGraphInterface {
	
	private long nodeCount; //field for storing the graph's node count
	private long edgeCount; //field for storing the graph's edge count

	HashMap<String, Node> nodeMap = new HashMap<String, Node>(); //this map is for all nodes in the graph
	
	public DiGraph () { //initialization
		nodeCount = 0;
		edgeCount = 0;
	}

	@Override
	public boolean addNode(long idNum, String label) { 
		if (idNum < 0) //idNum's less than 0 are faulty
		{
			return false;
		}
		
		if (label == null) //null label check
		{
			return false;
		}
		
		if (nodeMap.containsKey(label) == true) //unique labels enforced
		{
			return false;
		}
		
		Node newNode = new NodeImpl(idNum, label); //creates a new node
		nodeMap.put(label, newNode); //puts new node in the map
		nodeCount++; //increases node count
		return true;
	}

	@Override
	public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
		if (idNum < 0) //enforces ids over 0
		{
			return false;
		}
		
		if (sLabel == null) //makes sure source is a valid node
		{
			return false;
		}
		
		if (dLabel == null) //makes sure destination is a valid node
		{
			return false;
		}
		
		if (nodeMap.containsKey(sLabel) == false)//makes sure source is a valid node
		{
			return false;
		}
		
		if (nodeMap.containsKey(dLabel) == false)//makes sure destination is a valid node
		{
			return false;
		}
		
		if (nodeMap.get(sLabel).getEdgeOut(dLabel) != null) //checks to see if there even is an outgoing edge to destin. node
		{
			return false;
		}
		
		Edge newEdge = new EdgeImpl(idNum, nodeMap.get(sLabel), nodeMap.get(dLabel), weight, eLabel); //creates a new edge
		nodeMap.get(sLabel).setEdgeOut(dLabel, newEdge); //creates an outgoing edge (and adds to the node's map of outgoing edges)
		nodeMap.get(dLabel).setEdgeIn(sLabel, newEdge);//creates an incoming edge to destination's node (and adds to the node's map of incoming edges)
		edgeCount++; //increase graph edge count
		return true;
	}
	
	@Override
	public boolean delNode(String label) {
		if (label == null) //makes sure label is not null
		{
			return false;
		}
		
		if (nodeMap.containsKey(label) == false)//makes sure node is even in the tree
		{
			return false;
		}
		
		long numEdgesDel = nodeMap.get(label).getEdgeCount(); //grabs the edge count of the node about to be deleted
		nodeMap.get(label).updateAdjacentNodesOnDelete(); //this method updates the edges of both adjacent nodes and the (about to be deleted) node's incoming nodes
		nodeMap.remove(label);	//delete				//tldr; the updateAdjacentNodesOnDelete() method updates all stakeholders
		nodeCount--; //decrease node count
		edgeCount-=numEdgesDel; //decrease edge count properly
		return true;
	}

	@Override
	public boolean delEdge(String sLabel, String dLabel) { 
		if (sLabel == null) //checks source isn't null
		{
			return false;
		}
		
		if (dLabel == null) //checks destination isn't null
		{
			return false;
		}
		
		if (nodeMap.containsKey(sLabel) == false)//makes sure source node is even in the graph
		{
			return false;
		}
		
		if (nodeMap.containsKey(dLabel) == false) //makes sure destination node is even in the graph
		{
			return false;
		}
		
		if (nodeMap.get(sLabel).getEdgeOut(dLabel) == null) //checks for a valid edge
		{
			return false;
		}
		
		nodeMap.get(sLabel).removeEdgesOut(dLabel); //removes the node's outgoing edge from map
		nodeMap.get(dLabel).removeEdgesIn(sLabel); //removes the adjacent node's incoming edge from map
		edgeCount--; //update edge count
		return true;
	}

	@Override
	public long numNodes() {
		return nodeCount;
	}

	@Override
	public long numEdges() {
		return edgeCount;
	}

	@Override
	public ShortestPathInfo[] shortestPath(String label) {
		int pathInfoIndex = 0; //variable for the pathInfo array (the array being returned)
		long pathLength = 0; // variable for the distance from provided node and some N node
		
		PriorityQueue<PriorityNode> pq = new PriorityQueue<PriorityNode>(); //we'll make a PriorityQueue of PriorityNodes
		ShortestPathInfo[] pathInfo = new ShortestPathInfo[(int)nodeCount]; //this array will contain ShortestPathInfo objects
		
		for (Node value : nodeMap.values()) //let's start by looping through all nodes and setting their default values
		{
			value.setKnown(false);
			value.setDistance((long) Integer.MAX_VALUE); // we set the distance super high at first so it WILL be altered
			value.setPathNode(null);
		}
				
		nodeMap.get(label).setDistance(0); //provided node will always have a distance of 0
		nodeMap.get(label).setPathNode(nodeMap.get(label));
		PriorityNode rootNode = new PriorityNode(0, nodeMap.get(label)); //create a priority node and add to queue
		pq.add(rootNode);
		
		
		while (pq.size() > 0) //we always loop until the pq is 0.
		{
			PriorityNode curr = pq.peek(); //this is the current priority node (NOT to be confused with a typical node)
			pq.poll();
			int edgesIndex = 0;
			
			if (pathInfoIndex >= pathInfo.length) //this ensures we don't have an index out of bounds...
			{
				break;
			}
			
			Node currNode = nodeMap.get(curr.getNode().getNodeLabel()); //this is the current node
			
			if (currNode.getKnown() == true) //if the node we're working with is known, then it is finalized in distance
			{
				continue;
			}
			
			pathInfo[pathInfoIndex] = new ShortestPathInfo(curr.getNode().getNodeLabel(), curr.getPriority()); //since the node is now known we can add it to our array
			currNode.setKnown(true); //and toggle it to known
			pathInfoIndex++;
			
			
			Edge[] listOfEdges = currNode.getListOfEdges(); //lets investigate outgoing edges of the node we're looking at
			Edge currEdge = listOfEdges[0];
			while (currEdge != null)
			{
				if (currEdge.getDestNode().getKnown() == false) //if one of the outgoing edges is known then don't bother
				{
					pathLength = currNode.getDistance() + currEdge.getWeight(); //calculates the total pathLength
					if (pathLength < currEdge.getDestNode().getDistance()) //if the pathLength is less
					{
						currEdge.getDestNode().setDistance(pathLength); //set the adjacent node's new distance
						currEdge.getDestNode().setPathNode(currNode); //and path back
						PriorityNode newNode = new PriorityNode(pathLength, currEdge.getDestNode()); //create a priority node
						pq.add(newNode); //add to pq
					}
				}
				
				if ((edgesIndex+1) >= listOfEdges.length) //let's ensure we're in index bounds...
				{
					break;
				}
				edgesIndex++; 
				currEdge = listOfEdges[edgesIndex];
			}
		}
		
		
		//adding the -1 distances for nodes with no path
		int k = 0;		
		ShortestPathInfo[] dullNodes = new ShortestPathInfo[(int) numNodes()]; // this array will store ShortestPathInfo objects of unreachable nodes
		
		for (Node value : nodeMap.values()) //we'll loop through all nodes in the graph
		{
			if (value.getPathNode() == null) //if we find a node that has a PathNode (aka the path back to provided root) to be null well then it has no path
			{
				dullNodes[k] = new ShortestPathInfo(value.getNodeLabel(), -1); //so we'll use that node to create our ShortestPathInfo and add to array
				k++;
			}
		}
		
		k = 0; // lets reset our counter for next part
		
		for (int i = 0; i < pathInfo.length;i++) //we're going to loop through our current pathInfo array till we find a null
		{
			if (pathInfo[i] == null) // when we find a null spot, that means that that spot doesn't have a path back to the root so...
			{
				pathInfo[i] = dullNodes[k]; //we'll take from the dullNodes array we've created
				k++; 
					// theoretically, the number of dullNodes should always be the same amount as the number of null spots in our array
			}		// let's say we have a, b, c, d but a has no path to d
					// Path info would look like this pathInfo {a path: a, b: path a, c: b, d: null}
					// so our dullNodes array would look like this {d, null, null, null}
					// so when we loop through our pathInfo and hit first null it will take off from the top. If there were more
					// unreachable nodes then k it would put the next dullNode and put it in the next null spot in pathInfo
		}
		
		return pathInfo;
	}
	  
}

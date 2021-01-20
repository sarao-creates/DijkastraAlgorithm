package assignment5_f20;

import java.util.Random;

public class DiGraphPlayground {
	
	public static void main (String[] args) {
		// thorough testing is your responsibility
	    //
	    // you may wish to create methods like 
	    //    -- print
	    //    -- sort
	    //    -- random fill
	    //    -- etc.
	    // in order to convince yourself your code is producing
	    // the correct behavior
	    exTest();
	}
	
	public static void buildonce(int num_nodes, int num_edge_per_node, int seed) {; //this is someone else's method to test time
		Random r = new Random(seed);
		DiGraph d = new DiGraph();
		long start = System.currentTimeMillis();
		d.addNode(0, "0");
		d.addNode(1, "1");
		int eid = 0;
		for(int i = 2; i < num_nodes; i ++) {
			d.addNode(i, Integer.toString(i));
			for(int j =0; j < num_edge_per_node; j ++)
			d.addEdge(eid++, Integer.toString(i), Integer.toString(r.nextInt(i)), r.nextInt(20), null);
		}
		long built = System.currentTimeMillis();
		System.out.println("numNodes: "+d.numNodes());
		System.out.println("numEdges: "+d.numEdges());
		System.out.println("Building took: " + (built - start));
		long startPath = System.currentTimeMillis();
		d.shortestPath(Integer.toString(r.nextInt(num_nodes)));
		long endPath = System.currentTimeMillis();
		System.out.println("Found paths in: " + (endPath-startPath));
		long sdel = System.currentTimeMillis();
		for (int i = 0; i < num_nodes; i++) {
			d.delNode(Integer.toString(i));
		}
		long edel = System.currentTimeMillis();
		System.out.println("Destroying took: " + (edel-sdel));

}
	  
	public static void exTest(){
		DiGraph d = new DiGraph();
//	    d.addNode(1, "f");
//	    d.addNode(3, "s");
//	    d.addNode(7, "t");
//	    d.addNode(0, "fo");
//	    d.addNode(4, "fi");
//	    d.addNode(6, "si");
//	    d.addEdge(0, "f", "s", 1, null);
//	    d.addEdge(1, "f", "si", 3, null);
//	    d.addEdge(2, "s", "t", 5, null);
//	    d.addEdge(3, "fo", "fi", 2, null);
//	    d.addEdge(4, "fi", "si", 3, null);
//	    d.shortestPath("f");
//	    d.delNode("fi");
//	    d.delEdge("t", "s");
//	    d.delEdge("s", "t");
		
		
//		d.addNode(1,  "a");
//		d.addNode(2, "b");
//		d.addNode(3, "c");
//		
//		d.addEdge(0, "a", "b", 3, null);
//		d.addEdge(1, "b", "c", 4, null);
//		d.addEdge(2, "a", "c", 10, null);
//		
//		d.shortestPath("a");
		
		
//		d.addNode(1,  "a");
//		d.addNode(2, "b");
//		d.addNode(3, "c");
//		d.addNode(4, "d");
//		
//		d.addEdge(0	, "a", "b", 1, null);
//		d.addEdge(0	, "c", "a", 3, null);
//		d.addEdge(0	, "c", "d", 2, null);
//		d.addEdge(0	, "b", "c", 2, null);
//		d.addEdge(0	, "d", "b", 1, null);
//		
//		d.shortestPath("a");
		
		
		
		
//		d.addNode(1, "p");
//		d.addNode(2, "a");
//		d.addNode(3, "g");		
//		d.addNode(4, "e");
//		
//		d.addEdge(1, "p", "a", 10, null);
//		d.addEdge(2, "a", "g", 12, null);
//		d.addEdge(3, "g", "e", 1, null);
//		d.addEdge(4, "e", "p", 6, null);
//		d.addEdge(4, "p", "g", 4, null);
//		d.addEdge(4, "a", "e", 100, null);
//		d.addEdge(4, "a", "p", 9, null);
//		d.addEdge(4, "e", "a", 3, null);
//		d.addEdge(4, "g", "a", 15, null);
//		d.addEdge(4, "g", "p", 2, null);
//		d.addEdge(4, "p", "e", 8, null);
//		
//		d.shortestPath("p");
		
		
		//Case 4
//		d.addNode(1, "0");
//		d.addNode(1, "1");
//		d.addNode(2, "2");
//		d.addNode(3, "3");		
//		d.addNode(4, "4");
//		d.addNode(3, "5");		
//		d.addNode(4, "6");
//		
//		d.addEdge(1, "0", "5", 3, null);
//		d.addEdge(2, "3", "2", 6, null);
//		d.addEdge(3, "4", "0", 1, null);
//		d.addEdge(4, "4", "5", 2, null);
//		d.addEdge(4, "6", "1", 4, null);
//
//		d.shortestPath("0");
		
		
		
		d.addNode(1, "a");
		d.addNode(1, "b");
		d.addNode(2, "c");
		d.addNode(3, "d");		
		d.addNode(4, "e");

		d.addEdge(1, "a", "b", 2, null);
		d.addEdge(2, "b", "c", 1, null);
		d.addEdge(3, "b", "d", 1, null);
		d.addEdge(4, "d", "a", 3, null);
		d.addEdge(4, "a", "d", 4, null);
		d.addEdge(3, "d", "c", 3, null);

		d.shortestPath("a");
		
		
		buildonce(1000000, 2, 3);
		
		
		
		
	    System.out.println("numEdges: "+d.numEdges());
	    System.out.println("numNodes: "+d.numNodes());
	}
}

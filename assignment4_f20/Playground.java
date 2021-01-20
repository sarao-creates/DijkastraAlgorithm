package assignment4_f20;

public class Playground {
	public static void main(String[] args) {
	  // Add more tests as methods and call them here!!
	  RunMyTests();
	  // etc.
	}

	public static void RunMyTests() {
	  
	  Cache_LFU lfc = new Cache_LFU(8);
//	  CacheFrame cf1 = new CacheFrame("a", 3);
//
////	  lfc.heap.insert(cf1);
////	  lfc.heap.delMin();
//	  
//	  lfc.refer("AA8C");
//	  lfc.refer("AA8C");
//	  lfc.refer("1234");
//	  lfc.refer("234A");
//	  lfc.refer("AA8C");
//	  lfc.refer("1234");
	  
	  
	  CacheFrame cf1 = new CacheFrame("a", 3);
	  CacheFrame cf2 = new CacheFrame("b", 5);
	  CacheFrame cf3 = new CacheFrame("c", 9);
	  CacheFrame cf4 = new CacheFrame("d", 7);
	  CacheFrame cf5 = new CacheFrame("e", 4);
	  CacheFrame cf6 = new CacheFrame("f", 6);
	  CacheFrame cf7 = new CacheFrame("g", 2);
	  
//	  CacheFrame cf1 = new CacheFrame("a", 5);
//	  CacheFrame cf2 = new CacheFrame("b", 7);
//	  CacheFrame cf3 = new CacheFrame("c", 8);
//	  CacheFrame cf4 = new CacheFrame("d", 6);
//	  CacheFrame cf5 = new CacheFrame("e", 3);
	  
//	  lfc.heap.insert(cf1);
//	  lfc.heap.insert(cf2);
//	  lfc.heap.insert(cf3);
//	  lfc.heap.insert(cf4);
//	  lfc.heap.insert(cf5);
//	  lfc.heap.getMin();

	  
	  
	  lfc.heap.insert(cf1);
	  lfc.heap.insert(cf2);
	  lfc.heap.insert(cf3);
	  lfc.heap.insert(cf4);	  
	  lfc.heap.insert(cf5);
	  lfc.heap.insert(cf6);
	  lfc.heap.insert(cf7);

	  lfc.heap.delMin();
	  lfc.heap.delMin();
  
	  
	  
	  /*
	  lfc.refer("AA8C");
	  lfc.refer("AA8C");
	  lfc.refer("1234");
	  lfc.refer("234A");
	  lfc.refer("AA8C");
	  lfc.refer("234A");
	  lfc.refer("ABCD");
	  lfc.refer("234A");
	  lfc.refer("ABCD");
	  lfc.refer("1101");
	  lfc.refer("2202"); lfc.refer("2202");
	  lfc.refer("2202"); lfc.refer("2202");

	  System.out.println(lfc.size());
	  System.out.println(lfc.numElts());
	  printHeap(lfc.getHeap().getHeap(), lfc.getHeap().size());
	 
	  // etc.*/

	}

	public static void printHeap(CacheFrame[] e,int len) { 
	  // this method skips over unused 0th index....
	  System.out.println("Printing Heap");
	  for(int i=1; i< len+1; i++) {
		  System.out.print("(p."+e[i].value+",f"+e[i].priority+",s"+e[i].getSlot()+")\t");
	  }
	  System.out.print("\n");
	}
}

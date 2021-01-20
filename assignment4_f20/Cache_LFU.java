package assignment4_f20;

import java.util.HashMap;

public class Cache_LFU implements Cache {
	HashMap<String, CacheFrame> map; 
    // allocate from java collections lib
    // do it this way so we all start with default size and 
    // default lambda and default hash function for string keys
	MinBinHeap heap; // your own heap code above
	int limit;       // max num elts the cache can hold
	int size;        // current number elts in the cache
  
	Cache_LFU (int maxElts) {
		this.map = new HashMap<String, CacheFrame>();
		this.heap = new MinBinHeap(maxElts);
		this.limit = maxElts;
		this.size = 0;
	}
  
	// dont change this we need it for grading
	public MinBinHeap getHeap() { return this.heap; }
	public HashMap getHashMap() { return this.map; }
	
	// =========================================================
	//
	// you fill in code for the other ops in the interface
	//
	//==========================================================

	@Override
	public int size() { 
		return this.limit; //this is the size of our cache
	}

	@Override
	public int numElts() {
		return this.size; //this holds the number of elts on the cache
	}

	@Override
	public boolean isFull() {
		if (size() == numElts())
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}

	@Override
	public boolean refer(String address) {
		if (this.map.containsKey(address)) //we first to check the map to see if its contains our address
		{
			this.heap.incElt(map.get(address)); //if it does we want to just increase the frequency
			return true;
		}
		
		else //this assumes we did not find our address in the map 
		{
			if (isFull()) //first we'll see if the cache is full
			{
				CacheFrame cf = new CacheFrame(address, 1); //when its full we'll just make a new frame
				this.map.remove(this.heap.getMin().getValue()); //we're going to remove the minimum frame from map
				this.heap.delMin(); //we'll remove the min frame from the heap
				this.heap.insert(cf); //insert our new frame
				this.map.put(address, cf); //map out our new frame
				return false;
			}
		
			else //if the cache isn't full we just make a new frame and insert it in our heap / map
			{
				CacheFrame cf = new CacheFrame(address, 1);
				this.heap.insert(cf);
				this.map.put(address, cf);
				this.size++;
				return false;
			}
		}
	}
  
}

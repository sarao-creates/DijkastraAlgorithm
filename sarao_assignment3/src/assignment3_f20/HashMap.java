package assignment3_f20;

public interface HashMap extends Map {
	public String maxKey(); 
	public String minKey(); 
	public String[] getKeys();  // in random order
	  
	public int hash(String key, int tabSize);
	  
	public double lambda();  // compute lamda load factor
	public double extend();    // double table size, rehash, return new lambda
	  
	// leave this in... for grader
	// also specific to hash table structure
	public HMCell[] getTable();
}

package assignment3_f20;

public class HashMap_imp implements HashMap{
	HMCell[] tab;
	int nelts;
	  
	//-------------------------------------------------------------

	HashMap_imp (int num) { 
	  this.tab = new HMCell[num];
	  // for (int i=0; i<num; i++) { tab[i] = null; }
	  // we can rely on the Java compiler to fill the table array with nulls
	  // another way would be Array.fill()
	  this.nelts = 0; 
	}

	//-------------------------------------------------------------
	  
	public int hash (String key, int tabSize) {
	  int hval = 7;
	  for (int i=0; i<key.length(); i++) {
	    hval = (hval*31) + key.charAt(i);
	  }
	  hval = hval % tabSize;
	  if (hval<0) { hval += tabSize; }
	  return hval;
	}
	  
	//-------------------------------------------------------------

	// dont change 
	@Override
	public HMCell[] getTable() { return this.tab; }

	@Override
	public Value put(String k, Value v) {
		
		int hashCode = hash(k, tab.length); //this is the hash code for the put based on provided method
		
		
		if (tab[hashCode] == null) //if when we go to our hashcode its null, we'll just add a new cell
		{
			HMCell newCell = new HMCell_imp(k,v);
			tab[hashCode] = newCell;
			nelts++;
			if (lambda() >= 1) //before finishing our put, we want to check if lambda is >= 1 to extend autom.
			{
				extend();
			}
			return null;
		}
		
		else //if when we go to our hashcode we find that there is cell already there...
		{
			HMCell curr = tab[hashCode];
			while (curr != null) //we'll loop through the linked list till we find a matching key or till it finishes
			{
				if (curr.getKey().compareTo(k) == 0) //returns previous value of matched key
				{
					Value oldVal = curr.getValue();
					curr.setValue(v);
					return oldVal;
				}
				curr = curr.getNext();
			}
			
			HMCell temp = tab[hashCode]; //if we don't find a match, this adds a new cell to the front.
			HMCell newCell = new HMCell_imp(k,v);
			tab[hashCode] = newCell;
			newCell.setNext(temp);
			nelts++;
		}
		
		if (lambda() >= 1) //again before exiting our put, we'll want to extend
		{
			extend();
		}
		
		return null;
	}

	@Override
	public Value get(String k) {
		int hashCode = hash(k, tab.length); // let's start by getting the hash code of the key
		
		if (size() == 0) //we'll check if the size is 0 and auto return null since there can be no key to get if size is 0
		{
			return null;
		}
		
		if (tab[hashCode] == null) //if the hashCode we go to is null then we can also return null
		{
			return null;
		}
		
		HMCell curr = tab[hashCode]; //at this point, we've gone to the hashcode and there is a linked list so we'll loop
		while (curr != null)
		{
			if (curr.getKey().compareTo(k) == 0) //if we find a match, let's return the value that the key has
			{
				return curr.getValue();
			}
			
			curr = curr.getNext();
		}
		
		return null; // if we don't find a match, then we'll just return null
	}

	@Override
	public void remove(String k) { 
		
		int hashCode = hash(k, tab.length); //again start by getting the key's hash code
		HMCell prevCell;
		
		HMCell curr = tab[hashCode];
		
		if (curr == null) // checks if there is no cells at the hash code and returns since there will be nothing to remove
		{
			return;
		}
		
		if (curr.getKey().compareTo(k) == 0) //this is the first check -- if we find the top cell of a hashcode matches...
		{
			tab[hashCode] = null; //simply just set the hashcode to null
			nelts--;
			return;
		}
		
		prevCell = curr; //previous cell will be the one behind curr
		curr = curr.getNext();
		while (curr != null) // loop through linked list till match or till no match
		{
			if (curr.getKey().compareTo(k) == 0) //if we find a match, we'll use the previous cell to reorder structure
			{
				prevCell.setNext(curr.getNext());
				nelts--;
				return;
			}
			
			prevCell = curr; 
			curr = curr.getNext();
		}
		
		return;
		
	}

	@Override
	public boolean hasKey(String k) { 
		int hashCode = hash(k,tab.length); //again, start by getting the hash code
		
		if (tab[hashCode] == null) //we'll check if there is no linked list at hash code and return false
		{
			return false;
		}
		
		HMCell curr = tab[hashCode];
		while (curr != null) //loop through linked list till we find a match or no match
		{
			
			if (curr.getKey().compareTo(k) == 0) // if we find a match, we'll return true 
			{
				return true;
			}
			
			curr = curr.getNext();
		}
		
		return false; // if no match
		
		
	}

	@Override
	public int size() {
		return nelts;
	}

	@Override
	public String maxKey() {
		String maxKey = null;
		
		for (int i = 0; i < tab.length; i++) //this loops through each "hash code" in the hash table
		{
			HMCell curr = tab[i]; //this is set to the first cell in a linked list
			if (maxKey == null) //if the max key hasn't been changed we'll change it to the first value of curr
			{
				if (curr != null)
				{
					maxKey = curr.getKey();
				}
			}
			while (curr != null) //we'll loop through linked list till we find a key greater lexographically
			{
				if (curr.getKey().compareTo(maxKey) > 0)
				{
					maxKey = curr.getKey();
				}
				curr = curr.getNext();
			}
		}
		
		return maxKey;
	}

	@Override
	public String minKey() {
		String minKey = null;
				
		for (int i = 0; i < tab.length; i++) //same logic as above but changed to check less than lexographically
		{
			HMCell curr = tab[i];
			if (minKey == null)
			{
				if (curr != null)
				{
					minKey = curr.getKey();
				}
				
			}
			while (curr != null)
			{
				if (curr.getKey().compareTo(minKey) < 0)
				{
					minKey = curr.getKey();
				}
				curr = curr.getNext();
			}
		}
		
		return minKey;
	}

	@Override
	public String[] getKeys() {
		String[] keys = new String[size()]; //we make a keys array with the size of nelts
		int arrayIndex = 0;
		
		for (int i = 0; i < tab.length; i++) // we loop through the hash table
		{
			HMCell curr = tab[i];
			
			while (curr != null) //we loop through the linked lists at hash codes
			{
				if (curr != null)
				{
					keys[arrayIndex] = curr.getKey(); //we fill the array when we find a key and increment arrayIndex
					arrayIndex++;
				}
				
				curr = curr.getNext();
			}
		}
		
		return keys;
	}
	
	@Override
	public double lambda() {
		return ((double) (size()) / tab.length);
	}

	@Override
	public double extend() {
		HMCell[] newTable = new HMCell[tab.length * 2]; //we'll make a new hash table double the size
		int newHashCode; 
		
		for (int i = 0; i < tab.length; i++) //let's loop through each hash code in the old table
		{
			HMCell curr = tab[i];
			while (curr != null) // we'll loop through each linked list of a hash code
			{
				newHashCode = hash(curr.getKey(), newTable.length); //let's get the new hash code
				if(newTable[newHashCode] == null) //check if the new hash code is null, if so make a new cell and place
				{
					HMCell newCell = new HMCell_imp(curr.getKey(), curr.getValue());
					newTable[newHashCode] = newCell;
				}
					
				else //if the new hash code has a linked list add it to the front
				{
					
					HMCell temp = newTable[newHashCode];
					HMCell newCell = new HMCell_imp(curr.getKey(), curr.getValue());
					newTable[newHashCode] = newCell;
					newCell.setNext(temp);
				}
				
				curr = curr.getNext();
			}
		}
		
		this.tab = newTable; //reset the HM hash table
		return lambda(); //return the new lambda
		
		
	}

}

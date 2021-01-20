package assignment4_f20;

public class MinBinHeap implements Heap {
	private CacheFrame[] array; // load this array
	private int size;      // how many items currently in the heap
	private int arraySize; // Everything in the array will initially
	                         // be null. This is ok! Just build out
	                         // from array[1]

	public MinBinHeap(int nelts) {
	  this.array = new CacheFrame[nelts+1];  // remember we dont use slot 0
	  this.arraySize = nelts+1;
	  this.size = 0;
	  this.array[0] = new CacheFrame(null, 0); // 0 not used, so this is arbitrary
	}

	// Please do not remove or modify this method! Used to test your entire Heap.
	@Override
	public CacheFrame[] getHeap() { return this.array; }
	
	//===============================================================
	//
	// here down you implement the ops in the interface
	//
	//===============================================================

	@Override
	public void insert(CacheFrame elt) { 
		for (int i = 1; i < this.arraySize; i++) //we're going to loop through our array till we find the first null value
		{
			if (this.array[i] == null)
			{
				this.array[i] = elt; //we'll add our element to this index
				size++; //increase our size
				elt.setSlot(i); //we'll set our element's slot to i
				break;
			}
		}
		bubbleUp(elt); //calls our private bubbleUp method (at the bottom)	
	}

	@Override
	public void delMin() {
		if (size() == 0) //nothing to delete
		{
			return;
		}
		
		if (size() == 1) //simple quick case
		{
			this.array[1] = null;
			size--;
			return;
		}
		
		size--; //delMin decreases our numElts by 1.
		int lastEltIndex = arraySize - 1; //default value for our lastEltIndex.
		
		
		for (int i = arraySize - 1; i >= 0; i--) //this loop goes through from the back of the array to find the last element
		{
			if (this.array[i] != null)
			{
				lastEltIndex = i; //this is the last element's index
				break;
			}
		}
		
		this.array[1] = this.array[lastEltIndex]; //we'll want to set our last element as the first in delMin
		this.array[lastEltIndex] = null; //set the original last element to null now
		this.array[1].setSlot(1); 
		
		CacheFrame lChild = this.array[2]; //our default left child will always be 2
		CacheFrame rChild = this.array[3]; //default right child will always be 3
		CacheFrame eltTracked = this.array[1]; //for simplicity sake lets just copy the frame
		int eltTrackedPriority = this.array[1].getPriority(); //we'll use its priority in calculations
		
		if (rChild == null) //all we need to do is check if slot 3 is null. If slot 2 were null, 
		{ 					//that would imply size is 1 otherwise it would disobey the heap structure property. 
			if (eltTracked.getPriority() > lChild.getPriority()) //we just have to check to swap with left child.
			{
				lChildSwap(eltTracked, lChild); //calls our left child swap method
				return; //we'll want to return since there's nothing else to do...remember rChild is null.
			}
			return;
		}
		
		while ((eltTrackedPriority > lChild.getPriority()) || (eltTrackedPriority > rChild.getPriority())) //this loops while either
		{																		//left child or right child is smaller than parent
			if ((lChild.getPriority() < eltTrackedPriority) && (rChild.getPriority() < eltTrackedPriority)) //if both are smaller we need to compare children
			{
				if (lChild.getPriority() < rChild.getPriority()) //if left child is smaller, we'll call left child swap method
				{
					lChildSwap(eltTracked, lChild);
				}
				
				else if (lChild.getPriority() > rChild.getPriority()) //if right child is smaller, we'll call right child swap
				{
					rChildSwap(eltTracked, rChild);
				}
				
				else //if both are equal it doesn't matter. In this case, we'll call our left child swap.
				{
					lChildSwap(eltTracked, lChild);
				}
			}
			
			else if ((lChild.getPriority() < eltTrackedPriority)) //so now our left child is smaller than parent:
			{
				lChildSwap(eltTracked, lChild);
			}
			
			else if ((rChild.getPriority() < eltTrackedPriority)) //our right child is smaller than parent:
			{
				rChildSwap(eltTracked, rChild);
			}
		
			
			/* Now we'll need to reset for the next loop.
			 * Bubble down continues until neither child is smaller
			 * We'll need to set our left and right children. */
			
			if ((eltTracked.getSlot() * 2) <= (arraySize - 1)) //lets make sure a left child is even in the array bounds
			{
				if (this.array[eltTracked.getSlot() * 2] == null) //okay, well now lets make sure its not null. If it is then return.
				{
					return;
				}
				lChild = this.array[eltTracked.getSlot() * 2]; //coast is clear -- we can set our left child
			}
			
			else //if the array child is not in the array bounds just return.
			{
				return;
			}
			
			if ((eltTracked.getSlot() * 2) + 1 <= (arraySize - 1)) //lets make sure our right child is in the array bounds
			{
				if (this.array[(eltTracked.getSlot() * 2) + 1] == null) //now lets make sure our right child isn't null. if it is:
				{
					if (eltTracked.getPriority() > lChild.getPriority()) // there's a possibility that our left child could swap before we exit our delMin
					{
						lChildSwap(eltTracked, lChild);
					}
					return;
				}
				rChild = this.array[(eltTracked.getSlot() * 2) + 1];
			}
			
			else //if our right child isn't in the array bounds (and our left one is) then we'll check for a swap
			{
				if (eltTracked.getPriority() > lChild.getPriority())
				{
					lChildSwap(eltTracked, lChild);
				}
				return;
			}
		}
		return;
	}

	@Override
	public CacheFrame getMin() {
		return this.array[1];
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public void incElt(CacheFrame elt) {
		elt.setPriority(elt.getPriority() + 1); //increase our priority
		CacheFrame lChild = null; //default values
		CacheFrame rChild = null;
		
		if ((elt.getSlot() * 2) <= (arraySize - 1)) //if lChild is in the array bounds
		{
			if (this.array[elt.getSlot() * 2] == null) //okay, well now lets make sure its not null. If it is then return.
			{
				return;
			}
			lChild = this.array[elt.getSlot() * 2]; 
		}
	
		else
		{
			return;
		}
		
		if ((elt.getSlot() * 2) + 1 <= (arraySize - 1)) //let's make sure our right child is within bounds
		{
			if (this.array[(elt.getSlot() * 2) + 1] == null) //now lets make sure our right child isn't null. if it is:
			{
				if (elt.getPriority() > lChild.getPriority()) // there's a possibility that our left child could swap before we exit our delMin
				{
					lChildSwap(elt, lChild);
				}
			}
			rChild = this.array[(elt.getSlot() * 2) + 1];
		}
		
		else //if our right child is not within bounds
		{
			if (elt.getPriority() > lChild.getPriority()) //check for a left child swap
			{
				lChildSwap(elt, lChild);
			}
		}
		
		int eltTrackedPriority = elt.getPriority(); // for ease of use
	
		
		while ((eltTrackedPriority > lChild.getPriority()) || (eltTrackedPriority > rChild.getPriority()))
		{
			if ((lChild.getPriority() < eltTrackedPriority) && (rChild.getPriority() < eltTrackedPriority))
			{
				if (lChild.getPriority() < rChild.getPriority())
				{
					lChildSwap(elt, lChild);
				}
				
				else if (lChild.getPriority() > rChild.getPriority())
				{
					rChildSwap(elt, rChild);
				}
				
				else
				{
					lChildSwap(elt, lChild);
				}
			}
			
			else if ((lChild.getPriority() < eltTrackedPriority))
			{
				lChildSwap(elt, lChild);
			}
			
			else if ((rChild.getPriority() < eltTrackedPriority))
			{
				rChildSwap(elt, rChild);
			}
			
			if ((elt.getSlot() * 2) <= (arraySize - 1))
			{
				if (this.array[elt.getSlot() * 2] == null)
				{
					return;
				}
				lChild = this.array[elt.getSlot() * 2];
			}
			
			else
			{
				return;
			}
			
			/* Now we'll need to reset for the next loop.
			 * Bubble down continues until neither child is smaller
			 * We'll need to set our left and right children. */
			
			if ((elt.getSlot() * 2) + 1 <= (arraySize - 1))
			{
				if (this.array[(elt.getSlot() * 2) + 1] == null)
				{
					if (elt.getPriority() > lChild.getPriority())
					{
						lChildSwap(elt, lChild);
						return;
					}
					return;
				}
				rChild = this.array[(elt.getSlot() * 2) + 1];
			}
			
			else
			{
				if (elt.getPriority() > lChild.getPriority())
				{
					lChildSwap(elt, lChild);
					return;
				}
				return;
			}
					
			if ((lChild == null) && (rChild == null))
			{
				return;
			}
			
			else if (rChild == null)
			{
				if (elt.getPriority() > lChild.getPriority())
				{
					lChildSwap(elt, lChild);
					return;
				}
				return;
			}
		}	
	}

	@Override
	public void decElt(CacheFrame elt) {
		if (elt.getPriority() == 1) //if the element we're passing is 1, then we can't really decrement it...
		{
			return;
		}
		elt.setPriority(elt.getPriority() - 1); // decrement priority
		bubbleUp(elt); //calls our bubbleUp method 
	}
	
	private void bubbleUp(CacheFrame elt)
	{
		while (this.array[elt.getSlot()].getPriority() < this.array[elt.getSlot() / 2].getPriority()) //loop while child is smaller than parent
		{
			CacheFrame temp = this.array[elt.getSlot() / 2]; //keeps a temp of the parent
			this.array[elt.getSlot()] = temp; //sets the child with that parent frame
			this.array[elt.getSlot() / 2] = elt; //now we'll set the parent to the child's frame
			this.array[elt.getSlot()].setSlot(elt.getSlot()); //we'll set our slots so they're right
			this.array[elt.getSlot() / 2].setSlot(elt.getSlot() / 2);
		}
	}
	
	private void lChildSwap(CacheFrame elt, CacheFrame lChild)
	{
		CacheFrame temp = lChild; //this is a left child swap:
		this.array[elt.getSlot()] = temp; 
		this.array[lChild.getSlot()] = elt;
		this.array[elt.getSlot()].setSlot(elt.getSlot());
		this.array[elt.getSlot() * 2].setSlot(elt.getSlot() * 2);
		return;
	}
	
	private void rChildSwap(CacheFrame elt, CacheFrame rChild)
	{
		CacheFrame temp = rChild; //this is a right child swap:
		this.array[elt.getSlot()] = temp;
		this.array[rChild.getSlot()] = elt;
		this.array[elt.getSlot()].setSlot(elt.getSlot());
		this.array[(elt.getSlot() * 2) + 1].setSlot((elt.getSlot() * 2) + 1);
	}
	
}

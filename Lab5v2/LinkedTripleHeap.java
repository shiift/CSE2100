import java.util.Comparator;

/**
 * 
 * @author		William S. Dickson
 * @class		CSE2100
 * @instructor	Chun-Hsi Huang
 * @school		University of Connecticut
 * @date		November 10, 2013
 * 
 */

public class LinkedTripleHeap
{
	/***********************************************************************
	class LinkedTripleHeap - a heap of Triples with methods to keep the heap
							 sorted with the smallest element at the top
	***********************************************************************/
	
	TripleListArray _list;
	
	public LinkedTripleHeap()
	{
		// LinkedTripleHeap() - initializes the list
		_list = new TripleListArray();
	}
	
	public int size()
	{
		// size() - returns the size of the heap
		return _list.size();
	}
	public boolean isEmpty()
	{
		// isEmpty() - returns true if the heap is empty, otherwise false
		return _list.isEmpty();
	}
	
	private void upHeap()
	{
		/***********************************************************************
		upHeap() - runs up the heap (ArrayList) from the current position and
				   swaps parent with child if the parent is larger
		***********************************************************************/
		int eLoc = _list.size() - 1;
		while (eLoc > 0)
		{
			Comparator<Triple> c = new TripleComparator();
			int pLoc = (eLoc-1)/2; // finds parent (truncating if necessary)
			Triple e = _list.get(eLoc);
			Triple p = _list.get(pLoc);
			if(c.compare(e, p) < 0) // if the parent is greater than the element
			{
				_list.set(eLoc, p);
				_list.set(pLoc, e);
				eLoc = pLoc;
			}
			else
			{
				break;
			}
		}
	}
	private void downHeap()
	{
		/***********************************************************************
		downHeap() - runs down the heap (ArrayList) from the root position and
					 swaps parent with smaller child if the parent is larger
		***********************************************************************/
		Comparator<Triple> c = new TripleComparator();
		int eLoc = 0;
		int left = 2*eLoc+1;
		while(left < _list.size())
		{
			int last = left;
			int right = left+1;
			if(right < _list.size())
			{
				if(c.compare(_list.get(right),_list.get(left)) < 0)
				{
					last++;
				}
			}
			if(c.compare(_list.get(eLoc),_list.get(last)) > 0)
			{
				Triple t = _list.get(eLoc);
				_list.set(eLoc, _list.get(last));
				_list.set(last, t);
				eLoc = last;
				left = 2*eLoc+1;
			}
			else
			{
				break;
			}
		}
	}
	
	public void add(Triple e)
	{
		// adds the Triple to the heap and runs upHeap()
		_list.add(e);
		upHeap();
	}
	public Triple remove()
	{
		/***********************************************************************
		remove() - removes the top element (root) from the heap and runs
		           downHeap()
		***********************************************************************/
		if(_list.size() == 0)
		{
			System.out.println("Element does not exist!");
			return null;
		}
		else if(_list.size() == 1)
		{
			return _list.remove(0);
		}
		else
		{
			Triple t = _list.get(0);
			_list.set(0, _list.remove(_list.size()-1));
			downHeap();
			return t;
		}
	}
	
	public Triple top()
	{
		/***********************************************************************
		top() - returns the top element (root) of the heap
		***********************************************************************/
		if(_list.size() == 0)
		{
			System.out.println("Element does not exist!");
			return null;
		}
		return _list.get(0);
	}
}

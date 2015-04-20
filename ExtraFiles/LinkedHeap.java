
/**
 * 
 * @author		William S. Dickson
 * @class		CSE2100
 * @instructor	Chun-Hsi Huang
 * @school		University of Connecticut
 * @date		November 10, 2013
 * 
 */

public class LinkedHeap
{
	/***********************************************************************
	class LinkedHeap - a heap with methods to keep the heap
					   sorted with the smallest element at the top
	***********************************************************************/
	
	ListArray<PQNode> _list;
	
	public LinkedHeap()
	{
		// LinkedHeap() - initializes the list
		_list = new ListArray<PQNode>();
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
			int pLoc = (eLoc-1)/2; // finds parent (truncating if necessary)
			PQNode e = _list.get(eLoc);
			PQNode p = _list.get(pLoc);
			if(e.compareTo(p) < 0) // if the parent is greater than the element
			{
				p._position = eLoc;
				_list.set(eLoc, p);
				e._position = pLoc;
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
		int eLoc = 0;
		int left = 2*eLoc+1;
		while(left < _list.size())
		{
			int last = left;
			int right = left+1;
			if(right < _list.size())
			{
				if(_list.get(right).compareTo(_list.get(left)) < 0)
				{
					last++;
				}
			}
			if(_list.get(eLoc).compareTo(_list.get(last)) > 0)
			{
				PQNode t = _list.get(eLoc);
				_list.get(last)._position = eLoc;
				_list.set(eLoc, _list.get(last));
				t._position = last;
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
	
	public void add(PQNode e)
	{
		// adds the Triple to the heap and runs upHeap()
		_list.add(e);
		upHeap();
	}
	public PQNode remove() throws InvalidInputException
	{
		/***********************************************************************
		remove() - removes the top element (root) from the heap and runs
		           downHeap()
		***********************************************************************/
		if(_list.size() == 0)
		{
			throw new InvalidInputException("Element does not exist!");
		}
		else if(_list.size() == 1)
		{
			return _list.remove(0);
		}
		else
		{
			PQNode t = _list.get(0);
			_list.set(0, _list.remove(_list.size()-1));
			downHeap();
			return t;
		}
	}
	public PQNode remove(int k) throws InvalidInputException
	{
		/***********************************************************************
		remove() - removes the top element (root) from the heap and runs
		           downHeap()
		***********************************************************************/
		if(_list.size() < k)
		{
			throw new InvalidInputException(k + " Element does not exist!");
		}
		else
		{
			PQNode t = _list.get(k);
			_list.set(k, _list.remove(_list.size()-1));
			downHeap();
			return t;
		}
	}
	
	public PQNode top() throws InvalidInputException
	{
		/***********************************************************************
		top() - returns the top element (root) of the heap
		***********************************************************************/
		if(_list.size() == 0)
		{
			throw new InvalidInputException("Element does not exist!");
		}
		return _list.get(0);
	}
	
	public PQNode get(MyVertex v)
	{
		for(int i = 0; i < size(); i++)
		{
			if(_list.get(i)._value.equals(v))
			{
				return _list.get(i);
			}
		}
		return null;
	}
}

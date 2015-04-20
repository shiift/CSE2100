import java.util.Arrays;

/**
 * 
 * @author		William S. Dickson
 * @class		CSE2100
 * @instructor	Chun-Hsi Huang
 * @school		University of Connecticut
 * @date		November 10, 2013
 * 
 */

public class TripleListArray
{
	/***********************************************************************
	class TripleListArray - implementation of an ArrayList, a dynamic array,
							for object type, Triple
	***********************************************************************/
	private Triple[] _array;
	private int _size = 0;
	
	public TripleListArray()
	{
		// TripleListArray() - initializes the Triple array, size 5
		_array = new Triple[5];
	}
	
	public void add(Triple triple)
	{
		/***********************************************************************
		add() - adds the Triple to end of the array (increasing the size of the
				array necessary)
		***********************************************************************/
		if(_array.length <= _size)
		{
			increase();
		}
		_array[_size++] = triple;
	}
	public Triple remove(int index)
	{
		/***********************************************************************
		remove() - removes the Triple at the given location
		***********************************************************************/
		Triple triple = _array[index];
		_array[index] = null;
		int loc = index;
		while(loc < _size) //not used for heaps
		{
			_array[loc] = _array[loc+1];
			_array[loc+1] = null;
			loc++;
		}
		_size--;
		return triple;
	}
	
	public void set(int index, Triple triple)
	{
		// set() - sets the index of the array to the Triple
		_array[index] = triple;
	}
	public Triple get(int index)	
	{
		// get() - returns the Triple from the array index
		return _array[index];
	}
	
	public int size()
	{
		// size() - returns the size of the array
		return _size;
	}
	
	private void increase()
	{
		// increase() - creates a copy of the array with double the length
		_array = Arrays.copyOf(_array, _array.length*2);
	}

	public boolean isEmpty()
	{
		// isEmpty() - returns false if the array is not empty, otherwise true
		if(_size > 0)
		{
			return false;
		}
		return true;
	}
}
/**
 * 
 * @author		William S. Dickson
 * @class		CSE2100
 * @instructor	Chun-Hsi Huang
 * @school		University of Connecticut
 * @date		December 7, 2013
 * 
 */

import java.util.Arrays;

public class ListArray<E>
{
	/***********************************************************************
	class ListArray - implementation of an ArrayList, a dynamic array, for
					  any object, type E
	***********************************************************************/
	private Object[] _array;
	private int _size = 0;
	
	public ListArray()
	{
		// ListArray() - initializes the Object array, size 5
		_array = new Object[5];
	}
	
	public void add(E obj)
	{
		/***********************************************************************
		add() - adds the Object to end of the array (increasing the size of the
				array necessary)
		***********************************************************************/
		if(_array.length <= _size)
		{
			increase();
		}
		_array[_size++] = obj;
	}
	@SuppressWarnings("unchecked")
	public E remove(int index)
	{
		/***********************************************************************
		remove() - removes the Triple at the given location
		***********************************************************************/
		Object obj = _array[index];
		_array[index] = null;
		int loc = index;
		while(loc < _size) //not used for heaps
		{
			_array[loc] = _array[loc+1];
			_array[loc+1] = null;
			loc++;
		}
		_size--;
		return (E) obj;
	}
	
	public void set(int index, E obj)
	{
		// set() - sets the index of the array to the Triple
		_array[index] = obj;
	}
	@SuppressWarnings("unchecked")
	public E get(int index)	
	{
		// get() - returns the Triple from the array index
		return (E) _array[index];
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

public class NodeStack<E> implements Stack<E>
{
	/***********************************************************************
	class NodeStack - contains references to the linked list of nodes using
					  the stack framework and contains methods to change the
					  stack
	***********************************************************************/
	
	protected Node<E> top;		//head node or top of the stack
	protected int size;			//size of (number of elements in) the stack
	public NodeStack()
	{
		// NodeStack() - default constructor, creates an empty stack, size = 0
		top = null;
		size = 0;
	}
	@Override
	public int size()
	{
		// size() - returns the size of the stack
		return size;
	}
	@Override
	public boolean isEmpty()
	{
		// isEmpty() - returns true if the stack is empty, otherwise returns false
		if(top == null)
		{
			return true;
		}
		return false;
	}
	@Override
	public E top() throws EmptyStackException
	{
		// top() - returns the top element of the stack
		if(isEmpty())
		{
			throw new EmptyStackException("Stack is empty.");
		}
		return top.getElement();
	}
	@Override
	public void push(E element)
	{
		// push() - pushes a new element onto the stack
		top = new Node<E>(element, top);
		size++;
	}
	@Override
	public E pop() throws EmptyStackException
	{
		// pop() - takes the top element off the stack and returns it
		if(isEmpty())
		{
			throw new EmptyStackException("Stack is empty.");
		}
		E tElement = top.getElement();
		top = top.getNext();
		size--;
		return tElement;
	}
}


public class Node<E>
{
	/***********************************************************************
	class Node - a element of a data structure that contains an char and a
				 reference to the next element of the data structure
				 (linked list)
	***********************************************************************/
	
	private E element;		//integer value in the node
	private Node<E> next;	//next node
	public Node()
	{
		// Node() - constructor that creates a node null values
		this(null, null);
	}
	public Node(E s, Node<E> n)
	{
		// Node() - constructor that creates a node with element s and next node n
		element = s;
		next = n;
	}
	
	public void append(Node<E> newNode)
	{
		// append() - sets the next node to a different node
		next = newNode;
	}
	
	public E getElement()
	{
		// getElement() - returns the element of the node
		return element;
	}
	public Node<E> getNext()
	{
		// getNext() - returns the next node
		return next;
	}
	public void setElement(E newElem)
	{
		// setElement() - sets the value of the element
		element = newElem;
	}
	public void setNext(Node<E> newNext)
	{
		// setNext() - sets the next node
		next = newNext;
	}
}

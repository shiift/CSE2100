
public class Node
{
	/***********************************************************************
	class Node - a element of a linked list that contains an char and a
				 reference to the next element of the linked list
	***********************************************************************/
	
	private int element;		//integer value in the node
	private Node next;			//next node in the linked list
	public Node(int s, Node n)
	{
		// Node() - constructor that creates a node with element s and next node n
		element = s;
		next = n;
	}
	
	public void append(Node newNode)
	{
		// append() - sets the next node to a different node
		next = newNode;
	}
	
	public int getElement()
	{
		// getElement() - returns the element of the node
		return element;
	}
	public Node getNext()
	{
		// getNext() - returns the next node of the linked list
		return next;
	}
	public void setElement(int newElem)
	{
		// setElement() - sets the value of the element
		element = newElem;
	}
	public void setNext(Node newNext)
	{
		// setNext() - sets the next node
		next = newNext;
	}
}

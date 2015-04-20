
public class SLinkedList<E>
{
	/***********************************************************************
	class SLinkedList - contains references to the linked list of nodes and
						contains methods to operate on the linked list
	***********************************************************************/
	
	public Node<E> head;			//head node
	public Node<E> tail;			//tail node
	public int size;			//size of linked list
	
	public SLinkedList()
	{
		/***********************************************************************
		SLinkedList() - constructor that creates an empty linked list
		***********************************************************************/
		head = null;
		tail = null;
		size = 0;
	}
	
	public void addToHead(Node<E> newHead)
	{
		/***********************************************************************
		addToHead() - adds a node to the head of the linked list
		***********************************************************************/
		newHead.setNext(head);
		if(head == null)
		{
			tail = newHead;
		}
		head = newHead;
		size++;
	}
	public void addToTail(Node<E> newTail)
	{
		/***********************************************************************
		addToTail() - adds a node to the tail of the linked list
		***********************************************************************/
		newTail.setNext(null);
		if(tail == null)
		{
			head = newTail;
		}
		else
		{
			tail.setNext(newTail);
		}
		tail = newTail;
		size++;
	}
	public Node<E> removeFromTail()
	{
		/***********************************************************************
		removeFromTail() - removes a node from the tail of the linked list
		***********************************************************************/
		Node<E> oldTail = tail;
		if(size == 1)
		{
			head = null;
			tail = null;
		}
		else
		{
			Node<E> currentNode = head;
			for(int i = 1; i < size - 1; i++)
			{
				currentNode = currentNode.getNext();
			}
			currentNode.setNext(null);
			tail = currentNode;
		}
		size--;
		return oldTail;
	}
	
	@Override
	public String toString()
	{
		/***********************************************************************
		toString() - overrides the toString() method with one that prints every
				     element in the linked list
		***********************************************************************/
		String returnString = "";
		Node<E> cNode = head;
		while(cNode != null)
		{
			returnString += cNode.getElement();
			cNode = cNode.getNext();
		}
		return returnString;
	}
}

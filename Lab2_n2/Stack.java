
public interface Stack<E>
{
	/***********************************************************************
	interface Stack - creates the structure of a stack
	***********************************************************************/
	
	public int size(); 				//number of element in stack
	public boolean isEmpty(); 		//returns whether or not the stack is empty (true = empty)
	public E top() 					//return the top element in the stack
		throws EmptyStackException;
	public void push(E element); 	//insert a new element on top of the stack
	public E pop() 					//remove the top element from the stack and return it
		throws EmptyStackException;
}

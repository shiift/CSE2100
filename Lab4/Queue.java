
public interface Queue<E>
{
	/***********************************************************************
	interface CircularTranQueue - a queue interface that requires a size()
								  method, an isEmpty(), an isFull(), a
								  dequeue() and an enqueue(obj)
	***********************************************************************/
    public int size();
    public boolean isEmpty();
    public boolean isFull();
    
    public E dequeue() throws QueueEmptyException; 
    public void enqueue(E obj) throws QueueFullException;
}
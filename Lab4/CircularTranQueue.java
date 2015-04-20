/*
 * 
 * @author William Song Dickson
 * CSE2100 - University of Connecticut
 *
 */
public class CircularTranQueue implements Queue<Tran>
{
	/***********************************************************************
	class CircularTranQueue - a circular queue of type Tran based on an
	 						  array data structure. The queue will grow and
	 						  shrink as elements are added and removed by
	 						  using head and tail.
	***********************************************************************/
    private Tran[] _queue;
    private final int _length;
    private int head = 0;
    private int tail = 0;

    public CircularTranQueue(int length)
    {
    	// CircularTranQueue(length) - constructor for the queue of type Tran of a given 'length'
        _length = length;
        _queue = new Tran[_length];
    }

    public int size()
    {
    	// size() - returns the size of the queue
        if(tail > head)
        {
            return tail - head;
        }
        return _length - head + tail;
    }
    public boolean isEmpty()
    {
    	// isEmpty() - returns true if the queue is empty, otherwise false
    	if(tail == head){
    		return true;
    	}
    	return false;
    }
    public boolean isFull()
    {
    	// isFull() - returns true if the queue is full, otherwise false
        int temp = tail - head; 
        if(temp == -1 || temp == (_length - 1))
            return true;
        return false;
    }

    public Tran dequeue() throws QueueEmptyException
    {
    	// isFull() - removes the first element from the queue and returns it
    	//			- if the queue is empty, it throws a QueueEmptyException
        Tran top; 
        if(isEmpty())
        {
            throw new QueueEmptyException("Queue empty.");
        }
        else
        {
            top = _queue[head];
            _queue[head] = null;
            head = (head + 1) % _length;
        }
       return top;
    }
    public void enqueue(Tran transaction) throws QueueFullException
    {
    	// enqueue(transaction) - adds the transaction to the end of the queue
    	//						- if the queue is full, it throws a QueueFullException
        if(isFull())
        {
            throw new QueueFullException("Queue full.");
        }
        else
        {
            _queue[tail] = transaction;
            tail = (tail + 1) % _length;
        }
    }
    
    public Tran top()
    {
    	// top() - returns the top element of the queue
    	return _queue[head];
    }
    
    public Tran bot()
    {
    	// bot() - returns the bottom element of the queue
    	return _queue[tail];
    }

}
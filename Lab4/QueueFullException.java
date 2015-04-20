
@SuppressWarnings("serial")
public class QueueFullException extends RuntimeException
{
	/***********************************************************************
	class QueueFullException - the exception thrown when a queue is full
	***********************************************************************/
    public QueueFullException(String message)
    {
        super(message);
    }
}
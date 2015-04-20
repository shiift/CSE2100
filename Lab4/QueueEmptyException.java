
@SuppressWarnings("serial")
public class QueueEmptyException extends RuntimeException
{
	/***********************************************************************
	class QueueEmptyException - the exception thrown when a queue is empty
	***********************************************************************/
    public QueueEmptyException(String message)
    {
        super(message);
    }
}
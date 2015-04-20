public interface BTPosition<E> extends Position<E>
{
	public BTPosition<E> getParent();
	public BTPosition<E> getLeft();
	public BTPosition<E> getRight();
	public void setElement(E element);
	public void setParent(BTPosition<E> parent);
	public void setLeft(BTPosition<E> left);
	public void setRight(BTPosition<E> right);
	
}

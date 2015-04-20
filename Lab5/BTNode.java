
public class BTNode<E> implements BTPosition<E>
{
	private E _element;
	private BTPosition<E> _left, _right, _parent;
	public BTNode(E element, BTPosition<E> parent, BTPosition<E> left, BTPosition<E> right)
	{
		setElement(element);
		setParent(parent);
		setLeft(left);
		setRight(right);
	}
	public E element()
	{
		return _element;
	}
	public BTPosition<E> getParent()
	{
		return _parent;
	}
	public BTPosition<E> getLeft()
	{
		return _left;
	}
	public BTPosition<E> getRight()
	{
		return _right;
	}
	
	public void setElement(E element)
	{
		_element = element;
	}
	public void setParent(BTPosition<E> parent)
	{
		_parent = parent;
	}
	public void setLeft(BTPosition<E> left)
	{
		_left = left;
	}
	public void setRight(BTPosition<E> right)
	{
		_right = right;
	}
}

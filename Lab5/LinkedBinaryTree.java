import java.util.Iterator;


public class LinkedBinaryTree<E> implements BinaryTree<E>
{
	protected BTPosition<E> _root;
	protected int _size;
	public LinkedBinaryTree()
	{
		_root = null;
		_size = 0;
	}
	public boolean isEmpty() {
		return (_size == 0);
	}
	public int size()
	{
		return _size;
	}
	
	protected BTPosition<E> checkPosition(Position<E> v) throws InvalidPositionException
	{
		if (v == null || !(v instanceof BTPosition))
		{
			throw new InvalidPositionException("The position is invalid");
		}
		return (BTPosition<E>) v;
	}
	
	public boolean isInternal(Position<E> v) throws InvalidPositionException
	{
		checkPosition(v);
		return (hasLeft(v) || hasRight(v));
	}
	public boolean isExternal(Position<E> v) throws InvalidPositionException
	{
		checkPosition(v);
		return !(hasLeft(v) || hasRight(v));
	}
	public boolean isRoot(Position<E> v) throws InvalidPositionException, EmptyTreeException
	{
		checkPosition(v);
		return (v == root());
	}
	public boolean hasLeft(Position<E> v) throws InvalidPositionException
	{
		BTPosition<E> vv = checkPosition(v);
		return (vv.getLeft() != null);
	}
	public boolean hasRight(Position<E> v) throws InvalidPositionException {
		BTPosition<E> vv = checkPosition(v);
		return (vv.getRight() != null);
	}
	public Position<E> root() throws EmptyTreeException {
		if (_root == null)
		{
			throw new EmptyTreeException("The tree is empty");
		}
		return _root;
	}
	public Position<E> left(Position<E> v) throws InvalidPositionException, BoundaryViolationException
	{
		BTPosition<E> vv = checkPosition(v);
		Position<E> leftPos = vv.getLeft();
		if (leftPos == null)
		{
			throw new BoundaryViolationException("No left child");
		}
		return leftPos;
	}
	public Position<E> right(Position<E> v) throws InvalidPositionException, BoundaryViolationException
	{
		BTPosition<E> vv = checkPosition(v);
		Position<E> rightPos = vv.getRight();
		if (rightPos == null)
		{
			throw new BoundaryViolationException("No right child");
		}
		return rightPos;
	}
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException
	{
		BTPosition<E> vv = checkPosition(v);
		Position<E> parentPos = vv.getParent();
		if (parentPos == null)
		{
			throw new BoundaryViolationException("No parent");
		}
		return parentPos;
	}
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException, BoundaryViolationException
	{
		PositionList<Position<E>> children = new NodePositionList<Position<E>>();
		if (hasLeft(v))
		{
			children.addLast(left(v));
		}
		if (hasRight(v))
		{
			children.addLast(right(v));
		}
		return children;
	}
	public Iterable<Position<E>> positions() throws InvalidPositionException, EmptyTreeException, BoundaryViolationException
	{
		PositionList<Position<E>> positions = new NodePositionList<Position<E>>();
		if (_size != 0)
		{
			preorderPositions(root(), positions);
		}
		return positions;
	}
	public Iterator<E> iterator() throws InvalidPositionException, EmptyTreeException, BoundaryViolationException
	{
		Iterable<Position<E>> positions = positions();
		PositionList<E> elements = new NodePositionList<E>();
		for (Position<E> pos: positions)
		{
			elements.addLast(pos.element());
		}
		return elements.iterator();
	}
	public E replace(Position<E> v, E o) throws InvalidPositionException
	{
		BTPosition<E> vv = checkPosition(v);
		E temp = v.element();
		vv.setElement(o);
		return temp;
	}
	public Position<E> sibling(Position<E> v) throws InvalidPositionException, BoundaryViolationException
	{
		BTPosition<E> vv = checkPosition(v);
		BTPosition<E> parentPos = vv.getParent();
		if (parentPos != null)
		{
			BTPosition<E> sibPos;
			BTPosition<E> leftPos = parentPos.getLeft();
			if (leftPos == vv)
			{
				
				sibPos = parentPos.getRight();
			}
			else
			{
				sibPos = parentPos.getLeft();
			}
			if (sibPos != null)
			{
				return sibPos;
			}
		}
		throw new BoundaryViolationException("No sibling");
	}
	public Position<E> addRoot(E e) throws NonEmptyTreeException
	{
		if (!isEmpty())
		{
			throw new NonEmptyTreeException("Tree already has a root");
		}
		_size = 1;
		_root = createNode(e, null, null, null);
		return _root;
	}
	public Position<E> insertLeft(Position<E> v, E e) throws InvalidPositionException
	{
		BTPosition<E> vv = checkPosition(v);
		Position<E> leftPos = vv.getLeft();
		if (leftPos != null)
		{
			throw new InvalidPositionException("Node already has a left child");
		}
		BTPosition<E> ww = createNode(e, vv, null, null);
		vv.setLeft(ww);
		_size++;
		return ww;
	}
	public E remove(Position<E> v) throws InvalidPositionException
	{
		BTPosition<E> vv = checkPosition(v);
		BTPosition<E> leftPos = vv.getLeft();
		BTPosition<E> rightPos = vv.getRight();
		if (leftPos != null && rightPos != null)
		{
			throw new InvalidPositionException("Cannot remove node with two children");
		}
		BTPosition<E> ww;
		if (leftPos != null)
		{
			ww = leftPos;
		}
		else if (rightPos != null)
		{
			ww = rightPos;
		}
		else
		{
			ww = null;
		}
		if (vv == _root)
		{
			if (ww != null)
			{
				ww.setParent(null);
			}
			_root = ww;
		}
		else
		{
			BTPosition<E> uu = vv.getParent();
			if (vv == uu.getLeft())
			{
				uu.setLeft(ww);
			}
			else
			{
				uu.setRight(ww);
			}
			if (ww != null)
			{
				ww.setParent(uu);
			}
		}
		_size--;
		return v.element();
	}
	public void attach(Position<E> v, BinaryTree<E> T1, BinaryTree<E> T2) throws InvalidPositionException, EmptyTreeException
	{
		BTPosition<E> vv = checkPosition(v);
		if (isInternal(v))
		{
			throw new InvalidPositionException("Cannot attach from internal node");
		}
		int newSize = _size + T1.size() + T2.size();
		if (!T1.isEmpty())
		{
			BTPosition<E> r1 = checkPosition(T1.root());
			vv.setLeft(r1);
			r1.setParent(vv);
		}
		if (!T2.isEmpty())
		{
			BTPosition<E> r2 = checkPosition(T2.root());
			vv.setRight(r2);
			r2.setParent(vv);
		}
		_size = newSize;
	}
	protected BTPosition<E> createNode(E element, BTPosition<E> parent, BTPosition<E> left, BTPosition<E> right)
	{
		return new BTNode<E>(element, parent, left, right);
	}
	protected void preorderPositions(Position<E> v, PositionList<Position<E>> pos) throws InvalidPositionException, BoundaryViolationException
	{
		pos.addLast(v);
		if (hasLeft(v))
		{
			preorderPositions(left(v), pos);
		}
		if (hasRight(v))
		{
			preorderPositions(right(v), pos);
		}
	}
}


public class Node {
	private char element;
	private Node next;
	public Node(char s, Node n){
		element = s;
		next = n;
	}
	
	public void append(Node newNode){
		next = newNode;
	}
	public void append(char nodeName){
		next = new Node(nodeName, null);
	}
	
	public char getElement(){
		return element;
	}
	public Node getNext(){
		return next;
	}
	public void setElement(char newElem){
		element = newElem;
	}
	public void setNext(Node newNext){
		next = newNext;
	}
}


public class SLinkedList {
	public Node head;
	public Node tail;
	public int size;
	
	public SLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}
	
	public void addToHead(Node newHead){
		if(head == null){
			tail = newHead;
		}else{
			newHead.setNext(head);
		}
		head = newHead;
		size++;
	}
	public Node removeFromHead(){
		Node oldHead = head;
		if(size == 1){
			head = null;
			tail = null;
		}else{
			head = head.getNext();
		}
		size--;
		return oldHead;
	}
	public void addToTail(Node newTail){
		newTail.setNext(null);
		if(tail == null){
			head = newTail;
		}else{
			tail.setNext(newTail);
		}
		tail = newTail;
		size++;
	}
	public Node removeFromTail(){
		Node oldTail = tail;
		if(size == 1){
			head = null;
			tail = null;
		}else{
			Node currentNode = head;
			for(int i=1; i<size-1; i++){
				currentNode = currentNode.getNext();
			}
			currentNode.setNext(null);
			tail = currentNode;
		}
		size--;
		return oldTail;
	}
	
	@Override
	public String toString(){
		String returnString = "";
		Node cNode = head;
		while(cNode != null){
			returnString += cNode.getElement();
			cNode = cNode.getNext();
		}
		return returnString;
	}
}

class Node {
	int data;
	Node next;
	Node(int d)
	{
		data =d;
		next = null;
	}
}
//linked list provided by classEx1
class LUCLinkedList{
	Node head;
	public void insert(int data){
		Node new_node = new Node(data);
		new_node.next = null;
		if (head == null){
			head = new_node;
		} else {
			Node last = head;
			while (last.next != null){
				last = last.next;
			}
			last.next = new_node;//insert the 
new_node at last node
		}
	}
	public int sumOf(){
		Node currNode = head;
		int sum = 0;
		while (currNode != null){
			sum+=currNode.data;
			currNode = currNode.next;
		}
		return sum;
	}
	public int maxData(){
		Node currNode = head;
		int max = currNode.data;
		while (currNode != null){
			if (currNode.data > max){
				max = currNode.data;
			}
			currNode = currNode.next;	
		}
	}
	public void evenIndex(){
		Node currNode = head;
		while(currNode != null){
			System.out.println(currNode.data);
			currNode = currNode.next.next;
		}
	}
	public void removeElement(int element){
		Node prevNode = head;
		Node currNode = head;
		while (currNode.data != null){
			if (currNode.data == element){
				prevNode.next = currNode.next;
				currNode.next = null;
			}
			currNode = currNode.next;
			if (prevNode.next != currNode){
				prevNode = prevNode.next;	
			}
		}
	}	
	public void printList(){
		Node currNode = head;
		System.out.print("LinkedList: ");
		while (currNode != null){
			System.out.print(currNode.data + " ");
			currNode = currNode.next;
		}
	}
}
public class LUCLinkedListMain{
	public static void main(String[] args){
		LUCLinkedList list = new LUCLinkedList();
		list.insert(2);
		list.insert(5);
		list.insert(1);
		list.insert(6);
		list.insert(3);
		list.insert(9);
		list.printList();
	}
}

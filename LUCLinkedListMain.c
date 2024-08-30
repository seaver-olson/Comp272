#include <stdio.h>
#include "LUCLinkedListMain.h"

struct LinkedList{
	Node head;
	void insert(ind data){
		Node new_node;
		new_node.data = data;
		if (head == NULL){
			head = new_node();
		} else {
			Node last = head;
			while(last.next != NULL){
				last = last.next;
			}
			last.next = new_node;
		}
	}
	int sumOf(){
		Node currNode = head;
		int sum = 0;
		while (currNode != NULL){
			sum+=currNode.val;
			currNode = currNode.next
		}	
		return sum;
	}
	int maxData(){
		Node currNode = head;
		int max = currNode.val;
		while (currNode != null){
			if (currNode.val > max){
				max = currNode.val;
			} 
			currNode = currNode.next;	
		}
	}
	void evenIndex(){
		Node currNode = head;
		while(currNode != NULL){
			printf("%d", currNode.val);
			currNode = currNode.next.next;
		}
	}
	void removeElement(int element){
		
	}
}

int main(){

}

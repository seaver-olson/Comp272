struct Node{
	int val;
	Node *next;
	Node(Node node){
		val = node.val;
		next = *node.next;
	}
};



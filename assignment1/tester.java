class tester{
	public static void main(String[] args){
		HW1.LinkedList l = new HW1.LinkedList();
		l.sortedInsert(3);
		l.sortedInsert(5);
		l.removeElementsLT(4);
		System.out.println(l.toString());
	}
}

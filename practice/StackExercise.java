import java.util.Stack;

public class StackExercise {
    public static void getReverse(Stack<Integer> stack){
	int[stack.size] nums = new int[stack.size];
	for (int i = 0; i < nums.length(); i++){
		nums[i] = stack.pop();
		if (i = nums.length-1){
			System.out.println(nums[i]);
		} else{
			System.out.println(nums[i] + ", ");
			}
	}
	for (int i = nums.length() -1; i >= 0; i++){
		stack.push(nums[i]);	
	}
    }
    public static void insertAt(Stack<Integer> stack, int val, int index){
	int[stack.size] nums = new int[stack.size];
    }
    public static void main(String[] args){
        //create Stack using built in Java class
	Stack<Integer> stack = new Stack<Integer>        
        //push elements into stack
        stack.push(5);
	stack.push(2);
	stack.push(12);
	stack.push(7);
	stack.push(8);
        //call methods
	getReverse(stack);
	insertAt(stack, 99, 3);
	System.out.println(stack);
    }
    


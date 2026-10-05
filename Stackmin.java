
import java.util.ArrayDeque;
import java.util.Deque;

class Stackk {
	private int[] arr;                 // Array for main stack
	private int top;                   // Index of top element
	private int size;                  // Capacity of array
	private Deque<Integer> minStack;   // Auxiliary stack to track minimums - O(1) getMin
	
	Stackk(int capacity){
		this.size = capacity;
		this.arr = new int[capacity];
		this.top = -1;
		this.minStack = new ArrayDeque<>();
	}
	
	// Push element - O(1)
	public void push(int ele) {
		if(isfull()) {
			System.out.println("Stack Overflow! Cannot push " + ele);
			return;
		}
		
		arr[++top] = ele;
		
		// Push to minStack: either the new element or maintain current min
		if(minStack.isEmpty()) {
			minStack.push(ele);
		} else {
			minStack.push(Math.min(ele, minStack.peek()));
		}
	}
	
	// Pop element - O(1)
	public int pop() {
		if(isempty()) {
			System.out.println("Stack Underflow!");
			return -1;
		}
		minStack.pop();  // Remove corresponding min
		return arr[top--];
	}
	
	public boolean isfull() {
		return top == size - 1;  // Array is full
	}
	
	public boolean isempty() {
		return top == -1;
	}
	
	// Peek top element - O(1)
	public int peek() {
		if(isempty()) {
			System.out.println("Stack is empty!");
			return -1;
		}
		return arr[top];
	}
	
	// Get minimum element - O(1)
	public int minele() {
		if(minStack.isEmpty()) {
			System.out.println("Stack is empty!");
			return -1;
		}
		return minStack.peek();
	}
	
	public void display() {
		System.out.print("Stack elements (top to bottom): ");
		for(int i = top; i >= 0; i--) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		System.out.println("Current minimum: " + (minStack.isEmpty() ? "N/A" : minStack.peek()));
	}

}

public class Stackmin {

	public static void main(String[] args) {
		Stackk st = new Stackk(5);  // Array size = 5
		
		// Test case: push elements and track minimum
		st.push(10);
		System.out.println("After push(10): min = " + st.minele());  // min = 10
		
		st.push(20);
		System.out.println("After push(20): min = " + st.minele());  // min = 10
		
		st.push(5);
		System.out.println("After push(5): min = " + st.minele());   // min = 5
		
		st.push(30);
		System.out.println("After push(30): min = " + st.minele());  // min = 5
		
		st.push(2);
		System.out.println("After push(2): min = " + st.minele());   // min = 2
		
		// This will trigger Stack Overflow (isfull check)
		st.push(100);  
		
		System.out.println("\nPopping...");
		st.pop();  // Remove 2
		System.out.println("After pop(): min = " + st.minele());     // min = 5
		
		st.pop();  // Remove 30
		System.out.println("After pop(): min = " + st.minele());     // min = 5
		
		st.pop();  // Remove 5 - THIS RESTORES MIN TO 10!
		System.out.println("After pop(): min = " + st.minele());     // min = 10
		
		st.display();
	}

}

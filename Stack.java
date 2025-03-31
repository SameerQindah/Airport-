package application;


public class Stack {

	private int size; // number of elements in the stack
	private StackNode Front; // pointer to the top node

	public Stack() {
//empty stack
		Front = null;
		size = 0;
	}

	// Adds a new element to the top of the stack
	public void push(Object element) {

		StackNode newNode;
		newNode = new StackNode(element);

		newNode.next = Front;
		Front = newNode;

		size++;// update size
	}

	// Removes and returns the stack's top element
	public Object pop() {

		if (!isEmpty()) {
			StackNode top = Front; // save reference
			Front = Front.next;// Remove first node
			size--;
			return top.element;// Return the element from the saved ref
		} else
			return null;

	}

	public Object peek() {
		// Return the top element without changing the stack
		if (!isEmpty())
			return Front.element;
		else
			return null;
	}

	public int Size() {
		return size;
	}

	public boolean isEmpty() {
		return (Front == null); // return true if the stack is empty
	}
	
	/*
	 * public void printList() { if (isEmpty()) {
	 * System.out.println("The stack is empty."); return; }
	 * 
	 * StackNode current = Front; // Start from the top of the stack
	 * 
	 * // Traverse the stack from top to bottom and print each element
	 * System.out.println("Stack elements:");
	 * 
	 * while (current != null) { System.out.println(current.element); // Print the
	 * element of the current node current = current.next; // Move to the next node
	 * } }
	 */

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public StackNode getFront() {
		return Front;
	}

	public void setFront(StackNode front) {
		Front = front;
	}

}
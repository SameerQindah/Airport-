package application;

public class StackNode {

	public Object element;
	public StackNode next;

	public StackNode(Object element) {
		this(element, null);
	}

	public StackNode(Object element, StackNode next) {
		this.element = element;
		this.next = next;
	}
}
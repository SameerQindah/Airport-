package application;

public class SNode {
	// Variables
	private Object element;
	private SNode next;

	// constructor
	public SNode(Object element) {
		this(element, null);
	}

	// constructor
	public SNode(Object element, SNode next) {
		this.element = element;
		this.next = next;
	}

	// getter and setter
	public Object getElement() {
		return element;
	}

	public void setElement(Object element) {
		this.element = element;
	}

	public SNode getNext() {
		return next;
	}

	public void setNext(SNode next) {
		this.next = next;
	}

}
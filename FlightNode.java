package application;

public class FlightNode {
	private Object element;
	private FlightNode prev, next;
	private SingelList boardedPassengers;
	private SingelList CanceledPassengers;
	private Queue VIPpassengerQueue;
	private Queue RegularpassengerQueue;
	private Stack Undo;
	private Stack Redo;
	
	public Object getElement() {
		return element;
	}

	public void setElement(Object element) {
		this.element = element;
	}

	public FlightNode getPrev() {
		return prev;
	}

	public void setPrev(FlightNode prev) {
		this.prev = prev;
	}

	public FlightNode getNext() {
		return next;
	}

	public void setNext(FlightNode next) {
		this.next = next;
	}

	public FlightNode(Object element) {

		this(element, null, null);

	}

	public FlightNode(Object element, FlightNode prev, FlightNode next) {
		this.element = element;
		this.prev = prev;
		this.next = next;
		boardedPassengers = new SingelList();
		CanceledPassengers = new SingelList();
		VIPpassengerQueue = new Queue();
		RegularpassengerQueue = new Queue();
		Undo = new Stack();
		Redo = new Stack();
	}

	public SingelList getBoardedPassengers() {
		return boardedPassengers;
	}

	public void setBoardedPassengers(SingelList boardedPassengers) {
		this.boardedPassengers = boardedPassengers;
	}

	public SingelList getCanceledPassengers() {
		return CanceledPassengers;
	}

	public void setCanceledPassengers(SingelList canceledPassengers) {
		CanceledPassengers = canceledPassengers;
	}

	public Queue getVIPpassengerQueue() {
		return VIPpassengerQueue;
	}

	public void setVIPpassengerQueue(Queue vIPpassengerQueue) {
		VIPpassengerQueue = vIPpassengerQueue;
	}

	public Queue getRegularpassengerQueue() {
		return RegularpassengerQueue;
	}

	public void setRegularpassengerQueue(Queue regularpassengerQueue) {
		RegularpassengerQueue = regularpassengerQueue;
	}

	public Stack getUndo() {
		return Undo;
	}

	public void setUndo(Stack undo) {
		Undo = undo;
	}

	public Stack getRedo() {
		return Redo;
	}

	public void setRedo(Stack redo) {
		Redo = redo;
	}
	
	
	
	
}

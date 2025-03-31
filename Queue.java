package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Queue {
	private NodeQueue first, last;
	private int count = 0; // Corrected: count is now an instance variable

	// Peek the first element in the queue
	public Object peek() {
		if (first == null)
			return null;
		return first.getElement();
	}

	// Enqueue an element
	public void enQueue(Object data) {
		NodeQueue newNode = new NodeQueue(data);
		if (last == null) {
			first = last = newNode;
		} else {
			last.setNext(newNode);
			last = newNode;
		}
		count++; // Increment the size counter
	}

	// Dequeue an element
	public Object deQueue() {
		if (isEmpty())
			return null;
		NodeQueue temp = first;
		if (first == last) {
			// If there is only one element in the queue
			first = last = null;
		} else {
			first = first.getNext();
			temp.setNext(null); // Disconnect the node to be dequeued
		}
		count--; // Decrement the size counter
		return temp.getElement();
	}

	// Check if the queue is empty
	public boolean isEmpty() {
		return first == null;
	}

	// Get the size of the queue
	public int size() {
		return count;
	}

	// Convert the queue to an ObservableList of Passenger objects
	public ObservableList<Passenger> toObservableList() {
		ObservableList<Passenger> observablePassengers = FXCollections.observableArrayList();
		NodeQueue current = first; // Start from the first node in the queue

		// Traverse the queue and add elements to the ObservableList
		while (current != null) {
			Object key = current.getElement();
			if (key instanceof Passenger) { // Ensure the object is of type Passenger
				observablePassengers.add((Passenger) key);
			}
			current = current.getNext(); // Move to the next node
		}

		return observablePassengers; // Return the ObservableList
	}
}

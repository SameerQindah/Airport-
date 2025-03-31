package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FlightList {
	private FlightNode front, back;
	private int size;

	public FlightList() {
		front = null;
		back = null;
		size = 0;
	}

	public void addFirst(Object element) {
		FlightNode newNode = new FlightNode(element);
		if (size == 0) {
			front = back = newNode;
		} else {
			newNode.setNext(front);
			front.setPrev(newNode);
			front = newNode;
		}
		size++;
	}

	public void addLast(Object element) {
		FlightNode newNode = new FlightNode(element);
		if (size == 0) {
			front = back = newNode;
		} else {
			newNode.setPrev(back);
			back.setNext(newNode);
			back = newNode;
		}
		size++;
	}

	public Object getFirst() {
		if (size == 0) {
			return null;
		} else {
			return front.getElement();
		}
	}

	public Object getLast() {
		if (size == 0) {
			return null;
		}
		return back.getElement();
	}

	private void printList(FlightNode current) {
		current = front;
		while (current != null) {
			System.out.println(current.getElement() + " ");
			current.setNext(current);
		}
	}

	public void printList() {
		printList(front);
	}

	public boolean removeFirst() {
		if (size == 0) {
			return false;
		} else if (size == 1) {
			front = back = null;
			return true;
		} else {
			front = front.getNext();
			return true;
		}
	}

	public boolean removeLast() {
		if (size == 0) {
			return false;
		} else if (size == 1) {
			front = back = null;
			return true;
		} else {
			back = back.getPrev();
			return true;
		}
	}

	public Object get(int index) {
		FlightNode node = front;
		int x = 0;
		while (node != null) {
			if (x == index) {
				return node.getElement();
			}
			x++;
			node.setNext(node);
		}
		return back;
	}

	public int getSize() {
		return size;
	}

	public FlightNode getFront() {
		return front;
	}

	public void setFront(FlightNode front) {
		this.front = front;
	}

	public FlightNode getBack() {
		return back;
	}

	public void setBack(FlightNode back) {
		this.back = back;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	// Insert a major in sorted order based on name
		public void insertSortFlight(Flight element) {
			FlightNode newNode = new FlightNode(element);

			// Case 1: If the list is empty, add the element as the first node
			if (size == 0) {
				addFirst(element);
			}
			// Case 2: Insert at the beginning if the new element's name comes before the
			// first element's name
			else if (((Flight) getFirst()).compareTo(element) > 0) {
				addFirst(element);
			}
			// Case 3: Insert at the end if the new element's name comes after the last
			// element's name
			else if (((Flight) getLast()).compareTo(element) < 0) {
				addLast(element);
			}
			// Case 4: Insert in the correct alphabetical position within the sorted list
			else {
				FlightNode current = front;
				while (current != null) {
					Flight currentMajor = (Flight) current.getElement();
					// If currentMajor comes after the new element alphabetically, insert before it
					if (currentMajor.compareTo(element) > 0) {
						newNode.setNext(current);
						newNode.setPrev(current.getPrev());

						if (current.getPrev() != null) {
							current.getPrev().setNext(newNode);
						}
						current.setPrev(newNode);

						// Update front if the new node is now the first node
						if (current == front) {
							front = newNode;
						}

						size++; // Increment size for the new node
						break;
					}
					current = current.getNext();
				}
			}
		}
		// Convert the list of majors to an ObservableList
		public ObservableList<Flight> toObservableList() {
			ObservableList<Flight> observableMajors = FXCollections.observableArrayList();
			FlightNode current = front;
			while (current != null) {
				observableMajors.add((Flight) current.getElement());
				current = current.getNext();
			}
			return observableMajors;
		}
		
		// Search for a major by name and return it
		public Flight search(int Id) {
			FlightNode current = front;
			while (current != null) {
				Flight flight = (Flight) current.getElement();
				if (flight.getFlightID() == Id) {
					return flight;
				}
				current = current.getNext();
			}
			return null;
		}
		
		// Update the details of a specific major
		public boolean update(int currentId, String Destination, String Status) {
			// Search for the major by the current name
			Flight flight = search(currentId);

			if (flight != null) { // If the major is found

				// Update major information
				flight.setFlightID(currentId); // Update name
				flight.setDestination(Destination);
				flight.setStatus(Status);

				return true; // Return true to indicate the update was successful
			}

			return false; // Return false if the major was not found
		}
		
		// Remove a major by its name
		public boolean remove(int Id) {
			if (size == 0) {
				return false; // List is empty, nothing to remove
			}

			FlightNode current = front;

			// Traverse the list to find the node with the matching name
			while (current != null) {
				Flight flight = (Flight) current.getElement();

				if (flight.getFlightID() == Id) {
					// Node to be removed is the front node
					if (current == front) {
						front = current.getNext();
						if (front != null) {
							front.setPrev(null);
						} else {
							back = null; // List is now empty
						}
					}
					
					// Node to be removed is the back node
					else if (current == back) {
						back = current.getPrev();
						if (back != null) {
							back.setNext(null);
						}
					}
					// Node to be removed is in the middle of the list
					else {
						current.getPrev().setNext(current.getNext());
						current.getNext().setPrev(current.getPrev());
					}

					size--;
					return true; // Node successfully removed
				}

				current = current.getNext();
			}

			// No matching node was found
			return false;
		}
		
		public Flight removeAndReturn(int Id) {
			if (size == 0) {
				return null; // List is empty, nothing to remove
			}

			FlightNode current = front;

			// Traverse the list to find the node with the matching name
			while (current != null) {
				Flight flight = (Flight) current.getElement();

				if (flight.getFlightID() == Id) {
					// Node to be removed is the front node
					if (current == front) {
						front = current.getNext();
						if (front != null) {
							front.setPrev(null);
						} else {
							back = null; // List is now empty
						}
					}
					
					// Node to be removed is the back node
					else if (current == back) {
						back = current.getPrev();
						if (back != null) {
							back.setNext(null);
						}
					}
					// Node to be removed is in the middle of the list
					else {
						current.getPrev().setNext(current.getNext());
						current.getNext().setPrev(current.getPrev());
					}
					size--;
					return flight;
					
				}

				current = current.getNext();
			}
			return null;
		}
		
		// Search for a major by name and return it
		public Flight search(String destination) {
			FlightNode current = front;
			while (current != null) {
				Flight major = (Flight) current.getElement();
				if (major.getDestination().equalsIgnoreCase(destination)) {
					return major;
				}
				current = current.getNext();
			}
			return null;
		}


}

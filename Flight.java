package application;

public class Flight implements Comparable<Flight> {
	private int FlightID;
	private String Destination;
	private String Status;

	public Flight(int flightID, String destination, String status) {
		super();
		FlightID = flightID;
		Destination = destination;
		Status = status;
	}

	public int getFlightID() {
		return FlightID;
	}

	public void setFlightID(int flightID) {
		FlightID = flightID;
	}

	public String getDestination() {
		return Destination;
	}

	public void setDestination(String destination) {
		Destination = destination;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	@Override
	public int compareTo(Flight o) {
		if (this.FlightID > o.FlightID) {
			return 1; // Current flight has a higher ID
		} else if (this.FlightID < o.FlightID) {
			return -1; // Current flight has a lower ID
		} else {
			return 0; // Both flights have the same ID
		}
	}

}

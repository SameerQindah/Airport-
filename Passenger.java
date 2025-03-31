package application;

public class Passenger implements Comparable<Passenger>{
	private int PassengerID;
	private String Name;
	private int FlightID;
	private String Status;

	public Passenger(int passengerID, String name, int flightID, String status) {
		super();
		PassengerID = passengerID;
		Name = name;
		FlightID = flightID;
		Status = status;
	}

	public int getPassengerID() {
		return PassengerID;
	}

	public void setPassengerID(int passengerID) {
		PassengerID = passengerID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getFlightID() {
		return FlightID;
	}

	public void setFlightID(int flightID) {
		FlightID = flightID;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	@Override
	public int compareTo(Passenger o) {
		// TODO Auto-generated method stub
		return 0;
	}

}

package application;

public class LogFile {
	private String timestamp; 
	private String operation; 
	private String PassengerName;
	private String flightID;
	private String description;

	public LogFile(String timestamp, String operation, String passengerName, String flight, String description) {
		this.timestamp = timestamp;
		this.operation = operation;
		this.PassengerName = passengerName;
		this.flightID = flight;
		this.description = description;
	}
	
	

	public String getTimestamp() {
		return timestamp;
	}



	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}



	public String getOperation() {
		return operation;
	}



	public void setOperation(String operation) {
		this.operation = operation;
	}



	public String getPassengerName() {
		return PassengerName;
	}



	public void setPassengerName(String passengerName) {
		PassengerName = passengerName;
	}



	public String getFlightID() {
		return flightID;
	}



	public void setFlightID(String flightID) {
		this.flightID = flightID;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	@Override
	public String toString() {
		return timestamp + " | " + operation + " | " + PassengerName + " | " + flightID + " | " + description;
	}
}
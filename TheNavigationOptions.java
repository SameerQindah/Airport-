package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TheNavigationOptions {
    // Navigation buttons for next and previous flights
    private Button nextButton;
    private Button PreviousButton;

    // Fields to display flight information
    private TextField DestinationFeild;
    private TextField FlightIDFeild;
    private TextField StatusFeild;

    // Track the current flight index
    private int current = 0;

    // Lists to hold all passengers and filtered passengers for the current flight
    private ObservableList<Passenger> Passengers = FXCollections.observableArrayList();
    private ObservableList<Passenger> filteredPassenger = FXCollections.observableArrayList();

    // Table to display passengers
    private TableView<Passenger> studentTable;

    // Constructor
    public TheNavigationOptions() {
        // Initialize the text fields
        DestinationFeild = new TextField();
        DestinationFeild.setEditable(false); // Make it read-only

        FlightIDFeild = new TextField();
        FlightIDFeild.setEditable(false); // Make it read-only

        StatusFeild = new TextField();
        StatusFeild.setEditable(false); // Make it read-only

        // Initialize the table for displaying passengers
        studentTable = new TableView<>(filteredPassenger);
        createStudentTable();

        // Initialize navigation buttons
        nextButton = new Button("Next");
        PreviousButton = new Button("Previous");
    }

    // Method to create the main layout for displaying flights
    public HBox displayflights() {
        VBox flightDetailsBox = new VBox(10);

        // Labels and fields for flight details
        Label destinationLabel = new Label("Flight Destination:");
        Label flightIDLabel = new Label("Flight ID:");
        Label statusLabel = new Label("Status:");

        // Grid for flight details layout
        GridPane flightDetailsGrid = new GridPane();
        flightDetailsGrid.setHgap(10);
        flightDetailsGrid.setVgap(10);
        flightDetailsGrid.add(destinationLabel, 0, 0);
        flightDetailsGrid.add(DestinationFeild, 1, 0);
        flightDetailsGrid.add(flightIDLabel, 0, 1);
        flightDetailsGrid.add(FlightIDFeild, 1, 1);
        flightDetailsGrid.add(statusLabel, 0, 2);
        flightDetailsGrid.add(StatusFeild, 1, 2);
        flightDetailsGrid.setAlignment(Pos.CENTER);

        // Navigation buttons layout
        HBox navigationButtons = new HBox(30, PreviousButton, nextButton);
        navigationButtons.setAlignment(Pos.CENTER);

        flightDetailsBox.getChildren().addAll(flightDetailsGrid, navigationButtons);

        // Combine flight details and passenger table
        HBox displayFlights = new HBox(30);
        displayFlights.getChildren().addAll(flightDetailsBox, studentTable);

        return displayFlights;
    }

    // Method to load and display the list of flights
    public void openFile(ObservableList<Flight> flights) {
        if (flights == null || flights.isEmpty()) {
            // If the list is empty, clear fields and show a message
            DestinationFeild.setText("No Flights available");
            FlightIDFeild.clear();
            StatusFeild.clear();
            filteredPassenger.clear(); // Clear passengers associated with flights
            return;
        }

        // Reset the current index and display the first flight
        current = 0;
        displayFlight(flights.get(current));

        // Add navigation functionality
        nextButton.setOnAction(e -> {
            if (current < flights.size() - 1) {
                current++;
                displayFlight(flights.get(current));
                filterPassengersByFlight(); // Update filtered passengers
            }
        });

        PreviousButton.setOnAction(e -> {
            if (current > 0) {
                current--;
                displayFlight(flights.get(current));
                filterPassengersByFlight(); // Update filtered passengers
            }
        });
    }

    // Method to display details of the given flight
    private void displayFlight(Flight flight) {
        // Display flight details
        DestinationFeild.setText(flight.getDestination());
        FlightIDFeild.setText(String.valueOf(flight.getFlightID()));
        StatusFeild.setText(flight.getStatus());

        // Update passenger table to show passengers associated with this flight
        filterPassengersByFlight();
    }

    // Method to filter passengers who belong to the current flight
    private void filterPassengersByFlight() {
        filteredPassenger.clear(); // Clear previously filtered passengers

        try {
            // Get the current flight ID from the FlightIDFeild
            int currentFlightID = Integer.parseInt(FlightIDFeild.getText());

            // Filter passengers whose FlightID matches the current flight ID
            for (Passenger passenger : Passengers) {
                if (passenger.getFlightID() == currentFlightID) {
                    filteredPassenger.add(passenger);
                }
            }
        } catch (NumberFormatException e) {
            // Handle cases where FlightIDFeild is empty or invalid
            System.out.println("Error: Invalid Flight ID format.");
        }

        // Refresh the table view to display the filtered list
        studentTable.refresh();
    }

    // Method to set up the columns in the passenger table
    private void createStudentTable() {
        // Passenger ID column
        TableColumn<Passenger, String> passengerIdColumn = new TableColumn<>("Passenger ID");
        passengerIdColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getPassengerID())));

        // Passenger Name column
        TableColumn<Passenger, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

        // Flight ID column
        TableColumn<Passenger, String> flightIdColumn = new TableColumn<>("Flight ID");
        flightIdColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getFlightID())));
        flightIdColumn.setMinWidth(150);

        // Status column
        TableColumn<Passenger, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));
        statusColumn.setMinWidth(150);

        // Add columns to the table
        studentTable.getColumns().addAll(passengerIdColumn, nameColumn, flightIdColumn, statusColumn);
    }

    // Method to set the full list of passengers
    public void setpassengers(ObservableList<Passenger> allPassengers) {
        this.Passengers = allPassengers;
    }
}

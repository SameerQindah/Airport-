package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class Main extends Application {
	TheNavigationOptions options = new TheNavigationOptions();

	private ObservableList<Flight> flights = FXCollections.observableArrayList();
	private ObservableList<Flight> flightsActive = FXCollections.observableArrayList();
	private ObservableList<Flight> flightsNotActive = FXCollections.observableArrayList();
	private ObservableList<LogFile> LogFiles = FXCollections.observableArrayList();

	private FlightList flightList = new FlightList();
	private SingelList boardedPassengers = new SingelList();
	private SingelList CanceledPassengers = new SingelList();

	private ObservableList<Passenger> passengersNotCheckIn = FXCollections.observableArrayList();
	private ObservableList<Passenger> passengersCheckIn = FXCollections.observableArrayList();
	private Queue VIPpassengerQueue = new Queue();
	private Queue RegularpassengerQueue = new Queue();
	private SingelList AllPassenger = new SingelList();

	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			BackgroundImage b = new BackgroundImage(new Image("Airport.jpg"), null, null, null, null);
			root.setBackground(new Background(b));

			MenuBar bar = new MenuBar();
			Menu menuPassengerManagement = new Menu("Passenger Management");

			MenuItem itemAddPassenger = new MenuItem("Add Passenger");
			menuPassengerManagement.getItems().add(itemAddPassenger);
			itemAddPassenger.setOnAction(e -> {
				root.setLeft(createAddPassenger());
			});
			MenuItem itemUpdatePassenger = new MenuItem("Update Passenger");
			menuPassengerManagement.getItems().add(itemUpdatePassenger);
			itemUpdatePassenger.setOnAction(e -> {
				root.setLeft(createUpdatePassenger());
			});
			MenuItem itemRemovePassenger = new MenuItem("Remove Passenger");
			menuPassengerManagement.getItems().add(itemRemovePassenger);
			itemRemovePassenger.setOnAction(e -> {
				root.setLeft(createRemovePassenger());
			});
			MenuItem itemSearchPassenger = new MenuItem("Search Passenger");
			menuPassengerManagement.getItems().add(itemSearchPassenger);
			itemSearchPassenger.setOnAction(e -> {
				root.setLeft(createSearchPassenger());
			});
			MenuItem itemPrintAllPassengers = new MenuItem("Print All Passengers");
			menuPassengerManagement.getItems().add(itemPrintAllPassengers);
			itemPrintAllPassengers.setOnAction(e -> {
				root.setLeft(createPrintAllPassengers());
			});
			MenuItem itemPrintSpecificPassengerInfo = new MenuItem("Print Specific Passenger Info");
			menuPassengerManagement.getItems().add(itemPrintSpecificPassengerInfo);
			itemPrintSpecificPassengerInfo.setOnAction(e -> {
				root.setLeft(createPrintSpecificPassengerInfo());
			});

			Menu menuFlightManagement = new Menu("Flight Management");

			MenuItem itemAddFlight = new MenuItem("Add Flight");
			menuFlightManagement.getItems().add(itemAddFlight);
			itemAddFlight.setOnAction(e -> {
				root.setLeft(createAddFlight());
			});
			MenuItem itemUpdateFlight = new MenuItem("Update Flight");
			menuFlightManagement.getItems().add(itemUpdateFlight);
			itemUpdateFlight.setOnAction(e -> {
				root.setLeft(createUpdateFlight());
			});
			MenuItem itemRemoveFlight = new MenuItem("Remove Flight");
			menuFlightManagement.getItems().add(itemRemoveFlight);
			itemRemoveFlight.setOnAction(e -> {
				root.setLeft(createRemoveFlight());
			});
			MenuItem itemSearchFlight = new MenuItem("Search Flight");
			menuFlightManagement.getItems().add(itemSearchFlight);
			itemSearchFlight.setOnAction(e -> {
				root.setLeft(createSearchFlight());
			});
			MenuItem itemPrintAllFlights = new MenuItem("Print All Flights");
			menuFlightManagement.getItems().add(itemPrintAllFlights);
			itemPrintAllFlights.setOnAction(e -> {
				root.setLeft(createPrintAllFlights());
			});
			MenuItem itemPrintSpecificFlightInfo = new MenuItem("Print Specific Flight Info");
			menuFlightManagement.getItems().add(itemPrintSpecificFlightInfo);
			itemPrintSpecificFlightInfo.setOnAction(e -> {
				root.setLeft(createPrintSpecificFlightInfo());
			});
			MenuItem itemDisplayAllActiveFlightsAndTheirDestinations = new MenuItem(
					"Display all active flights and their destinations");
			menuFlightManagement.getItems().add(itemDisplayAllActiveFlightsAndTheirDestinations);
			itemDisplayAllActiveFlightsAndTheirDestinations.setOnAction(e -> {
				root.setLeft(createDisplayallactiveflights());
			});
			MenuItem itemDisplayAllInactiveFlights = new MenuItem("Display all inactive flights");
			menuFlightManagement.getItems().add(itemDisplayAllInactiveFlights);
			itemDisplayAllInactiveFlights.setOnAction(e -> {
				root.setLeft(createDisplayallinactiveflights());
			});
			MenuItem itemIntegratingTheNavigationOptions = new MenuItem("Integrating the Navigation Options");
			menuFlightManagement.getItems().add(itemIntegratingTheNavigationOptions);
			itemIntegratingTheNavigationOptions.setOnAction(e -> {
				root.setLeft(options.displayflights());
			});

			Menu menuOperation = new Menu("Operation");

			MenuItem itemCheckInPassenger = new MenuItem("Check-in Passenger");
			menuOperation.getItems().add(itemCheckInPassenger);
			itemCheckInPassenger.setOnAction(e -> {
				root.setLeft(CheckInPassenger());
			});
			MenuItem itemBoardPassenger = new MenuItem("Board Passenger");
			menuOperation.getItems().add(itemBoardPassenger);
			itemBoardPassenger.setOnAction(e -> {
				root.setLeft(BoardPassenger());
			});
			MenuItem itemCancelPassenger = new MenuItem("Cancel Passenger");
			menuOperation.getItems().add(itemCancelPassenger);
			itemCancelPassenger.setOnAction(e -> {
				root.setLeft(CancelPassenger());
			});
			MenuItem itemUndo = new MenuItem("Undo");
			menuOperation.getItems().add(itemUndo);
			itemUndo.setOnAction(e -> {

			});
			MenuItem itemRedo = new MenuItem("Redo");
			menuOperation.getItems().add(itemRedo);
			itemRedo.setOnAction(e -> {

			});

			Menu menuLogFile = new Menu("Log File");

			MenuItem itemPrintLogFile = new MenuItem("Print Log File");
			menuLogFile.getItems().add(itemPrintLogFile);
			itemPrintLogFile.setOnAction(e -> {
				root.setLeft(PrintLogFile());
			});
			MenuItem itemExportLogFile = new MenuItem("Export Log File");
			menuLogFile.getItems().add(itemExportLogFile);
			itemExportLogFile.setOnAction(e -> {
				exportLogToFile();
			});

			Menu menuStatistical = new Menu("Statistical");

			MenuItem itemDisplayStatistical = new MenuItem("Display Statistical");
			menuStatistical.getItems().add(itemDisplayStatistical);
			itemDisplayStatistical.setOnAction(e -> {
				root.setLeft(DisplayStatistical());
			});

			Menu menuFile = new Menu("File");
			MenuItem itemOpenFile = new MenuItem("Open File");
			menuFile.getItems().add(itemOpenFile);
			itemOpenFile.setOnAction(e -> {
				FileChooser chooser = new FileChooser();
				File file = chooser.showOpenDialog(primaryStage);
				if (file != null) {
					readFlight(file);
					readPassenger(file);

					options.openFile(flights);
					options.setpassengers(passengersNotCheckIn);

				}
			});

			bar.getMenus().addAll(menuPassengerManagement, menuFlightManagement, menuOperation, menuLogFile,
					menuStatistical, menuFile);

			root.setTop(bar);

			primaryStage.setTitle("Airport System");
			Scene scene = new Scene(root, 800, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void readFlight(File file) {
		try (Scanner in = new Scanner(file)) {

			while (in.hasNextLine()) {
				String line = in.nextLine();

				if (line.isEmpty())
					continue; // Skip empty lines

				String[] splitLine = line.split(",");

				if (splitLine.length < 3)
					continue;

				try {
					int flightID = Integer.parseInt(splitLine[0].trim());
					String destination = splitLine[1].trim();
					String status = splitLine[2].trim();

					if (destination.matches(".*\\d.*") || status.matches(".*\\d.*")) {
						// Skip invalid destinations or statuses with numbers
						continue;
					}
					if (!status.equalsIgnoreCase("Active") && !status.equalsIgnoreCase("Not Active")
							&& !status.equalsIgnoreCase("In Active")) {
						continue;
					}

					// Create a new Flight object
					Flight flight = new Flight(flightID, destination, status);

					// Add to the main flight list
					flightList.insertSortFlight(flight);

					// Add to active or inactive lists based on status
					if (status.equalsIgnoreCase("Active")) {
						flightsActive.add(flight);
					} else if (status.equalsIgnoreCase("Not Active")) {
						flightsNotActive.add(flight);
					}

				} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
					// Handle parsing errors and skip invalid lines
					continue;
				}
			}

			// Update the main flights list after processing
			flights.setAll(flightList.toObservableList());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readPassenger(File file) {
		try (Scanner in = new Scanner(file)) {
			while (in.hasNextLine()) {
				String line = in.nextLine().trim(); // Read and trim the line

				if (line.isEmpty())
					continue; // Skip empty lines

				String[] splitLine = line.split(","); // Split by comma
				if (splitLine.length < 4) {
					continue; // Skip invalid lines
				}

				try {
					// Parse the data
					int passengerID = Integer.parseInt(splitLine[0].trim());
					String name = splitLine[1].trim();
					int flightID = Integer.parseInt(splitLine[2].trim());
					String status = splitLine[3].trim();

					// Validate input data
					if (name.matches(".*\\d.*") || status.matches(".*\\d.*")) {
						continue; // Skip if name or status contains numbers
					}
					if (flightList.search(flightID) == null) {
						// showAlert("Error", "The ID can not be frequent ");
						continue;
					}
					if (AllPassenger.search(passengerID) != null) {
						// showAlert("Error", "The ID can not be frequent ");
						continue;
					}
					if (!status.equalsIgnoreCase("VIP") && !status.equalsIgnoreCase("Regular")) {
						continue;
					}

					// Create a new Passenger object
					Passenger passenger = new Passenger(passengerID, name, flightID, status);
					AllPassenger.insertSortStudent(passenger);

				} catch (NumberFormatException e) {
					// Skip invalid lines with parsing errors
					continue;
				}
			}

			// After processing all lines, update the ObservableList
			passengersNotCheckIn.clear();
			passengersNotCheckIn.addAll(AllPassenger.toObservableList());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private BorderPane DisplayStatistical() {
		BorderPane root = new BorderPane();
		VBox statsBox = new VBox(15); // VBox for displaying stats
		statsBox.setPadding(new Insets(20));
		statsBox.setAlignment(Pos.TOP_CENTER);

		// Title
		Label title = new Label("Statistical Information");
		title.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");

		// Calculate Statistics
		int canceledVIPPassengers = (int) CanceledPassengers.toObservableList().stream()
				.filter(p -> p.getStatus().equalsIgnoreCase("VIP")).count();
		int canceledRegularPassengers = (int) CanceledPassengers.toObservableList().stream()
				.filter(p -> p.getStatus().equalsIgnoreCase("Regular")).count();
		int vipQueueCount = VIPpassengerQueue.size();
		int regularQueueCount = RegularpassengerQueue.size();
		int boardedVIPPassengers = (int) boardedPassengers.toObservableList().stream()
				.filter(p -> p.getStatus().equalsIgnoreCase("VIP")).count();
		int boardedRegularPassengers = (int) boardedPassengers.toObservableList().stream()
				.filter(p -> p.getStatus().equalsIgnoreCase("Regular")).count();

		// Labels and TextFields for each statistic
		Label canceledVIPLabel = new Label("Total Canceled VIP Passengers:");
		canceledVIPLabel.setStyle("-fx-font-weight: bold;");
		TextField canceledVIPField = new TextField(String.valueOf(canceledVIPPassengers));
		canceledVIPField.setEditable(false);
		canceledVIPField.setMaxWidth(250);

		Label canceledRegularLabel = new Label("Total Canceled Regular Passengers:");
		canceledRegularLabel.setStyle("-fx-font-weight: bold;");
		TextField canceledRegularField = new TextField(String.valueOf(canceledRegularPassengers));
		canceledRegularField.setEditable(false);
		canceledRegularField.setMaxWidth(250);

		Label vipQueueLabel = new Label("Total VIP Passengers in Queue:");
		vipQueueLabel.setStyle("-fx-font-weight: bold;");
		TextField vipQueueField = new TextField(String.valueOf(vipQueueCount));
		vipQueueField.setEditable(false);
		vipQueueField.setMaxWidth(250);

		Label regularQueueLabel = new Label("Total Regular Passengers in Queue:");
		regularQueueLabel.setStyle("-fx-font-weight: bold;");
		TextField regularQueueField = new TextField(String.valueOf(regularQueueCount));
		regularQueueField.setEditable(false);
		regularQueueField.setMaxWidth(250);

		Label boardedVIPLabel = new Label("Total VIP Passengers Boarded:");
		boardedVIPLabel.setStyle("-fx-font-weight: bold;");
		TextField boardedVIPField = new TextField(String.valueOf(boardedVIPPassengers));
		boardedVIPField.setEditable(false);
		boardedVIPField.setMaxWidth(250);

		Label boardedRegularLabel = new Label("Total Regular Passengers Boarded:");
		boardedRegularLabel.setStyle("-fx-font-weight: bold;");
		TextField boardedRegularField = new TextField(String.valueOf(boardedRegularPassengers));
		boardedRegularField.setEditable(false);
		boardedRegularField.setMaxWidth(250);

		// Use GridPane for form-like layout
		GridPane gridPane = new GridPane();
		gridPane.setHgap(15); // Horizontal spacing between columns
		gridPane.setVgap(10); // Vertical spacing between rows
		gridPane.setAlignment(Pos.CENTER);

		// Add all labels and text fields to the GridPane
		gridPane.add(canceledVIPLabel, 0, 0);
		gridPane.add(canceledVIPField, 1, 0);

		gridPane.add(canceledRegularLabel, 0, 1);
		gridPane.add(canceledRegularField, 1, 1);

		gridPane.add(vipQueueLabel, 0, 2);
		gridPane.add(vipQueueField, 1, 2);

		gridPane.add(regularQueueLabel, 0, 3);
		gridPane.add(regularQueueField, 1, 3);

		gridPane.add(boardedVIPLabel, 0, 4);
		gridPane.add(boardedVIPField, 1, 4);

		gridPane.add(boardedRegularLabel, 0, 5);
		gridPane.add(boardedRegularField, 1, 5);

		// Add title and GridPane to the statsBox
		statsBox.getChildren().addAll(title, gridPane);

		// Set the statsBox to the center of the BorderPane
		root.setCenter(statsBox);

		return root;
	}

	private void exportLogToFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Log File");
		File file = fileChooser.showSaveDialog(null);

		if (file != null) {
			try (FileWriter writer = new FileWriter(file)) {
				for (LogFile entry : LogFiles) {
					writer.write(entry.toString() + "\n");
				}
				showAlert("Success", "Log file exported successfully!");
			} catch (IOException e) {
				showAlert("Error", "An error occurred while exporting the log file: " + e.getMessage());
			}
		}
	}

	private BorderPane PrintLogFile() {
		BorderPane root = new BorderPane();

		// Label for the log file display
		Label title = new Label("Log File Contents");
		title.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
		root.setTop(title);
		BorderPane.setAlignment(title, Pos.CENTER);

		// TableView to display the log file entries
		TableView<LogFile> logTable = new TableView<>();
		logTable.setEditable(false);
		logTable.setItems(LogFiles); // Bind the table to the ObservableList

		logTable.setPrefWidth(800); // Set a wider width
		logTable.setPrefHeight(400); // Set a taller height

		// Define columns
		TableColumn<LogFile, String> timestampColumn = new TableColumn<>("Timestamp");
		timestampColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTimestamp()));

		TableColumn<LogFile, String> operationColumn = new TableColumn<>("Operation");
		operationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOperation()));

		TableColumn<LogFile, String> passengerNameColumn = new TableColumn<>("Passenger Name");
		passengerNameColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPassengerName()));

		TableColumn<LogFile, String> flightIDColumn = new TableColumn<>("Flight ID");
		flightIDColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFlightID()));

		TableColumn<LogFile, String> descriptionColumn = new TableColumn<>("Description");
		descriptionColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));

		// Add columns to the table
		logTable.getColumns().addAll(timestampColumn, operationColumn, passengerNameColumn, flightIDColumn,
				descriptionColumn);


		// Add the TableView to the center of the layout
		root.setCenter(logTable);

		return root;
	}

	private void addLog(String operation, String passengerName, String flightID, String description) {
		String timestamp = java.time.LocalDateTime.now().toString(); // Current timestamp
		LogFile logFile = new LogFile(timestamp, operation, passengerName, flightID, description);
		LogFiles.add(logFile); // Add to the ObservableList
	}

	// for the Time Picker
	private void addLog(String timestamp, String operation, String passengerName, String flightID, String description) {
		LogFile logFile = new LogFile(timestamp, operation, passengerName, flightID, description);
		LogFiles.add(logFile); // Add to the ObservableList
	}

	private BorderPane CancelPassenger() {
		BorderPane root = new BorderPane();

		// Table to display Canceled passengers
		TableView<Passenger> CanceledTable = new TableView<>();
		CanceledTable.setItems(FXCollections.observableArrayList(CanceledPassengers.toObservableList()));

		TableColumn<Passenger, String> PassengerID = new TableColumn<>("Passenger ID");
		PassengerID.setCellValueFactory(
				cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPassengerID())));
		TableColumn<Passenger, String> Name = new TableColumn<>("Name");
		Name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
		TableColumn<Passenger, String> FlightID = new TableColumn<>("Flight ID");
		FlightID.setCellValueFactory(
				cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFlightID())));
		TableColumn<Passenger, String> Status = new TableColumn<>("Status");
		Status.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));

		CanceledTable.getColumns().addAll(PassengerID, Name, FlightID, Status);

		// Button to Cancel a passenger from the VIP queue
		Button CancelButton1 = new Button("Cancel VIP Passenger");
		CancelButton1.setOnAction(event -> {
			try {
				// Attempt to cancel a passenger from the VIP queue
				Passenger canceledPassenger = (Passenger) VIPpassengerQueue.deQueue();

				if (canceledPassenger != null) {
					// Add the canceled passenger to the canceled list
					CanceledPassengers.insertSortStudent(canceledPassenger);

					// Log the cancellation
					addLog("Cancellation (VIP)", canceledPassenger.getName(),
							String.valueOf(canceledPassenger.getFlightID()), "VIP Passenger canceled");

					// Update the UI (CanceledTable) and remove from check-in list
					CanceledTable.setItems(FXCollections.observableArrayList(CanceledPassengers.toObservableList()));
					passengersCheckIn.remove(canceledPassenger);

					// Show success message
					showAlert("Success", "VIP Passenger canceled successfully!");
				} else {
					// If no passengers in the VIP queue
					showAlert("Error", "No VIP passengers available to cancel.");
				}

			} catch (Exception e) {
				// General error handling for unexpected cases
				showAlert("Error", "An unexpected error occurred: " + e.getMessage());
			}
		});

		// Button to Cancel a passenger from the Regular queue
		Button CancelButton2 = new Button("Cancel Regular Passenger");
		CancelButton2.setOnAction(event -> {
			try {
				// Attempt to cancel a passenger from the Regular queue
				Passenger canceledPassenger = (Passenger) RegularpassengerQueue.deQueue();

				if (canceledPassenger != null) {
					// Add the canceled passenger to the canceled list
					CanceledPassengers.insertSortStudent(canceledPassenger);

					// Log the cancellation
					addLog("Cancellation (Regular)", canceledPassenger.getName(),
							String.valueOf(canceledPassenger.getFlightID()), "Regular Passenger canceled");

					// Update the UI (CanceledTable) and remove from check-in list
					CanceledTable.setItems(FXCollections.observableArrayList(CanceledPassengers.toObservableList()));
					passengersCheckIn.remove(canceledPassenger);

					// Show success message
					showAlert("Success", "Regular Passenger canceled successfully!");
				} else {
					// If no passengers in the Regular queue
					showAlert("Error", "No Regular passengers available to cancel.");
				}

			} catch (Exception e) {
				// General error handling for unexpected cases
				showAlert("Error", "An unexpected error occurred: " + e.getMessage());
			}
		});

		// Label for Cancel Passengers
		Label labelCancel = new Label("Cancel Passengers");
		labelCancel.setStyle("-fx-font-weight: bold;");

		// VBox to contain both buttons
		VBox buttonBox = new VBox(10, CancelButton1, CancelButton2);
		buttonBox.setAlignment(Pos.CENTER);

		// Set the layout
		root.setTop(labelCancel);
		root.setCenter(CanceledTable);
		root.setRight(buttonBox);

		return root;
	}

	private BorderPane BoardPassenger() {
		BorderPane root = new BorderPane();

		// Table to display boarded passengers
		TableView<Passenger> boardedTable = new TableView<>();
		boardedTable.setItems(FXCollections.observableArrayList(boardedPassengers.toObservableList()));

		TableColumn<Passenger, String> PassengerID = new TableColumn<>("Passenger ID");
		PassengerID.setCellValueFactory(
				cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPassengerID())));
		TableColumn<Passenger, String> Name = new TableColumn<>("Name");
		Name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
		TableColumn<Passenger, String> FlightID = new TableColumn<>("Flight ID");
		FlightID.setCellValueFactory(
				cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFlightID())));
		TableColumn<Passenger, String> Status = new TableColumn<>("Status");
		Status.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));

		boardedTable.getColumns().addAll(PassengerID, Name, FlightID, Status);

		// Button to board a passenger specifically from the VIP queue
		Button boardButtonVIP = new Button("Board VIP Passenger");
		boardButtonVIP.setOnAction(event -> {
			Passenger boardedPassenger = null;

			// Dequeue from the VIP queue
			if (!VIPpassengerQueue.isEmpty()) {
				boardedPassenger = (Passenger) VIPpassengerQueue.deQueue();
			}

			if (boardedPassenger != null) {
				// Add the dequeued passenger to the boarded list
				boardedPassengers.insertSortStudent(boardedPassenger);
				addLog("Boarding (VIP)", boardedPassenger.getName(), String.valueOf(boardedPassenger.getFlightID()),
						"VIP Passenger boarded successfully");
				boardedTable.setItems(FXCollections.observableArrayList(boardedPassengers.toObservableList()));
				passengersCheckIn.remove(boardedPassenger);
				showAlert("Success", "VIP Passenger boarded successfully!");
			} else {
				// If no passengers in the VIP queue
				showAlert("Error", "No VIP passengers available to board.");
			}
		});

		// Button to board a passenger specifically from the Regular queue
		Button boardButtonRegular = new Button("Board Regular Passenger");
		boardButtonRegular.setOnAction(event -> {
			Passenger boardedPassenger = null;

			// Dequeue from the Regular queue
			if (!RegularpassengerQueue.isEmpty()) {
				boardedPassenger = (Passenger) RegularpassengerQueue.deQueue();
			}

			if (boardedPassenger != null) {
				// Add the dequeued passenger to the boarded list
				boardedPassengers.insertSortStudent(boardedPassenger);
				addLog("Boarding (Regular)", boardedPassenger.getName(), String.valueOf(boardedPassenger.getFlightID()),
						"Regular Passenger boarded successfully");
				boardedTable.setItems(FXCollections.observableArrayList(boardedPassengers.toObservableList()));
				passengersCheckIn.remove(boardedPassenger);
				showAlert("Success", "Regular Passenger boarded successfully!");
			} else {
				// If no passengers in the Regular queue
				showAlert("Error", "No Regular passengers available to board.");
			}
		});

		// Label for boarded passengers
		Label labelBoarded = new Label("Boarded Passengers");
		labelBoarded.setStyle("-fx-font-weight: bold;");

		// VBox to contain both buttons
		VBox buttonBox = new VBox(10, boardButtonVIP, boardButtonRegular);
		buttonBox.setAlignment(Pos.CENTER);

		// Set the layout
		root.setTop(labelBoarded);
		root.setCenter(boardedTable);
		root.setRight(buttonBox);

		return root;
	}

	private BorderPane CheckInPassenger() {
		BorderPane root = new BorderPane();

		// Table for displaying passenger information
		TableView<Passenger> passengerTable = new TableView<>();
		passengerTable.setItems(passengersCheckIn);

		TableColumn<Passenger, String> PassengerID = new TableColumn<>("Passenger ID");
		PassengerID.setCellValueFactory(
				cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPassengerID())));
		TableColumn<Passenger, String> Name = new TableColumn<>("Name");
		Name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
		TableColumn<Passenger, String> FlightID = new TableColumn<>("Flight ID");
		FlightID.setCellValueFactory(
				cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFlightID())));
		TableColumn<Passenger, String> Status = new TableColumn<>("Status");
		Status.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));

		passengerTable.getColumns().addAll(PassengerID, Name, FlightID, Status);

		// Fields to enter passenger details
		Label addLabel = new Label("Check In Passenger:");
		addLabel.setStyle("-fx-font-weight: bold;");

		// Button to check in all passengers
		Button checkInAllButton = new Button("Check In All Passengers");
		checkInAllButton.setOnAction(event -> {
			if (!passengersNotCheckIn.isEmpty()) {
				for (int i = 0; i < passengersNotCheckIn.size(); i++) {
					Passenger passenger = passengersNotCheckIn.get(i);
					if (passenger.getStatus().equalsIgnoreCase("VIP")) {
						VIPpassengerQueue.enQueue(passenger);
						passengersCheckIn.add(passenger);
						addLog("Check-In", passenger.getName(), String.valueOf(passenger.getFlightID()),
								"Checked in as VIP");
					} else {
						RegularpassengerQueue.enQueue(passenger);
						passengersCheckIn.add(passenger);
						addLog("Check-In", passenger.getName(), String.valueOf(passenger.getFlightID()),
								"Checked in as Regular");
					}
				}
				passengersNotCheckIn.clear();
				AllPassenger.clear();
				showAlert("Success", "All passengers checked in successfully!");
			} else {
				showAlert("Error", "No passengers to check in.");
			}
		});

		// TextField for entering a specific Passenger ID
		TextField passengerIdField = new TextField();
		passengerIdField.setPromptText("Enter Passenger ID");

		// Button to check in a specific passenger by ID
		Button checkInByIdButton = new Button("Check In by ID");
		checkInByIdButton.setOnAction(event -> {
			try {
				// Parse the entered Passenger ID
				int passengerId = Integer.parseInt(passengerIdField.getText().trim());

				// Find the passenger in the passengersNotCheckIn list
				Passenger passengerToCheckIn = passengersNotCheckIn.stream()
						.filter(p -> p.getPassengerID() == passengerId).findFirst().orElse(null);

				if (passengerToCheckIn != null) {
					// Check in the passenger
					if (passengerToCheckIn.getStatus().equalsIgnoreCase("VIP")) {
						VIPpassengerQueue.enQueue(passengerToCheckIn);
						addLog("Check-In", passengerToCheckIn.getName(),
								String.valueOf(passengerToCheckIn.getFlightID()), "Checked in as VIP");
					} else {
						RegularpassengerQueue.enQueue(passengerToCheckIn);
						addLog("Check-In", passengerToCheckIn.getName(),
								String.valueOf(passengerToCheckIn.getFlightID()), "Checked in as Regular");
					}
					passengersCheckIn.add(passengerToCheckIn);
					passengersNotCheckIn.remove(passengerToCheckIn);

					// Update the table and show a success alert
					passengerTable.refresh();
					showAlert("Success", "Passenger checked in successfully!");
				} else {
					// Passenger not found
					showAlert("Error", "Passenger with ID " + passengerId + " not found in the list.");
				}

				// Clear the input field
				passengerIdField.clear();

			} catch (NumberFormatException e) {
				// Handle invalid numeric input
				showAlert("Error", "Please enter a valid numeric Passenger ID.");
			}
		});

		// Layout for the buttons and input field
		VBox buttonBox = new VBox(10, checkInAllButton, checkInByIdButton, passengerIdField);
		buttonBox.setAlignment(Pos.CENTER);

		// Grid layout for the entire section
		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.add(addLabel, 0, 0);
		gridPane.add(buttonBox, 0, 2);
		gridPane.setAlignment(Pos.CENTER);

		root.setRight(gridPane);
		root.setCenter(passengerTable);

		return root;
	}

	private BorderPane createAddFlight() {
		BorderPane root = new BorderPane();
		VBox studentPane = new VBox(10);
		studentPane.setPadding(new Insets(10));

		// Table for displaying student information
		TableView<Flight> studentTable = new TableView<>();
		studentTable.setItems(flights);

		// Student ID column (converted to String for display)
		TableColumn<Flight, String> FlightID = new TableColumn<>("FlightID");
		FlightID.setCellValueFactory(
				cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFlightID())));

		// Student Name column
		TableColumn<Flight, String> Destination = new TableColumn<>("Destination");
		Destination.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDestination()));

		// Tawjihi Grade column
		TableColumn<Flight, String> Status = new TableColumn<>("Status");
		Status.setCellValueFactory(
				cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getStatus())));

		// Add columns to the student table
		studentTable.getColumns().addAll(FlightID, Destination, Status);

		// Fields to enter major details
		Label addLabel = new Label("Add Flight :");
		addLabel.setStyle("-fx-font-weight: bold;");
		TextField FlightIDField = new TextField();
		FlightIDField.setPromptText("Flight ID");
		TextField DestinationField = new TextField();
		DestinationField.setPromptText("Destination");
		TextField StatusField = new TextField();
		StatusField.setPromptText("Status");

		// Button to save the new major

		Button AddButton = new Button("Add");
		AddButton.setOnAction(event -> {
			try {
				int flightId = Integer.parseInt(FlightIDField.getText());
				String destination = DestinationField.getText().trim();
				String status = StatusField.getText().trim();

				if (destination.matches(".*\\d.*") || status.matches(".*\\d.*")) {
					showAlert("Error", "Please enter valid String for status and  destination(cannot be Nmbers).");
					return;
				}
				if (!status.equalsIgnoreCase("Active") && !status.equalsIgnoreCase("Not Active")
						&& !status.equalsIgnoreCase("In Active")) {
					showAlert("Error", "The status must be Equal Active or Not Active ");
					return;
				}

				Flight flight = new Flight(flightId, destination, status);
				flightList.insertSortFlight(flight);
				if (status.equalsIgnoreCase("Active")) {
					flightsActive.add(flight);
				} else if (status.equalsIgnoreCase("Not Active")) {
					flightsNotActive.add(flight);
				}
				flights.setAll(flightList.toObservableList());

				addLog("Add Flight", "-", String.valueOf(flightId), "Flight added with destination: " + destination);

				FlightIDField.clear();
				DestinationField.clear();
				StatusField.clear();
				showAlert("Success", "Flight added successfully!");
			} catch (NumberFormatException e) {
				showAlert("Error", "Please enter valid numbers for the Flight ID.");
			}
		});

		// Layout for the form fields and button
		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.add(addLabel, 0, 0);
		gridPane.add(FlightIDField, 0, 2);
		gridPane.add(DestinationField, 0, 4);
		gridPane.add(StatusField, 0, 6);
		gridPane.add(AddButton, 0, 8);
		gridPane.setAlignment(Pos.CENTER);

		root.setRight(gridPane);
		root.setCenter(studentTable);
		return root;
	}

	private BorderPane createUpdateFlight() {
		BorderPane root = new BorderPane();
		VBox studentPane = new VBox(10);
		studentPane.setPadding(new Insets(10));

		// Table for displaying student information
		TableView<Flight> studentTable = new TableView<>();
		studentTable.setItems(flights);

		// Student ID column (converted to String for display)
		TableColumn<Flight, String> FlightID = new TableColumn<>("FlightID");
		FlightID.setCellValueFactory(
				cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFlightID())));

		// Student Name column
		TableColumn<Flight, String> Destination = new TableColumn<>("Destination");
		Destination.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDestination()));

		// Tawjihi Grade column
		TableColumn<Flight, String> Status = new TableColumn<>("Status");
		Status.setCellValueFactory(
				cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getStatus())));

		// Add columns to the student table
		studentTable.getColumns().addAll(FlightID, Destination, Status);

		// Fields to enter major details
		Label addLabel = new Label("Update Flight :");
		addLabel.setStyle("-fx-font-weight: bold;");
		TextField FlightIDField = new TextField();
		FlightIDField.setPromptText("Flight ID");
		TextField DestinationField = new TextField();
		DestinationField.setPromptText("New Destination");
		TextField StatusField = new TextField();
		StatusField.setPromptText("New Status");

		// Button to save the new major

		Button UpdateButton = new Button("Update");
		UpdateButton.setOnAction(event -> {
			try {
				// Get input values
				int flightId = Integer.parseInt(FlightIDField.getText());
				String newDestination = DestinationField.getText().trim();
				String newStatus = StatusField.getText().trim();

				// Find and update flight
				boolean updated = flightList.update(flightId, newDestination, newStatus);

				if (updated) {
					// Find the updated flight
					Flight updatedFlight = flightList.search(flightId);

					if (updatedFlight != null) {
						// Remove the flight from the old status list
						flightsActive.remove(updatedFlight);
						flightsNotActive.remove(updatedFlight);

						// Add to the new status list
						if (newStatus.equalsIgnoreCase("Active")) {
							flightsActive.add(updatedFlight);
						} else if (newStatus.equalsIgnoreCase("Not Active")) {
							flightsNotActive.add(updatedFlight);
						}

						// Update the observable list for all flights
						flights.setAll(flightList.toObservableList());

						showAlert("Success", "Flight updated successfully!");
					}
				} else {
					showAlert("Error", "Update failed. Flight not found.");
				}

				// Clear fields
				FlightIDField.clear();
				DestinationField.clear();
				StatusField.clear();

			} catch (NumberFormatException e) {
				showAlert("Error", "Please enter valid numbers for the Flight ID.");
			}
		});

		// Layout for the form fields and button
		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.add(addLabel, 0, 0);
		gridPane.add(FlightIDField, 0, 2);
		gridPane.add(DestinationField, 0, 4);
		gridPane.add(StatusField, 0, 6);
		gridPane.add(UpdateButton, 0, 8);
		gridPane.setAlignment(Pos.CENTER);

		root.setRight(gridPane);
		root.setCenter(studentTable);
		return root;
	}

	private BorderPane createRemoveFlight() {
		BorderPane root = new BorderPane();
		VBox studentPane = new VBox(10);
		studentPane.setPadding(new Insets(10));

		// Table for displaying student information
		TableView<Flight> studentTable = new TableView<>();
		studentTable.setItems(flights);

		// Student ID column (converted to String for display)
		TableColumn<Flight, String> FlightID = new TableColumn<>("FlightID");
		FlightID.setCellValueFactory(
				cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFlightID())));

		// Student Name column
		TableColumn<Flight, String> Destination = new TableColumn<>("Destination");
		Destination.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDestination()));

		// Tawjihi Grade column
		TableColumn<Flight, String> Status = new TableColumn<>("Status");
		Status.setCellValueFactory(
				cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getStatus())));

		// Add columns to the student table
		studentTable.getColumns().addAll(FlightID, Destination, Status);

		// Fields to enter major details
		Label addLabel = new Label("Remove Flight :");
		addLabel.setStyle("-fx-font-weight: bold;");
		TextField FlightIDField = new TextField();
		FlightIDField.setPromptText("Flight ID");

		// Button to save the new major

		Button RemoveButton = new Button("Remove");
		RemoveButton.setOnAction(event -> {
			try {
				int flightId = Integer.parseInt(FlightIDField.getText());

				Flight removedFlight = flightList.removeAndReturn(flightId);

				if (removedFlight != null) {
					if (removedFlight.getStatus().equalsIgnoreCase("Active")) {
						flightsActive.remove(removedFlight);
					} else if (removedFlight.getStatus().equalsIgnoreCase("Not Active")) {
						flightsNotActive.remove(removedFlight);
					}

					flights.setAll(flightList.toObservableList());
					addLog("Remove Flight", "-", String.valueOf(flightId), "Flight removed");

					showAlert("Success", "Flight removed successfully!");
				} else {
					showAlert("Error", "Removal failed. Flight not found.");
				}

				FlightIDField.clear();
			} catch (NumberFormatException e) {
				showAlert("Error", "Please enter valid numbers for the Flight ID.");
			}
		});

		// Layout for the form fields and button
		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.add(addLabel, 0, 0);
		gridPane.add(FlightIDField, 0, 2);
		gridPane.add(RemoveButton, 0, 4);
		gridPane.setAlignment(Pos.CENTER);

		root.setRight(gridPane);
		root.setCenter(studentTable);
		return root;
	}

	private BorderPane createSearchFlight() {
		BorderPane root = new BorderPane();
		VBox studentPane = new VBox(10);
		studentPane.setPadding(new Insets(10));

		// Table for displaying student information
		TableView<Flight> studentTable = new TableView<>();
		studentTable.setItems(flights);

		// Student ID column (converted to String for display)
		TableColumn<Flight, String> FlightID = new TableColumn<>("FlightID");
		FlightID.setCellValueFactory(
				cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFlightID())));

		// Student Name column
		TableColumn<Flight, String> Destination = new TableColumn<>("Destination");
		Destination.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDestination()));

		// Tawjihi Grade column
		TableColumn<Flight, String> Status = new TableColumn<>("Status");
		Status.setCellValueFactory(
				cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getStatus())));

		// Add columns to the student table
		studentTable.getColumns().addAll(FlightID, Destination, Status);

		// Fields to enter major details
		Label addLabel = new Label("Search Flight :");
		addLabel.setStyle("-fx-font-weight: bold;");
		TextField FlightIDField = new TextField();
		FlightIDField.setPromptText("Flight ID Or Destination");

		// Button to save the new major

		Button RemoveButton = new Button("Search");
		RemoveButton.setOnAction(event -> {
			try {
				// Get input values
				int flightId = Integer.parseInt(FlightIDField.getText());
				String destination = FlightIDField.getText().trim();

				Flight searchedById = flightList.search(flightId);
				Flight searchedByDestination = flightList.search(destination);
				if (searchedById != null) {
					showAlert("Correct", "Flight Found");
				} else if (searchedByDestination != null) {
					showAlert("Correct", "Flight Found");
				}

				else {
					showAlert("Error", "Flight not found.");
				}

				// Clear fields
				FlightIDField.clear();

			} catch (NumberFormatException e) {
				showAlert("Error", "Please enter valid numbers for the ID Flight.");
			}
		});

		// Layout for the form fields and button
		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.add(addLabel, 0, 0);
		gridPane.add(FlightIDField, 0, 2);
		gridPane.add(RemoveButton, 0, 4);
		gridPane.setAlignment(Pos.CENTER);

		root.setRight(gridPane);
		root.setCenter(studentTable);
		return root;
	}

	private BorderPane createPrintAllFlights() {
		BorderPane root = new BorderPane();
		VBox studentPane = new VBox(10);
		studentPane.setPadding(new Insets(10));

		// Table for displaying student information
		TableView<Flight> studentTable = new TableView<>();
		studentTable.setItems(flights);

		// Student ID column (converted to String for display)
		TableColumn<Flight, String> FlightID = new TableColumn<>("FlightID");
		FlightID.setCellValueFactory(
				cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFlightID())));

		// Student Name column
		TableColumn<Flight, String> Destination = new TableColumn<>("Destination");
		Destination.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDestination()));

		// Tawjihi Grade column
		TableColumn<Flight, String> Status = new TableColumn<>("Status");
		Status.setCellValueFactory(
				cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getStatus())));

		// Add columns to the student table
		studentTable.getColumns().addAll(FlightID, Destination, Status);

		root.setCenter(studentTable);
		return root;
	}

	private BorderPane createPrintSpecificFlightInfo() {
		BorderPane root = new BorderPane();
		VBox studentPane = new VBox(10);
		studentPane.setPadding(new Insets(10));

		// Table for displaying student information
		TableView<Flight> studentTable = new TableView<>();
		studentTable.setItems(flights);

		// Student ID column (converted to String for display)
		TableColumn<Flight, String> FlightID = new TableColumn<>("FlightID");
		FlightID.setCellValueFactory(
				cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFlightID())));

		// Student Name column
		TableColumn<Flight, String> Destination = new TableColumn<>("Destination");
		Destination.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDestination()));

		// Tawjihi Grade column
		TableColumn<Flight, String> Status = new TableColumn<>("Status");
		Status.setCellValueFactory(
				cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getStatus())));

		// Add columns to the student table
		studentTable.getColumns().addAll(FlightID, Destination, Status);

		// Fields to enter major details
		Label addLabel = new Label("Enter Flight ID :");
		addLabel.setStyle("-fx-font-weight: bold;");
		TextField FlightIDField = new TextField();
		FlightIDField.setPromptText("Flight ID");

		// Button to save the new major

		Button RemoveButton = new Button("Enter");
		RemoveButton.setOnAction(event -> {
			try {
				// Get input values
				int flightId = Integer.parseInt(FlightIDField.getText());
				String destination = FlightIDField.getText().trim();

				Flight searchedById = flightList.search(flightId);
				Flight searchedByDestination = flightList.search(destination);
				if (searchedById != null) {
					showAlert("Flight Found", "ID: " + searchedById.getFlightID() + "\nDestination: "
							+ searchedById.getDestination() + "\nStatus: " + searchedById.getStatus());
				} else if (searchedByDestination != null) {
					showAlert("Flight Found",
							"ID: " + searchedByDestination.getFlightID() + "\nDestination: "
									+ searchedByDestination.getDestination() + "\nStatus: "
									+ searchedByDestination.getStatus());
				}

				else {
					showAlert("Error", "Flight not found.");
				}

				// Clear fields
				FlightIDField.clear();

			} catch (NumberFormatException e) {
				showAlert("Error", "Please enter valid numbers for the ID Flight.");
			}
		});

		// Layout for the form fields and button
		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.add(addLabel, 0, 0);
		gridPane.add(FlightIDField, 0, 2);
		gridPane.add(RemoveButton, 0, 4);
		gridPane.setAlignment(Pos.CENTER);

		root.setRight(gridPane);
		root.setCenter(studentTable);
		return root;
	}

	private BorderPane createDisplayallactiveflights() {
		BorderPane root = new BorderPane();
		VBox studentPane = new VBox(10);
		studentPane.setPadding(new Insets(10));

		// Table for displaying active flights
		TableView<Flight> studentTable = new TableView<>();
		studentTable.setItems(flightsActive); // Use the filtered list

		// Columns for FlightID, Destination, and Status
		TableColumn<Flight, String> FlightID = new TableColumn<>("FlightID");
		FlightID.setCellValueFactory(
				cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFlightID())));
		TableColumn<Flight, String> Destination = new TableColumn<>("Destination");
		Destination.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDestination()));
		TableColumn<Flight, String> Status = new TableColumn<>("Status");
		Status.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));

		// Add columns to the table
		studentTable.getColumns().addAll(FlightID, Destination, Status);

		root.setCenter(studentTable);
		return root;
	}

	private BorderPane createDisplayallinactiveflights() {
		BorderPane root = new BorderPane();
		VBox studentPane = new VBox(10);
		studentPane.setPadding(new Insets(10));

		// Table for displaying active flights
		TableView<Flight> studentTable = new TableView<>();
		studentTable.setItems(flightsNotActive); // Use the filtered list

		// Columns for FlightID, Destination, and Status
		TableColumn<Flight, String> FlightID = new TableColumn<>("FlightID");
		FlightID.setCellValueFactory(
				cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFlightID())));
		TableColumn<Flight, String> Destination = new TableColumn<>("Destination");
		Destination.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDestination()));
		TableColumn<Flight, String> Status = new TableColumn<>("Status");
		Status.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));

		// Add columns to the table
		studentTable.getColumns().addAll(FlightID, Destination, Status);

		root.setCenter(studentTable);
		return root;
	}

	private BorderPane createAddPassenger() {
		BorderPane root = new BorderPane();

		// Table for displaying passenger information
		TableView<Passenger> passengerTable = new TableView<>();
		passengerTable.setItems(passengersNotCheckIn);

		TableColumn<Passenger, String> PassengerID = new TableColumn<>("Passenger ID");
		PassengerID.setCellValueFactory(
				cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPassengerID())));
		TableColumn<Passenger, String> Name = new TableColumn<>("Name");
		Name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
		TableColumn<Passenger, String> FlightID = new TableColumn<>("Flight ID");
		FlightID.setCellValueFactory(
				cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFlightID())));
		TableColumn<Passenger, String> Status = new TableColumn<>("Status");
		Status.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));

		passengerTable.getColumns().addAll(PassengerID, Name, FlightID, Status);

		// Fields to enter passenger details
		Label addLabel = new Label("Add Passenger:");
		addLabel.setStyle("-fx-font-weight: bold;");
		TextField PassengerIDField = new TextField();
		PassengerIDField.setPromptText("Passenger ID");
		TextField NameField = new TextField();
		NameField.setPromptText("Name");
		TextField FlightIDField = new TextField();
		FlightIDField.setPromptText("Flight ID");
		TextField StatusField = new TextField();
		StatusField.setPromptText("Status");

		// Use Datepicker() to get date and time input
		VBox dateTimePicker = Datepicker();

		Button addButton = new Button("Add");
		addButton.setOnAction(event -> {
			try {
				int passengerId = Integer.parseInt(PassengerIDField.getText());
				String name = NameField.getText().trim();
				int flightId = Integer.parseInt(FlightIDField.getText());
				String status = StatusField.getText().trim();

				// Extract selected date and time
				LocalDate selectedDate = ((DatePicker) dateTimePicker.getChildren().get(0)).getValue();
				Integer selectedHour = ((ComboBox<Integer>) dateTimePicker.getChildren().get(1)).getValue();
				Integer selectedMinute = ((ComboBox<Integer>) dateTimePicker.getChildren().get(2)).getValue();

				if (selectedDate == null || selectedHour == null || selectedMinute == null) {
					showAlert("Error", "Please select a valid date and time.");
					return;
				}

				LocalDateTime selectedDateTime = LocalDateTime.of(selectedDate,
						LocalTime.of(selectedHour, selectedMinute));

				if (name.matches(".*\\d.*") || status.matches(".*\\d.*")) {
					showAlert("Error", "Status and Name cannot contain numbers.");
					return;
				}
				if (flightList.search(flightId) == null) {
					showAlert("Error", "Flight ID not found.");
					return;
				}
				if (AllPassenger.search(passengerId) != null) {
					showAlert("Error", "Passenger ID already exists.");
					return;
				}
				if (!status.equalsIgnoreCase("VIP") && !status.equalsIgnoreCase("Regular")) {
					showAlert("Error", "Status must be either 'VIP' or 'Regular'.");
					return;
				}

				Passenger newPassenger = new Passenger(passengerId, name, flightId, status);
				passengersNotCheckIn.add(newPassenger); // Add to observable list
				AllPassenger.insertSortStudent(newPassenger);

				// Log the addition
				addLog(selectedDateTime.toString(), "Add Passenger", name, String.valueOf(flightId),
						"Passenger added with status: " + status);

				PassengerIDField.clear();
				NameField.clear();
				FlightIDField.clear();
				StatusField.clear();

				showAlert("Success", "Passenger added successfully!");

			} catch (NumberFormatException e) {
				showAlert("Error", "Please enter valid numeric values for Passenger ID and Flight ID.");
			}
		});

		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.add(addLabel, 0, 0);
		gridPane.add(PassengerIDField, 0, 2);
		gridPane.add(NameField, 0, 4);
		gridPane.add(FlightIDField, 0, 6);
		gridPane.add(StatusField, 0, 8);
		gridPane.add(dateTimePicker, 0, 10);
		gridPane.add(addButton, 0, 12);
		gridPane.setAlignment(Pos.CENTER);

		root.setRight(gridPane);
		root.setCenter(passengerTable);
		return root;
	}

	private BorderPane createSearchPassenger() {
		BorderPane root = new BorderPane();

		// Table for displaying passenger information
		TableView<Passenger> passengerTable = new TableView<>();
		passengerTable.setItems(passengersNotCheckIn);

		TableColumn<Passenger, String> PassengerID = new TableColumn<>("Passenger ID");
		PassengerID.setCellValueFactory(
				cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPassengerID())));
		TableColumn<Passenger, String> Name = new TableColumn<>("Name");
		Name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
		TableColumn<Passenger, String> FlightID = new TableColumn<>("Flight ID");
		FlightID.setCellValueFactory(
				cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFlightID())));
		TableColumn<Passenger, String> Status = new TableColumn<>("Status");
		Status.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));

		passengerTable.getColumns().addAll(PassengerID, Name, FlightID, Status);

		// Field to search passenger
		Label searchLabel = new Label("Search Passenger:");
		searchLabel.setStyle("-fx-font-weight: bold;");
		TextField searchField = new TextField();
		searchField.setPromptText("Passenger ID");

		Button searchButton = new Button("Search");
		searchButton.setOnAction(event -> {
			try {
				int passengerId = Integer.parseInt(searchField.getText().trim());

				Passenger foundPassenger = AllPassenger.search(passengerId);

				if (foundPassenger != null) {
					showAlert("Correct", "Passenger Found");
				} else {
					showAlert("Error", "Passenger not found.");
				}

				searchField.clear();

			} catch (NumberFormatException e) {
				showAlert("Error", "Please enter a valid numeric Passenger ID.");
			}
		});

		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.add(searchLabel, 0, 0);
		gridPane.add(searchField, 0, 2);
		gridPane.add(searchButton, 0, 4);
		gridPane.setAlignment(Pos.CENTER);

		root.setRight(gridPane);
		root.setCenter(passengerTable);
		return root;
	}

	private BorderPane createRemovePassenger() {
		BorderPane root = new BorderPane();

		// Table for displaying passenger information
		TableView<Passenger> passengerTable = new TableView<>();
		passengerTable.setItems(passengersNotCheckIn);

		TableColumn<Passenger, String> PassengerID = new TableColumn<>("Passenger ID");
		PassengerID.setCellValueFactory(
				cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPassengerID())));
		TableColumn<Passenger, String> Name = new TableColumn<>("Name");
		Name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
		TableColumn<Passenger, String> FlightID = new TableColumn<>("Flight ID");
		FlightID.setCellValueFactory(
				cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFlightID())));
		TableColumn<Passenger, String> Status = new TableColumn<>("Status");
		Status.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));

		passengerTable.getColumns().addAll(PassengerID, Name, FlightID, Status);

		// Field to remove passenger
		Label removeLabel = new Label("Remove Passenger:");
		removeLabel.setStyle("-fx-font-weight: bold;");
		TextField removeField = new TextField();
		removeField.setPromptText("Passenger ID");

		// DatePicker and TimePicker
		VBox dateTimePicker = Datepicker(); // Use the existing Datepicker() method

		Button removeButton = new Button("Remove");
		removeButton.setOnAction(event -> {
			try {
				// Parse Passenger ID from the input field
				int passengerId = Integer.parseInt(removeField.getText().trim());

				// Extract selected date and time from DatePicker and ComboBox
				LocalDate selectedDate = ((DatePicker) dateTimePicker.getChildren().get(0)).getValue();
				Integer selectedHour = ((ComboBox<Integer>) dateTimePicker.getChildren().get(1)).getValue();
				Integer selectedMinute = ((ComboBox<Integer>) dateTimePicker.getChildren().get(2)).getValue();

				// Check if date and time are valid
				if (selectedDate == null || selectedHour == null || selectedMinute == null) {
					showAlert("Error", "Please select a valid date and time.");
					return;
				}

				// Create a timestamp from the selected date and time
				LocalDateTime selectedDateTime = LocalDateTime.of(selectedDate,
						LocalTime.of(selectedHour, selectedMinute));

				// Remove the passenger from AllPassenger
				Passenger removedPassenger = AllPassenger.removeid(passengerId);

				if (removedPassenger != null) {
					// Synchronize with passengersNotCheckIn
					passengersNotCheckIn.clear();
					passengersNotCheckIn.addAll(AllPassenger.toObservableList());

					// Log the removal with the selected timestamp
					addLog(selectedDateTime.toString(), // Save the timestamp here
							"Remove Passenger", removedPassenger.getName(),
							String.valueOf(removedPassenger.getFlightID()), "Passenger removed");

					// Refresh the passenger table
					passengerTable.refresh();
					showAlert("Success", "Passenger removed successfully!");
				} else {
					showAlert("Error", "Passenger not found.");
				}

				// Clear the input field
				removeField.clear();

			} catch (NumberFormatException e) {
				showAlert("Error", "Please enter a valid numeric Passenger ID.");
			}
		});

		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.add(removeLabel, 0, 0);
		gridPane.add(removeField, 0, 2);
		gridPane.add(dateTimePicker, 0, 4);
		gridPane.add(removeButton, 0, 6);
		gridPane.setAlignment(Pos.CENTER);

		root.setRight(gridPane);
		root.setCenter(passengerTable);
		return root;
	}

	private BorderPane createUpdatePassenger() {
		BorderPane root = new BorderPane();

		// Table for displaying passenger information
		TableView<Passenger> passengerTable = new TableView<>();
		passengerTable.setItems(passengersNotCheckIn);

		TableColumn<Passenger, String> PassengerID = new TableColumn<>("Passenger ID");
		PassengerID.setCellValueFactory(
				cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPassengerID())));
		TableColumn<Passenger, String> Name = new TableColumn<>("Name");
		Name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
		TableColumn<Passenger, String> FlightID = new TableColumn<>("Flight ID");
		FlightID.setCellValueFactory(
				cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFlightID())));
		TableColumn<Passenger, String> Status = new TableColumn<>("Status");
		Status.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));

		passengerTable.getColumns().addAll(PassengerID, Name, FlightID, Status);

		// Fields to update passenger details
		Label updateLabel = new Label("Update Passenger:");
		updateLabel.setStyle("-fx-font-weight: bold;");
		TextField PassengerIDField = new TextField();
		PassengerIDField.setPromptText("Passenger ID");
		TextField NameField = new TextField();
		NameField.setPromptText("New Name");
		TextField FlightIDField = new TextField();
		FlightIDField.setPromptText("New Flight ID");
		TextField StatusField = new TextField();
		StatusField.setPromptText("New Status");

		// Use Datepicker() to get date and time input
		VBox dateTimePicker = Datepicker();

		Button updateButton = new Button("Update");
		updateButton.setOnAction(event -> {
			try {
				int passengerId = Integer.parseInt(PassengerIDField.getText().trim());
				String newName = NameField.getText().trim();
				int newFlightId = Integer.parseInt(FlightIDField.getText().trim());
				String newStatus = StatusField.getText().trim();

				// Extract selected date and time
				LocalDate selectedDate = ((DatePicker) dateTimePicker.getChildren().get(0)).getValue();
				Integer selectedHour = ((ComboBox<Integer>) dateTimePicker.getChildren().get(1)).getValue();
				Integer selectedMinute = ((ComboBox<Integer>) dateTimePicker.getChildren().get(2)).getValue();

				if (selectedDate == null || selectedHour == null || selectedMinute == null) {
					showAlert("Error", "Please select a valid date and time.");
					return;
				}

				LocalDateTime selectedDateTime = LocalDateTime.of(selectedDate,
						LocalTime.of(selectedHour, selectedMinute));

				Passenger passengerToUpdate = AllPassenger.search(passengerId);

				if (passengerToUpdate != null) {
					passengerToUpdate.setName(newName);
					passengerToUpdate.setFlightID(newFlightId);
					passengerToUpdate.setStatus(newStatus);

					passengersNotCheckIn.clear();
					passengersNotCheckIn.addAll(AllPassenger.toObservableList());

					// Log the update
					addLog(selectedDateTime.toString(), "Update Passenger", newName, String.valueOf(newFlightId),
							"Passenger updated with status: " + newStatus);

					passengerTable.refresh();
					showAlert("Success", "Passenger updated successfully!");
				} else {
					showAlert("Error", "Passenger not found.");
				}

				PassengerIDField.clear();
				NameField.clear();
				FlightIDField.clear();
				StatusField.clear();

			} catch (NumberFormatException e) {
				showAlert("Error", "Please enter valid numeric values for Passenger ID and Flight ID.");
			}
		});

		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.add(updateLabel, 0, 0);
		gridPane.add(PassengerIDField, 0, 2);
		gridPane.add(NameField, 0, 4);
		gridPane.add(FlightIDField, 0, 6);
		gridPane.add(StatusField, 0, 8);
		gridPane.add(dateTimePicker, 0, 10);
		gridPane.add(updateButton, 0, 12);
		gridPane.setAlignment(Pos.CENTER);

		root.setRight(gridPane);
		root.setCenter(passengerTable);
		return root;
	}

	private BorderPane createPrintAllPassengers() {
		BorderPane root = new BorderPane();

		// Table for displaying passenger information
		TableView<Passenger> passengerTable = new TableView<>();
		passengerTable.setItems(passengersNotCheckIn);

		TableColumn<Passenger, String> PassengerID = new TableColumn<>("Passenger ID");
		PassengerID.setCellValueFactory(
				cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPassengerID())));
		TableColumn<Passenger, String> Name = new TableColumn<>("Name");
		Name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
		TableColumn<Passenger, String> FlightID = new TableColumn<>("Flight ID");
		FlightID.setCellValueFactory(
				cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFlightID())));
		TableColumn<Passenger, String> Status = new TableColumn<>("Status");
		Status.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));

		passengerTable.getColumns().addAll(PassengerID, Name, FlightID, Status);

		root.setCenter(passengerTable);
		return root;
	}

	private BorderPane createPrintSpecificPassengerInfo() {
		BorderPane root = new BorderPane();

		// Table for displaying passenger information
		TableView<Passenger> passengerTable = new TableView<>();
		passengerTable.setItems(passengersNotCheckIn);

		TableColumn<Passenger, String> PassengerID = new TableColumn<>("Passenger ID");
		PassengerID.setCellValueFactory(
				cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPassengerID())));
		TableColumn<Passenger, String> Name = new TableColumn<>("Name");
		Name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
		TableColumn<Passenger, String> FlightID = new TableColumn<>("Flight ID");
		FlightID.setCellValueFactory(
				cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFlightID())));
		TableColumn<Passenger, String> Status = new TableColumn<>("Status");
		Status.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));

		passengerTable.getColumns().addAll(PassengerID, Name, FlightID, Status);

		// Field to search passenger
		Label searchLabel = new Label("Enter Passenger ID:");
		searchLabel.setStyle("-fx-font-weight: bold;");
		TextField searchField = new TextField();
		searchField.setPromptText("Passenger ID");

		Button searchButton = new Button("Enter");
		searchButton.setOnAction(event -> {
			try {
				int passengerId = Integer.parseInt(searchField.getText().trim());

				Passenger foundPassenger = passengersNotCheckIn.stream().filter(p -> p.getPassengerID() == passengerId)
						.findFirst().orElse(null);

				if (foundPassenger != null) {
					showAlert("Passenger Found",
							"ID: " + foundPassenger.getPassengerID() + "\nName: " + foundPassenger.getName()
									+ "\nFlight ID: " + foundPassenger.getFlightID() + "\nStatus: "
									+ foundPassenger.getStatus());
				} else {
					showAlert("Error", "Passenger not found.");
				}

				searchField.clear();

			} catch (NumberFormatException e) {
				showAlert("Error", "Please enter a valid numeric Passenger ID.");
			}
		});

		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.add(searchLabel, 0, 0);
		gridPane.add(searchField, 0, 2);
		gridPane.add(searchButton, 0, 4);
		gridPane.setAlignment(Pos.CENTER);

		root.setRight(gridPane);
		root.setCenter(passengerTable);
		return root;
	}

	private VBox Datepicker() {
		DatePicker datePicker = new DatePicker();
		datePicker.setPromptText("Select Date");

		// Create ComboBoxes for hour and minute selection
		ComboBox<Integer> hourPicker = new ComboBox<>();
		ComboBox<Integer> minutePicker = new ComboBox<>();

		// Populate hours and minutes
		for (int i = 0; i < 24; i++) {
			hourPicker.getItems().add(i);
		}
		for (int i = 0; i < 60; i++) {
			minutePicker.getItems().add(i);
		}

		hourPicker.setPromptText("Hour");
		minutePicker.setPromptText("Minute");

		// Layout
		VBox layout = new VBox(10);
		layout.setPadding(new Insets(15));
		layout.getChildren().addAll(datePicker, hourPicker, minutePicker);
		return layout;
	}

	// Method to show alert messages
	private void showAlert(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

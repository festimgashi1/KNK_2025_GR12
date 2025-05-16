package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.beans.property.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Tickets;
import services.TicketService;
import session.AirlineSession;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class AddTicketController {

    @FXML private TextField txtFlightNumber;
    @FXML private TextField txtTicketPrice;
    @FXML private TextField txtMaxPassengers;

    @FXML private Label lblError;
    @FXML private Label lblSuccess;

    @FXML private TableView<Tickets> ticketTable;
    @FXML private TableColumn<Tickets, Integer> colFlightNumber;
    @FXML private TableColumn<Tickets, Double> colTicketPrice;
    @FXML private TableColumn<Tickets, Integer> colMaxPassengers;
    @FXML private TableColumn<Tickets, String> colDepartureAirport;
    @FXML private TableColumn<Tickets, String> colArrivalAirport;
    @FXML private TableColumn<Tickets, Date> colDepartureTime;
    @FXML private TableColumn<Tickets, Date> colArrivalTime;
    @FXML private TableColumn<Tickets, String> colStatus;
    @FXML private TableColumn<Tickets, Void> colActions;

    private final TicketService ticketService = new TicketService();

    @FXML
    public void initialize() {
        setupTableColumns();
        loadTickets();
    }

    private void setupTableColumns() {
        colFlightNumber.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getFlightNumber()).asObject());

        colTicketPrice.setCellValueFactory(cellData ->
                new SimpleDoubleProperty(cellData.getValue().getTicketPrice()).asObject());

        colMaxPassengers.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getPassengers()).asObject());

        colDepartureAirport.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDepartureAirport()));

        colArrivalAirport.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getArrivalAirport()));

        colDepartureTime.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getDepartureTime()));

        colArrivalTime.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getArrivalTime()));

        colStatus.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getStatus()));

        addActionButtonsToTable();
    }

    @FXML
    private void handleSubmit() {
        String flightNumber = txtFlightNumber.getText();
        String price = txtTicketPrice.getText();
        String maxPassengers = txtMaxPassengers.getText();

        if (flightNumber.isEmpty() || price.isEmpty() || maxPassengers.isEmpty()) {
            lblError.setText("All fields are required!");
            lblError.setVisible(true);
            lblSuccess.setVisible(false);
            return;
        }

        try {
            double ticketPrice = Double.parseDouble(price);
            int passengers = Integer.parseInt(maxPassengers);
            int flightNum = Integer.parseInt(flightNumber);

            Tickets ticket = new Tickets(0, flightNum, 0, new Date(), ticketPrice, "", passengers);

            ticketService.addTicket(ticket);

            lblSuccess.setText("Ticket added successfully.");
            lblSuccess.setVisible(true);
            lblError.setVisible(false);

            loadTickets();

        } catch (NumberFormatException e) {
            lblError.setText("Invalid input format.");
            lblError.setVisible(true);
            lblSuccess.setVisible(false);
        }
    }

    private void loadTickets() {
        int airlineId = AirlineSession.getAirlineId();
        List<Tickets> tickets = ticketService.getTicketsByAirlineId(airlineId);
        ticketTable.setItems(FXCollections.observableArrayList(tickets));
    }

    private void addActionButtonsToTable() {
        colActions.setCellFactory(col -> new TableCell<>() {
            private final Button btnEdit = new Button("Edit");
            private final Button btnDelete = new Button("Delete");
            private final HBox buttonBox = new HBox(10, btnEdit, btnDelete);

            {
                btnEdit.setStyle("-fx-background-color: #ffc107; -fx-text-fill: black;");
                btnDelete.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white;");

                btnEdit.setOnAction(e -> {
                    Tickets ticket = getTableView().getItems().get(getIndex());

                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/EditTicket.fxml"));
                        Parent root = loader.load();

                        EditTicketController controller = loader.getController();
                        controller.setTicket(ticket);

                        Stage stage = new Stage();
                        stage.setTitle("Edit Ticket");
                        stage.setScene(new Scene(root));
                        stage.showAndWait();

                        loadTickets(); // rifresko tabelën pas editimit

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });

                btnDelete.setOnAction(e -> {
                    Tickets ticket = getTableView().getItems().get(getIndex());
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Delete Ticket");
                    alert.setHeaderText("Are you sure you want to delete this ticket?");
                    alert.setContentText("Ticket ID: " + ticket.getTicketid());

                    alert.showAndWait().ifPresent(result -> {
                        if (result == ButtonType.OK) {
                            ticketService.deleteTicket(ticket.getTicketid());
                            loadTickets(); // Rifresko tabelën pas fshirjes
                        }
                    });
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(buttonBox);
                }
            }
        });
    }
}

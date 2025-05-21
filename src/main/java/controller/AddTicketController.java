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
import services.LanguageManager;
import session.AirlineSession;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class AddTicketController {

    @FXML private TextField txtFlightNumber;
    @FXML private TextField txtTicketPrice;
    @FXML private TextField txtMaxPassengers;

    @FXML private Label lblError;
    @FXML private Label lblSuccess;
    @FXML private Label lblTitleAdd;
    @FXML private Label lblTitleManage;

    @FXML private Label lblFlight;
    @FXML private Label lblPrice;
    @FXML private Label lblPassengers;

    @FXML private Button btnAddTicket;

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
        loadTranslations();
        LanguageManager.getInstance().addListener(this::loadTranslations);
    }

    private void loadTranslations() {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();

        lblTitleAdd.setText(bundle.getString("ticket.add.title"));
        lblTitleManage.setText(bundle.getString("ticket.manage.title"));
        lblFlight.setText(bundle.getString("ticket.flight"));
        lblPrice.setText(bundle.getString("ticket.price"));
        lblPassengers.setText(bundle.getString("ticket.passengers"));
        btnAddTicket.setText(bundle.getString("ticket.add.button"));

        colFlightNumber.setText(bundle.getString("col.flight"));
        colTicketPrice.setText(bundle.getString("col.price"));
        colMaxPassengers.setText(bundle.getString("col.passengers"));
        colDepartureAirport.setText(bundle.getString("col.from"));
        colArrivalAirport.setText(bundle.getString("col.to"));
        colDepartureTime.setText(bundle.getString("col.departure"));
        colArrivalTime.setText(bundle.getString("col.arrival"));
        colStatus.setText(bundle.getString("col.status"));
        colActions.setText(bundle.getString("col.actions"));
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

        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();

        if (flightNumber.isEmpty() || price.isEmpty() || maxPassengers.isEmpty()) {
            lblError.setText(bundle.getString("ticket.error.required"));
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

            lblSuccess.setText(bundle.getString("ticket.success"));
            lblSuccess.setVisible(true);
            lblError.setVisible(false);

            loadTickets();

        } catch (NumberFormatException e) {
            lblError.setText(bundle.getString("ticket.error.format"));
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

                        loadTickets();

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });

                btnDelete.setOnAction(e -> {
                    Tickets ticket = getTableView().getItems().get(getIndex());
                    ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle(bundle.getString("ticket.delete.title"));
                    alert.setHeaderText(bundle.getString("ticket.delete.confirm"));
                    alert.setContentText("Ticket ID: " + ticket.getTicketid());

                    alert.showAndWait().ifPresent(result -> {
                        if (result == ButtonType.OK) {
                            ticketService.deleteTicket(ticket.getTicketid());
                            loadTickets();
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

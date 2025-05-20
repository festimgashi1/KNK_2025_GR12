package controller;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.BoardingPass;
import services.BoardingPassService;
import java.time.LocalDate;

import services.SceneManager;
import session.AirlineSession;

public class BoardingPassController {


    @FXML
    private TextField txtFromCode;
    @FXML
    private TextField txtToCode;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtSeat;
    @FXML
    private TextField txtGate;
    @FXML
    private TextField txtFlightCode;
    @FXML
    private TextField txtBoardingTime;
    @FXML
    private TextField txtFlightDate;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField txtGateCloses;
    @FXML
    private TextField txtDepartureTime;
    @FXML
    private Label lblAirlinePriority;

    private final BoardingPassService passService = new BoardingPassService();

    @FXML
    public void initialize() {
        BoardingPass pass = (BoardingPass) SceneManager.getInstance().getData("boardingPass");
        System.out.println("BOARDING PASS RECEIVED: " + pass);

        if (pass != null) {
            populateBoardingPass(pass);
        } else {
            System.out.println("BoardingPass not found!");
        }
    }

    private void populateBoardingPass(BoardingPass pass) {
        txtFromCode.setText(pass.getFrom());
        txtToCode.setText(pass.getTo());
        txtName.setText(pass.getName());
        txtSeat.setText(pass.getSeat());
        txtGate.setText(pass.getGate());
        txtFlightCode.setText(pass.getFlightCode());
        txtBoardingTime.setText(pass.getBoardingTime());
        txtFlightDate.setText(pass.getFlightDate().toString());
        datePicker.setValue(pass.getFlightDate());
        txtGateCloses.setText(pass.getBoardingTime());
        txtDepartureTime.setText(pass.getBoardingTime());
        lblAirlinePriority.setText(AirlineSession.getAirlineName());
    }
}
package controller;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.BoardingPass;
import services.BoardingPassService;
import services.LanguageManager;
import services.SceneManager;
import session.AirlineSession;

import java.time.LocalDate;
import java.util.ResourceBundle;

public class BoardingPassController {

    @FXML private TextField txtFromCode;
    @FXML private TextField txtToCode;
    @FXML private TextField txtName;
    @FXML private TextField txtSeat;
    @FXML private TextField txtGate;
    @FXML private TextField txtFlightCode;
    @FXML private TextField txtBoardingTime;
    @FXML private TextField txtFlightDate;
    @FXML private DatePicker datePicker;
    @FXML private TextField txtGateCloses;
    @FXML private TextField txtDepartureTime;
    @FXML private Label lblAirlinePriority;

    @FXML private Label lblDate;
    @FXML private Label lblGateCloses;
    @FXML private Label lblDeparture;
    @FXML private Label lblBaggage;
    @FXML private Label lblNoteTitle;
    @FXML private Label lblNoteBody;

    private final BoardingPassService passService = new BoardingPassService();

    @FXML
    public void initialize() {
        applyTranslations();
        LanguageManager.getInstance().addListener(this::applyTranslations);

        BoardingPass pass = (BoardingPass) SceneManager.getInstance().getData("boardingPass");
        if (pass != null) {
            populateBoardingPass(pass);
        }
    }

    private void applyTranslations() {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();

        txtFromCode.setPromptText(bundle.getString("from"));
        txtToCode.setPromptText(bundle.getString("to"));
        txtName.setPromptText(bundle.getString("passenger.name"));
        txtSeat.setPromptText(bundle.getString("seat"));
        txtGate.setPromptText(bundle.getString("gate"));
        txtFlightCode.setPromptText(bundle.getString("flight.code"));
        txtBoardingTime.setPromptText(bundle.getString("boarding.time"));
        txtFlightDate.setPromptText(bundle.getString("flight.date"));

        lblDate.setText(bundle.getString("date"));
        lblGateCloses.setText(bundle.getString("gate.closes"));
        lblDeparture.setText(bundle.getString("departure"));

        lblBaggage.setText(bundle.getString("baggage"));
        lblNoteTitle.setText(bundle.getString("important.read"));
        lblNoteBody.setText(bundle.getString("boarding.info"));
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

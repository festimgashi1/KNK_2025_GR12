package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import model.Feedback;
import repository.FeedbackRepository;

public class FeedbackController {

    @FXML private ToggleButton btnVeryGood;
    @FXML private ToggleButton btnGood;
    @FXML private ToggleButton btnSomewhatGood;
    @FXML private ToggleButton btnBad;
    @FXML private ToggleButton btnVeryBad;

    @FXML private TextField txtComment;
    @FXML private Button btnSend;

    private final ToggleGroup ratingGroup = new ToggleGroup();
    private final FeedbackRepository feedbackRepo = new FeedbackRepository();

    @FXML
    public void initialize() {
        btnVeryGood.setToggleGroup(ratingGroup);
        btnGood.setToggleGroup(ratingGroup);
        btnSomewhatGood.setToggleGroup(ratingGroup);
        btnBad.setToggleGroup(ratingGroup);
        btnVeryBad.setToggleGroup(ratingGroup);
    }

    @FXML
    private void handleSendFeedback() {
        Toggle selected = ratingGroup.getSelectedToggle();

        if (selected == null) {
            showAlert(AlertType.WARNING, "Please select a rating.");
            return;
        }

        String rating = ((ToggleButton) selected).getText();
        String comment = txtComment.getText();


        int costumerId = 1;
        int flightNumber = 1002;

        Feedback feedback = new Feedback(costumerId, flightNumber, rating, comment);
        boolean inserted = feedbackRepo.saveFeedback(feedback);

        if (inserted) {
            showAlert(AlertType.INFORMATION, "Thank you for your feedback!");
            txtComment.clear();
            ratingGroup.selectToggle(null);
        } else {
            showAlert(AlertType.ERROR, "Failed to submit feedback.");
        }
    }

    private void showAlert(AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle("Feedback");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

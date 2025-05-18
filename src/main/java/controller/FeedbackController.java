package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import model.Feedback;
import repository.FeedbackRepository;
import session.CustomerSession;

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
        Toggle selectedToggle = ratingGroup.getSelectedToggle();

        if (selectedToggle == null) {
            showAlert(AlertType.WARNING, "Please select a rating.");
            return;
        }

        String rating = ((ToggleButton) selectedToggle).getText();
        String comment = txtComment.getText().trim();

        if (comment.isEmpty()) {
            showAlert(AlertType.WARNING, "Please write a comment.");
            return;
        }

        if (CustomerSession.getInstance().getCurrentCustomer() == null) {
            showAlert(AlertType.ERROR, "Session expired. Please log in again.");
            return;
        }

        int customerId = CustomerSession.getInstance().getCurrentCustomer().getCostumerId();

        Feedback feedback = new Feedback(customerId, null, rating, comment);

        boolean success = feedbackRepo.saveFeedback(feedback);
        if (success) {
            showAlert(AlertType.INFORMATION, "Feedback submitted successfully.");
            txtComment.clear();
            ratingGroup.getToggles().forEach(t -> t.setSelected(false));
        } else {
            showAlert(AlertType.ERROR, "Failed to submit feedback. Try again.");
        }
    }

    private void showAlert(AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

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

        if (CustomerSession.getInstance().getCurrentCostumer() == null) {
            showAlert(Alert.AlertType.ERROR, "You must be logged in to submit feedback.");
            return;
        }

        Toggle selected = ratingGroup.getSelectedToggle();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Please select a rating.");
            return;
        }

        String rating = ((ToggleButton) selected).getText();
        String comment = txtComment.getText();

        int costumerId = CustomerSession.getInstance().getCurrentCostumer().getCostumerId();

        Feedback feedback = new Feedback(costumerId, rating, comment);
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

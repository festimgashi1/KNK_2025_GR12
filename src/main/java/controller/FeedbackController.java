package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import model.Feedback;
import repository.FeedbackRepository;
import session.CustomerSession;
import services.LanguageManager;

import java.util.ResourceBundle;

public class FeedbackController {

    @FXML private ToggleButton btnVeryGood;
    @FXML private ToggleButton btnGood;
    @FXML private ToggleButton btnSomewhatGood;
    @FXML private ToggleButton btnBad;
    @FXML private ToggleButton btnVeryBad;

    @FXML private TextField txtComment;
    @FXML private Button btnSend;
    @FXML private Label lblQuestion;

    private final ToggleGroup ratingGroup = new ToggleGroup();
    private final FeedbackRepository feedbackRepo = new FeedbackRepository();

    @FXML
    public void initialize() {
        btnVeryGood.setToggleGroup(ratingGroup);
        btnGood.setToggleGroup(ratingGroup);
        btnSomewhatGood.setToggleGroup(ratingGroup);
        btnBad.setToggleGroup(ratingGroup);
        btnVeryBad.setToggleGroup(ratingGroup);

        applyTranslations();
        LanguageManager.getInstance().addListener(this::applyTranslations);
    }

    private void applyTranslations() {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();
        lblQuestion.setText(bundle.getString("feedback.question"));
        btnVeryGood.setText(bundle.getString("feedback.very.good"));
        btnGood.setText(bundle.getString("feedback.good"));
        btnSomewhatGood.setText(bundle.getString("feedback.somewhat.good"));
        btnBad.setText(bundle.getString("feedback.bad"));
        btnVeryBad.setText(bundle.getString("feedback.very.bad"));
        txtComment.setPromptText(bundle.getString("feedback.prompt"));
        btnSend.setText(bundle.getString("send"));
    }

    @FXML
    private void handleSendFeedback() {
        ResourceBundle bundle = LanguageManager.getInstance().getResourceBundle();

        if (CustomerSession.getInstance().getCurrentCostumer() == null) {
            showAlert(AlertType.ERROR, bundle.getString("feedback.login.required"));
            return;
        }

        Toggle selected = ratingGroup.getSelectedToggle();
        if (selected == null) {
            showAlert(AlertType.WARNING, bundle.getString("feedback.select.rating"));
            return;
        }

        String rating = ((ToggleButton) selected).getText();
        String comment = txtComment.getText();
        int costumerId = CustomerSession.getInstance().getCurrentCostumer().getCostumerId();

        Feedback feedback = new Feedback(costumerId, rating, comment);
        boolean inserted = feedbackRepo.saveFeedback(feedback);

        if (inserted) {
            showAlert(AlertType.INFORMATION, bundle.getString("feedback.thank.you"));
            txtComment.clear();
            ratingGroup.selectToggle(null);
        } else {
            showAlert(AlertType.ERROR, bundle.getString("feedback.failed"));
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

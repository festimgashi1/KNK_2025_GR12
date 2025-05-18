package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.Costumer;
import services.SceneManager;
import session.CustomerSession;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerProfileController implements Initializable {

    @FXML
    private Label lblFirstName;

    @FXML
    private Label lblLastName;

    @FXML
    private Label lblEmail;

    @FXML private Label lblBirthdate;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Costumer loggedIn = CustomerSession.getInstance().getCurrentCustomer();

        if (loggedIn != null) {
            lblFirstName.setText(loggedIn.getFirstName());
            lblLastName.setText(loggedIn.getLastName());
            lblEmail.setText(loggedIn.getEmail());
            lblBirthdate.setText(loggedIn.getBirthDate().toString());
        } else {
            System.out.println("No customer is logged in.");
        }
    }
    @FXML
    public void goToChangePassword() {
        SceneManager.getInstance().switchScene("/Views/change_customer_password.fxml");
    }

}

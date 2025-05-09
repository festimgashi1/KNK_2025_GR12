package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Costumer;
import model.dto.CostumerDto;
import repository.CostumerRepository;
import services.CostumerService;
import javafx.event.ActionEvent;
import services.SceneManager;

import java.io.IOException;

public class LogInController {

    @FXML
    private TextField emailTxt;

    @FXML
    private PasswordField pwId;

    private CostumerService costumerService;

    public LogInController(){
        this.costumerService = new CostumerService();
    }

    @FXML
    public void handleLogInButton(ActionEvent event) {
        String email = emailTxt.getText();
        String password = pwId.getText();

        try {
            Costumer user = this.costumerService.login(email, password);
            System.out.println("You have successfully logged in, welcome " + user.getFirstName());
            this.cleanFields();
        } catch (Exception e) {
            System.out.println("Error while logging: " + e.getMessage());
        }
    }

    private void cleanFields() {
        this.emailTxt.setText("");
        this.pwId.setText("");
    }

    @FXML
    public void goSignUp(ActionEvent event) {
        SceneManager.getInstance().switchScene("/Views/sign_up.fxml");
    }
}

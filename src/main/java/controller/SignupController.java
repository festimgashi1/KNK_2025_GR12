package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Costumer;
import model.dto.CreateCostumerDto;
import services.CostumerService;

import java.time.LocalDate;
import java.util.Date;

public class SignupController {

    @FXML
    private DatePicker dtBirthDate;

    @FXML
    private TextField pwdConfirmPass;

    @FXML
    private TextField pwdPassword;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtPhoneNumber;

    private CostumerService costumerService;

    public SignupController(){
        this.costumerService = new CostumerService();
    }

    @FXML
    void handleSignUpButton(ActionEvent event) {

        try{
            Costumer user = this.costumerService.create(this.getCostumerInputData());
            System.out.println("User inserted successfully!");
            System.out.println("User Id: " + user.getCostumerId());
            this.cleanFields();
        }catch (Exception e){
            System.out.println("Error inserting user. " + e.getMessage());
        }

    }
    private void cleanFields(){
        this.txtFirstName.setText("");
        this.txtLastName.setText("");
        this.txtEmail.setText("");
        this.txtPhoneNumber.setText("");
        this.txtAddress.setText("");
        this.pwdPassword.setText("");
        this.pwdConfirmPass.setText("");
        this.dtBirthDate.setValue(null);
    }
    private CreateCostumerDto getCostumerInputData(){
        String firstName = this.txtFirstName.getText();
        String lastName = this.txtLastName.getText();
        String email = this.txtEmail.getText();
        String phoneNumber = this.txtPhoneNumber.getText();
        String address = this.txtAddress.getText();
        String password = this.pwdPassword.getText();
        String confirmPass = this.pwdConfirmPass.getText();
        LocalDate birthDate = this.dtBirthDate.getValue();
        return new CreateCostumerDto(firstName,lastName ,email, phoneNumber,address,birthDate,password,confirmPass);
    }

}

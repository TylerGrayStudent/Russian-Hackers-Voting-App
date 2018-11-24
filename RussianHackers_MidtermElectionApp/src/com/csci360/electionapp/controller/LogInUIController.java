package com.csci360.electionapp.controller;

//import java.awt.*;

import com.csci360.electionapp.foundation.MySQLAccess;
import com.csci360.electionapp.model.Voter;
import com.csci360.electionapp.tech.security.Security;
import com.gluonhq.charm.glisten.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class LogInUIController {

    private Main main;


    @FXML
    private TextField userName;

    @FXML
    private TextField authentication;

    @FXML
    private PasswordField password;

    @FXML
    private Button registerButton;

    @FXML
    private Button logInButton;

    @FXML
    private TextArea text;

    @FXML
    private Text errormessage;

    public LogInUIController(){

    }
    @FXML
    void loginclicked(MouseEvent event) throws InvalidKeySpecException, NoSuchAlgorithmException {
        //String userUserName = userName.getText().toLowerCase();
        //String userPasswordHashed = Security.generateStorngPasswordHash(password.getText());
        //String userAuthentication = authentication.getText();
        MySQLAccess db = new MySQLAccess();
        try {
            String userNameText = userName.getText();
            String passwordText = password.getText();
            if(db.verifyLogIn(userNameText,passwordText))
            {
                System.out.println("Allowed User");
                String[] voterInfo = db.getUserInfo(userNameText);
                Voter voter = new Voter(voterInfo[0],voterInfo[1]);
                main.setMainVoter(voter);
                main.showCandidateSelectionScreen();


            }
            else{
                System.out.println("Denied User");
                errormessage.setText("Invalid Username or Password. Please try again.");
                password.clear();
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText("Invalid Username or Password. Please try again or select Register if you are a new Voter.");

                alert.showAndWait();


            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @FXML
    void registerclicked(MouseEvent event) throws Exception {
        main.showRegistrationWindow();
    }

    @FXML
    void adminClicked(MouseEvent event) throws Exception{
        main.showAdminLogInWindow();
        //main.showSettingsWindow();
    }

    @FXML
    void initialize() {

    }

    public void setMain(Main main){
        this.main = main;
    }
}

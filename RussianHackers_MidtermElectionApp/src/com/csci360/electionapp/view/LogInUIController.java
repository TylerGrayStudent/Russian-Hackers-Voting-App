package com.csci360.electionapp.view;

//import java.awt.*;

import com.csci360.electionapp.foundation.MySQLAccess;
import com.csci360.electionapp.tech.security.Security;
import com.gluonhq.charm.glisten.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

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

    public LogInUIController(){

    }
    @FXML
    void loginclicked(MouseEvent event) throws InvalidKeySpecException, NoSuchAlgorithmException {
        //String userUserName = userName.getText().toLowerCase();
        //String userPasswordHashed = Security.generateStorngPasswordHash(password.getText());
        //String userAuthentication = authentication.getText();
        MySQLAccess db = new MySQLAccess();
        try {
            if(db.verifyLogIn(userName.getText(),password.getText()))
            {
                System.out.println("Allowed User");
            }
            else{
                System.out.println("Denied User");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @FXML
    void registerclicked(MouseEvent event) throws Exception {
        main.showRegistrationWindow();;
    }

    @FXML
    void initialize() {

    }

    public void setMain(Main main){
        this.main = main;
    }
}

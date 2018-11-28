package com.csci360.electionapp.controller;


import com.csci360.electionapp.foundation.MySQLAccess;
import com.csci360.electionapp.model.Voter;
import com.gluonhq.charm.glisten.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

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
    void checkRegStatus() throws Exception {
        MySQLAccess db = new MySQLAccess();
        TextInputDialog dialog = new TextInputDialog("username");
        dialog.setTitle("Check Registration Status");
        dialog.setHeaderText("Check Registration Status");
        dialog.setContentText("Please enter your user name:");

        Optional<String> res = dialog.showAndWait();
        if(res.isPresent()){
            if(db.checkRegStatus(res.get())){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("User is registered");
                alert.setHeaderText("Awesome!!");
                alert.setContentText("You are registered and can log in!");
                alert.showAndWait();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("User is not registered");
                alert.setContentText("You are not registered. Please register. Contact a poll official for any help needed!");
                alert.showAndWait();
            }
        }
    }
    @FXML
    void loginclicked(MouseEvent event) throws Exception {
        if(userName.getText().trim().isEmpty() || password.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Blank Fields");
            alert.setHeaderText("Please make sure you enter both a user name and password to log in.");
            alert.showAndWait();


            return;
        }
        //String userUserName = userName.getText().toLowerCase();
        //String userPasswordHashed = Security.generateStorngPasswordHash(password.getText());
        //String userAuthentication = authentication.getText();
        if(!main.getElection().isActive()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("NO ACTIVE ELECTION");
            alert.setHeaderText("There currently is no active election. Please contact a polling official.");
            alert.showAndWait();
            return;
        }
        MySQLAccess db = new MySQLAccess();
        if(db.checkRegStatus(userName.getText())) {
            if (db.checkVotedStatus(userName.getText())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("USER ALREADY VOTED");
                alert.setHeaderText("You have already voted for the current election. Remember to vote next year!!");
                alert.showAndWait();

                return;
            }
        }
        try {
            String userNameText = userName.getText();
            String passwordText = password.getText();
            if(db.verifyLogIn(userNameText,passwordText))
            {
                System.out.println("Allowed User");
                String[] voterInfo = db.getUserInfo(userNameText);
                Voter voter = new Voter(voterInfo[1],voterInfo[0]);
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

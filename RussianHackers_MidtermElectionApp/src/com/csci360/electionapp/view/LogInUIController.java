package com.csci360.electionapp.view;

//import java.awt.*;

import com.csci360.electionapp.foundation.MySQLAccess;
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
            if(db.verifyLogIn(userName.getText(),password.getText()))
            {
                System.out.println("Allowed User");
<<<<<<< HEAD
                
=======
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("CandidateSelectionUI.fxml")));
                Node node = (Node)event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
>>>>>>> 1f95eca4d11dcc4d7eb905b57041be969e7ed543
            }
            else{
                System.out.println("Denied User");
                errormessage.setText("Invalid Username or Password. Please try again.");
                password.clear();
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText("Look, a Warning Dialog");

                alert.showAndWait();


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

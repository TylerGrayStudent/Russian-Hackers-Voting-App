package com.csci360.electionapp.controller;

import com.csci360.electionapp.foundation.MySQLAccess;
import com.csci360.electionapp.model.NewVoter;
import com.csci360.electionapp.tech.security.Decryption;
import com.csci360.electionapp.tech.security.Encryption;
import com.gluonhq.charm.glisten.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

public class NewVoterRegistrationController {

    private Main main;
    private ArrayList<TextField> fields;
    private String regex;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField username;

    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;

    @FXML
    private TextField password;

    @FXML
    private TextField passwordconfirm;

    @FXML
    private TextField address;

    @FXML
    private TextField zipcode;

    @FXML
    private TextField ssn;

    @FXML
    private TextField dlnumber;

    @FXML
    void initialize() {
        fields = new ArrayList<>();
        fields.add(username);
        fields.add(firstname);
        fields.add(lastname);
        fields.add(password);
        fields.add(passwordconfirm);
        fields.add(address);
        fields.add(zipcode);
        fields.add(ssn);
        fields.add(dlnumber);
        regex = "^[0-9]{9}";

    }

    public void submitClicked(MouseEvent event) throws Exception {
        //Model to create the user in DB
        /**
         * try{
         *  DB.createUser(username.getText()...)
         * }
         * catch(exception e){
         *  System.out.println("Error: Missing Data!!");
         * }
         *
         *
         *
         */
        Encryption encryption = new Encryption();
        MySQLAccess db = new MySQLAccess();

        boolean empty = false;
        for(TextField f: fields){
            f.setStyle(null);
            if(f.getText().trim().isEmpty()){
                f.setStyle("    -fx-text-box-border: red ;\n" +
                        "    -fx-focus-color: red ;");
                empty = true;

            }


        }
        if(empty){
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Blank Fields");
            alert.setHeaderText("Please make sure you fill out the entire form in order to sumbit.");
            alert.showAndWait();
            return;
        }

        if(db.checkRegStatus(username.getText())){
            username.setStyle("    -fx-text-box-border: red ;\n" +
                    "    -fx-focus-color: red ;");
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("UserName Taken");
            alert.setHeaderText("That username is already taken. Please use a different one, of if that is your username, simply log in from the main screen.");
            alert.showAndWait();
            username.setText(null);
            return;
        }

        if(!password.getText().equals(passwordconfirm.getText())){
            passwordconfirm.setStyle("    -fx-text-box-border: red ;\n" +
                    "    -fx-focus-color: red ;");
            password.setStyle("    -fx-text-box-border: red ;\n" +
                    "    -fx-focus-color: red ;");

            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Passwords Dont Match");
            alert.setHeaderText("Please make sure your passwords match.");
            alert.showAndWait();
            password.setText(null);
            passwordconfirm.setText(null);

            return;
        }

        if(!ssn.getText().matches(regex)){
            ssn.setStyle("    -fx-text-box-border: red ;\n" +
                    "    -fx-focus-color: red ;");

            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("SSN must be 9 digits");
            alert.setHeaderText("Please make sure your social security is 9 digits.");
            alert.showAndWait();
            ssn.setText(null);
            return;
        }

        if(!dlnumber.getText().matches(regex)){
            dlnumber.setStyle("    -fx-text-box-border: red ;\n" +
                    "    -fx-focus-color: red ;");

            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("DL Number must be 9 digits");
            alert.setHeaderText("Please make sure your DL Number is 9 digits.");
            alert.showAndWait();
            dlnumber.setText(null);
            return;
        }




        NewVoter nv = new NewVoter(username.getText(),firstname.getText(),lastname.getText(),password.getText(),address.getText(),zipcode.getText(),encryption.encrypt(ssn.getText()),encryption.encrypt(dlnumber.getText()));
        db.addNewRegisteredVoter(nv);
        main.initRootLayout();
        main.showLogInWindow();
    }

    @FXML
    void cancelClicked() throws Exception {
        main.showLogInWindow();
    }


    public void setMain(Main main){
        this.main = main;
    }
}

package com.csci360.electionapp.controller;

import com.csci360.electionapp.foundation.MySQLAccess;
import com.csci360.electionapp.model.NewVoter;
import com.gluonhq.charm.glisten.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class NewVoterRegistrationController {

    private Main main;

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
        NewVoter nv = new NewVoter(username.getText(),firstname.getText(),lastname.getText(),password.getText(),address.getText(),zipcode.getText(),ssn.getText(),dlnumber.getText());
        MySQLAccess db = new MySQLAccess();
        db.addNewRegisteredVoter(nv);
        main.initRootLayout();
        main.showLogInWindow();
    }



    public void setMain(Main main){
        this.main = main;
    }
}
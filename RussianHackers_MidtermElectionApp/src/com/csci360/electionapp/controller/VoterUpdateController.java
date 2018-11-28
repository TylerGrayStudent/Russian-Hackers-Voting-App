package com.csci360.electionapp.controller;

import com.csci360.electionapp.foundation.MySQLAccess;
import com.csci360.electionapp.tech.security.Encryption;
import com.gluonhq.charm.glisten.control.TextField;
import javafx.fxml.FXML;

import java.util.ArrayList;

public class VoterUpdateController {

    private Main main;
    private String voterID;

    private ArrayList<TextField> fields;

    @FXML
    private TextField username;

    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;


    @FXML
    private TextField address;

    @FXML
    private TextField zipcode;

    @FXML
    private TextField ssn;

    @FXML
    private TextField dlnumber;



    @FXML
    void initialize(String username) throws Exception {
        MySQLAccess db = new MySQLAccess();
        ArrayList<String> info = db.getUserData(username);
        voterID = info.get(0);
        firstname.setText(info.get(1));
        lastname.setText(info.get(2));
        address.setText(info.get(3));
        zipcode.setText(info.get(4));
        ssn.setText(info.get(5));
        dlnumber.setText(info.get(6));



    }

    @FXML
    void submitClicked() throws Exception {
        ArrayList<String> info = new ArrayList<>();
        info.add(voterID);
        info.add(firstname.getText());
        info.add(lastname.getText());
        info.add(address.getText());
        info.add(zipcode.getText());
        Encryption encryption = new Encryption();
        info.add(encryption.encrypt(ssn.getText()));
        info.add(encryption.encrypt(dlnumber.getText()));
        MySQLAccess db = new MySQLAccess();
        db.updateUserByID(info);
        main.showSettingsWindow();

    }





    @FXML
    void cancelClicked() throws Exception {
        main.showSettingsWindow();
    }



    public void setMain(Main main){
        this.main = main;
    }
}

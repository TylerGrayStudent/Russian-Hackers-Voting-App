package com.csci360.electionapp.view;

import com.gluonhq.charm.glisten.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

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

    public void setMain(Main main){
        this.main = main;
    }
}

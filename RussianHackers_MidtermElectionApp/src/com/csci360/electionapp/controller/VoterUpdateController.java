package com.csci360.electionapp.controller;

import com.csci360.electionapp.foundation.MySQLAccess;
import com.csci360.electionapp.tech.security.Encryption;
import com.gluonhq.charm.glisten.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.util.ArrayList;

public class VoterUpdateController {

    private Main main;
    private String voterID;
    private String regex;
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

        fields = new ArrayList<>();
        fields.add(firstname);
        fields.add(lastname);
        fields.add(address);
        fields.add(zipcode);
        fields.add(ssn);
        fields.add(dlnumber);
        regex = "^[0-9]{9}";




    }

    @FXML
    void submitClicked() throws Exception {

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

        if(!ssn.getText().matches(regex)){
            ssn.setStyle("    -fx-text-box-border: red ;\n" +
                    "    -fx-focus-color: red ;");

            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("SSN must be 9 digits");
            alert.setHeaderText("Please make sure your social security is 9 digits.");
            alert.showAndWait();
            ssn.setText("");
            return;
        }

        if(!dlnumber.getText().matches(regex)){
            dlnumber.setStyle("    -fx-text-box-border: red ;\n" +
                    "    -fx-focus-color: red ;");

            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("DL Number must be 9 digits");
            alert.setHeaderText("Please make sure your DL Number is 9 digits.");
            alert.showAndWait();
            dlnumber.setText("");
            return;
        }



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

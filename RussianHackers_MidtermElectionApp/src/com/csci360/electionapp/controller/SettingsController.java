package com.csci360.electionapp.controller;

import com.csci360.electionapp.foundation.MySQLAccess;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SettingsController {

    private Main main;

    @FXML
    TextField username;


    @FXML
    void electionCreatorClicked(MouseEvent event) throws Exception {
        main.showElectionCreatorWindow();


    }

    @FXML
    void logout() throws Exception{
        main.showLogInWindow();
    }
    public void setMain(Main main){
        this.main = main;
    }

    @FXML
    void recountClicked(MouseEvent event) {

    }

    @FXML
    void editClicked() throws Exception {
        if(username.getText().trim().isEmpty()){
            return;
        }
        else {
            MySQLAccess db = new MySQLAccess();

            if(!db.checkRegStatus(username.getText())){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("There is no registered user by that username");
                alert.showAndWait();
            }
            else{
                main.showVoterInfoUpdateScreen(username.getText());
            }

        }
    }

    @FXML
    void electionResultsClicked(MouseEvent event) throws Exception {
        if(main.getElection().isActive()){
            main.showElectionResultsWindow();
        }
        else{
            main.showSettingsWindow();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("NO ACTIVE ELECTION");
            alert.setHeaderText("There currently isnt an active election..");

            alert.showAndWait();

            return;
        }

    }



}

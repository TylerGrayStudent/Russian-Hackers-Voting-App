package com.csci360.electionapp.controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class SettingsController {

    private Main main;


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
}

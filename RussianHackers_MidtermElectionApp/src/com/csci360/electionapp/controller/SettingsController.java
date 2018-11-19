package com.csci360.electionapp.controller;

import javafx.fxml.FXML;

public class SettingsController {

    private Main main;


    @FXML
    void logout() throws Exception{
        main.showLogInWindow();
    }
    public void setMain(Main main){
        this.main = main;
    }
}

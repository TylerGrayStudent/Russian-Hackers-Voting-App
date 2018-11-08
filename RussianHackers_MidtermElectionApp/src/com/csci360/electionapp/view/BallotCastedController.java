package com.csci360.electionapp.view;

import javafx.fxml.FXML;

public class BallotCastedController {

    private Main main;

    @FXML
    void initialize() {

    }

    @FXML
    void logOut() throws Exception{
        main.showLogInWindow();
    }

    public void setMain(Main main){
        this.main = main;
    }
}

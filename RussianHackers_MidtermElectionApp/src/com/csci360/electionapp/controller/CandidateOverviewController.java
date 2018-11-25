package com.csci360.electionapp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class CandidateOverviewController {

    private Main main;

    @FXML
    private Button submitButton;

    @FXML
    void submitClicked(MouseEvent event) {

    }

    public void updateCandidate(){

    }

    public void setMain(Main main){
        this.main = main;
    }
}

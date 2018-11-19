package com.csci360.electionapp.controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class CandidateSelectionController {

    private Main main;

    @FXML
    void submitClicked(MouseEvent event) throws Exception {
        main.initRootLayout();
        main.showBallotCastedScreen();
    }

    @FXML
    void initialize() {

    }

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    void nextButton(MouseEvent event) throws Exception{
        main.nextTab();
    }
}

package com.csci360.electionapp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;

import java.io.IOException;

public class CandidatesEmptyController {

    private TabPane tab;

    @FXML
    void nextButton(){
        tab.getSelectionModel().selectNext();
    }

    public void setTab(TabPane tab) throws IOException { this.tab = tab; tab.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);}
}

package com.csci360.electionapp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class CandidatesEmptyController {

    private TabPane tab;

    @FXML
    private Text text;

    @FXML
    void nextButton(){
        tab.getSelectionModel().selectNext();
    }

    @FXML
    void radioSelected(){


    }

    @FXML
    void initialize(){
        text.setText("Test");

    }

    public void updateCurrentlySelectedName(String name){
        text.setText(name);
    }

    public void setTab(TabPane tab) throws IOException { this.tab = tab; tab.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE); }
}

package com.csci360.electionapp.controller;

import com.csci360.electionapp.model.Office;
import javafx.fxml.FXML;

public class OfficeCreationController {
    private Office office;

    @FXML
    void initialize() {
        office = new Office("temp");
    }

    @FXML
    void addNewCandidate(String candidate){

        office.addCandidate(candidate);
    }

}

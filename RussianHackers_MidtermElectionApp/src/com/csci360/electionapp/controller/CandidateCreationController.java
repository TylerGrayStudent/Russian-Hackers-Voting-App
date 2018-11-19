package com.csci360.electionapp.controller;

import com.csci360.electionapp.model.Office;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CandidateCreationController {

    private Main main;
    private Office office;

    @FXML
    private ComboBox<String> candidateList;

    @FXML
    private Button deleteCandidateButton;

    @FXML
    private Button createCandidateButton;

    @FXML
    private TextField candidateName;

    @FXML
    private Button okButton;

    @FXML
    void createCandidateClicked(MouseEvent event) throws Exception{
        if(candidateName.getText() != null && !candidateName.getText().trim().isEmpty() && !office.contains(candidateName.getText())) {
            office.addCandidate(candidateName.getText());
            candidateList.getItems().add(candidateName.getText());
            System.out.println(candidateName.getText());
            candidateName.setText("");
        }
        else{
            candidateName.setText("");
        }
    }

    @FXML
    void deleteCandidateClicked(MouseEvent event) throws Exception{
        if(candidateList.getItems().isEmpty()){
            return;
        }
        office.removeCandidate(candidateList.getValue());
        candidateList.getItems().remove(candidateList.getValue());

    }
    @FXML
    void initialize() {
        //TODO: Need to change to get the office from the Election
        office = new Office("Temp");
    }

    public void setMain(Main main){
        this.main = main;
    }
}
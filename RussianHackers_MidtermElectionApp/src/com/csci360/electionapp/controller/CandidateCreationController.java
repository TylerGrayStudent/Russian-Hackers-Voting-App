package com.csci360.electionapp.controller;

import com.csci360.electionapp.model.Office;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class CandidateCreationController {

    private Main main;
    private Office office;

    @FXML
    private Text officeNameText;

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
            //candidateList.getItems().add(candidateName.getText());
            //System.out.println(candidateName.getText());
            candidateName.setText("");
            updateCandidateList();
        }
        else{
            candidateName.setText("");
        }
    }

    @FXML
    void okButtonPressed() throws Exception {
        main.showOfficeCreationScreen(main.getElection());
    }

    void updateCandidateList(){
        candidateList.getItems().clear();
        for(String candidate: office.getCandidates()){

            candidateList.getItems().add(candidate);
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

    }

    void getOfficeData(Office o){
        office = o;
        officeNameText.setText("Office: " + office.getNameOfOffice());
        updateCandidateList();
    }

    public void setMain(Main main){
        this.main = main;
    }
}
package com.csci360.electionapp.controller;

import com.csci360.electionapp.model.Election;
import com.csci360.electionapp.model.Office;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public class OfficeCreationController {
    private Office office;
    private Election election;
    private Main main;


    @FXML
    private TextField officeName;

    @FXML
    private ComboBox<String> officeList;

    @FXML
    private Button editOfficeButton;

    @FXML
    private Button createNewOfficeButton;

    @FXML
    private Button okButton;

    @FXML
    private Button deleteOfficeButton;

    public void setMain(Main main) {
        this.main = main;
    }


    @FXML
    void initialize() {
    }


    void setElection(Election e){
        election = e;
        updateOfficeList();
    }

    @FXML
    void addNewOfficeClicked(MouseEvent event){
        if(officeName.getText() != null && !officeName.getText().trim().isEmpty() && !election.containsNameOf(officeName.getText())){
            Office newOffice = new Office(officeName.getText());
            election.addOffice(newOffice);
            officeName.setText("");
            updateOfficeList();

        }
        else {
            officeName.setText("");
        }
    }

    @FXML
    void deleteOfficeClicked(MouseEvent event){
        if(officeList.getItems().isEmpty()){
            return;
        }
        election.removeOfficeByName(officeList.getValue());
        updateOfficeList();

    }

    void updateOfficeList(){
        officeList.getItems().clear();
        for(Office o: election.getOffices()){
            officeList.getItems().add(o.getNameOfOffice());
        }
    }

    @FXML
    void okClicked(MouseEvent event) throws Exception {
        main.showElectionCreatorWindow();
    }

    @FXML
    void editOfficeClicked() throws Exception {
        String officeName = officeList.getValue();
        Office o = election.getOfficeByName(officeName);
        main.showCandidateCreationScreen(o);


    }



}

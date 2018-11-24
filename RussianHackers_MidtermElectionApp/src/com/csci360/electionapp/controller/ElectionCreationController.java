package com.csci360.electionapp.controller;

import com.csci360.electionapp.model.Election;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class ElectionCreationController {
    private Main main;
    private Election election;

    @FXML
    private Text electionStatus;

    @FXML
    private Button createElectionButtonClicked;

    @FXML
    private TextField nameOfElection;

    @FXML
    private Button cancelButton;

    @FXML
    private Button endElectionButton;

    @FXML
    void cancelClicked(MouseEvent event) throws Exception {
        main.showSettingsWindow();


    }

    @FXML
    void initialize() {
        try{
            updateText();
        }
        catch (Exception e){
            electionStatus.setText("There isnt currently an active election");

        }

       // updateText();
    }

    @FXML
    void createElectionClicked(MouseEvent event) {
        election = new Election(nameOfElection.getText());
        main.setElection(election);
        nameOfElection.clear();
        updateText();


    }

    @FXML
    void endElectionClicked(MouseEvent event) {
        main.getElection().endElectin();
        main.setElection(null);

    }

    @FXML
    void editElectionClicked(MouseEvent event) throws Exception {
        main.showOfficeCreationScreen(main.getElection());

    }

    public void setMain(Main main){
        this.main = main;
    }

    public void updateText(){
        try{
            String name = main.getElection().getNameOfElection();
            electionStatus.setText(main.getElection().getNameOfElection() + " Election is Currently Active");
        }
        catch(Exception e){
           electionStatus.setText("There isnt currently an active election");
        }
    }
}

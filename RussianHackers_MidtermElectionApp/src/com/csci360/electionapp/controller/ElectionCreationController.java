package com.csci360.electionapp.controller;

import com.csci360.electionapp.foundation.MySQLAccess;
import com.csci360.electionapp.model.Election;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    void importClicked(MouseEvent event) throws Exception {
        //main.showSettingsWindow();


    }

    @FXML
    void exportClicked(MouseEvent event) throws Exception {
       // main.showSettingsWindow();
    }

    @FXML
    void publishClicked() throws Exception {
        if(!main.getElection().isActive()){
            main.getElection().publishElection();
            main.createBallotBox();
            MySQLAccess db = new MySQLAccess();
            db.publishElection(main.getElection());
            electionStatus.setText(main.getElection().getNameOfElection() + " is the current election and is published.");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ELECTION PUBLISHED!");
            alert.setHeaderText("ELECTION PUBLISHED.");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ALREADY ACTIVE ELECTION");
            alert.setHeaderText("There currently is an election active. In order to pubish an election, the current one must be ended.");
            alert.showAndWait();
        }
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
    if(main.getElection()!=null){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("ALREADY ACTIVE ELECTION");
        alert.setHeaderText("There currently is an election active. In order to create an election, the current one must be ended.");
        alert.showAndWait();
        return;
    }
        election = new Election(nameOfElection.getText());
        main.setElection(election);
        nameOfElection.clear();
        updateText();


    }

    @FXML
    void endElectionClicked(MouseEvent event) {
        main.getElection().endElectin();
        main.setElection(null);
        electionStatus.setText("There is no current election active.");

    }

    @FXML
    void editElectionClicked(MouseEvent event) throws Exception {
        if(main.getElection() == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("NO CURRENT ELECTION");
            alert.setHeaderText("There isnt an active election. Please create one to edit it.");
            alert.showAndWait();

            return;
        }
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

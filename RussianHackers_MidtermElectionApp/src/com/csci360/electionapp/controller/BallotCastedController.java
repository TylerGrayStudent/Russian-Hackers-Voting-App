package com.csci360.electionapp.controller;

import com.csci360.electionapp.model.Ballot;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

public class BallotCastedController {

    private Main main;
    private Ballot ballot;
    private ArrayList<Text> offices;
    private ArrayList<Text> candidates;


    @FXML
    private Text textOffice1;

    @FXML
    private Text textOffice2;

    @FXML
    private Text textOffice3;

    @FXML
    private Text textOffice4;

    @FXML
    private Text textOffice5;

    @FXML
    private Text textOffice8;

    @FXML
    private Text textOffice7;

    @FXML
    private Text textOffice6;

    @FXML
    private Text textCandidate1;

    @FXML
    private Text textCandidate2;

    @FXML
    private Text textCandidate3;

    @FXML
    private Text textCandidate8;

    @FXML
    private Text textCandidate7;

    @FXML
    private Text textCandidate6;

    @FXML
    private Text textCandidate5;

    @FXML
    private Text textCandidate4;

    @FXML
    void initialize(Ballot ballot) {

        this.ballot=ballot;

        offices = new ArrayList<Text>();
        candidates = new ArrayList<Text>();

        offices.add(textOffice1);
        offices.add(textOffice2);
        offices.add(textOffice3);
        offices.add(textOffice4);
        offices.add(textOffice5);
        offices.add(textOffice6);
        offices.add(textOffice7);
        offices.add(textOffice8);

        candidates.add(textCandidate1);
        candidates.add(textCandidate2);
        candidates.add(textCandidate3);
        candidates.add(textCandidate4);
        candidates.add(textCandidate5);
        candidates.add(textCandidate6);
        candidates.add(textCandidate7);
        candidates.add(textCandidate8);

        for(int i = 0; i < ballot.getVotes().size();i++){
            offices.get(i).setText(ballot.getVotes().get(i).getOffice());
            candidates.get(i).setText(ballot.getVotes().get(i).getCandidate());

        }





    }

    @FXML
    void logOut() throws Exception{
        main.showLogInWindow();
    }

    public void setMain(Main main){
        this.main = main;
    }
    public void setBallot(Ballot ballot){ this.ballot = ballot; }
}

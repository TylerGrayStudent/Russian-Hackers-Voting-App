package com.csci360.electionapp.controller;

import com.csci360.electionapp.foundation.MySQLAccess;
import com.csci360.electionapp.model.Office;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ElectionResultsController {

    private Main main;

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
    private Text voteResultsText;

    private ArrayList<Text> offices;
    private ArrayList<Text> candidates;


    @FXML
    void exitClicked() throws Exception {
        main.showSettingsWindow();

    }

    @FXML
    void recountClicked() throws Exception{
        main.getBallotBox().recount(main.getElection());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Recount Issued");
        alert.showAndWait();
        initialize(main);
    }

    @FXML
    void initialize(Main main) throws Exception {
        MySQLAccess db = new MySQLAccess();
        HashMap<Office,String> results = db.getResults(main.getElection());

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

        Iterator it = results.entrySet().iterator();
        int i = 0;
        /*
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry) it.next();
            offices.get(i).setText(pair.getKey().);

            i++;
        }
        */
        for (Map.Entry<Office, String> entry : results.entrySet()) {
            Office key = entry.getKey();
            String value = entry.getValue();
            offices.get(i).setText(key.nameOfOffice);
            candidates.get(i).setText(value);
            i++;
        }
    }

    public void setMain(Main main) {
        this.main = main;
    }

}

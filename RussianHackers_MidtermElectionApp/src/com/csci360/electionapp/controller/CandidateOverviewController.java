package com.csci360.electionapp.controller;

import com.csci360.electionapp.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class CandidateOverviewController {

    private Main main;
    private Election election;
    private ArrayList<Text> labels;
    private ArrayList<ImageView> imgs;
    private Ballot ballot;
    private Voter voter;
    private ArrayList<Vote> votes;

    @FXML
    private ImageView img1;

    @FXML
    private ImageView img5;

    @FXML
    private ImageView img2;

    @FXML
    private ImageView img6;

    @FXML
    private ImageView img3;

    @FXML
    private ImageView img4;

    @FXML
    private ImageView img7;

    @FXML
    private ImageView img8;

    @FXML
    private Text label1;

    @FXML
    private Text label2;

    @FXML
    private Text label3;

    @FXML
    private Text label4;

    @FXML
    private Text label5;

    @FXML
    private Text label6;

    @FXML
    private Text label7;

    @FXML
    private Text label8;

    @FXML
    private Button submitButton;

    @FXML
    void submitClicked() throws Exception {
        if(votes.size() != main.getElection().getOffices().size()){
            return;
        }

        ballot = new Ballot(voter);
        for(Vote v: votes){
            ballot.addVote(v);
        }
        main.showBallotCastedScreen(ballot);

    }

    public void updateCandidate(int index, String name){
        labels.get(index).setText(name);
        Office o = main.getElection().getOffices().get(index);
        votes.add(index, new Vote(name,o.nameOfOffice));

    }

    public void updatePictureCount(int numOfCandidates){
        for(int i = numOfCandidates; i < imgs.size();i++){
            imgs.get(i).setImage(null);
        }

    }

    @FXML
    void initialize(){
        labels = new ArrayList<Text>();
        labels.add(label1);
        labels.add(label2);
        labels.add(label3);
        labels.add(label4);
        labels.add(label5);
        labels.add(label6);
        labels.add(label7);
        labels.add(label8);

        imgs = new ArrayList<ImageView>();
        imgs.add(img1);
        imgs.add(img2);
        imgs.add(img3);
        imgs.add(img4);
        imgs.add(img5);
        imgs.add(img6);
        imgs.add(img7);
        imgs.add(img8);

        //voter= new Voter("Tyler", "123");
        //voter = main.getMainVoter();
        //votes = new ArrayList<Vote>(main.getElection().getOffices().size());


    }

    public void setMain(Main main){
        this.main = main;
    }

    public void setVoter(Voter v){
        this.voter = v;
    }

    public void setElection(Election election){
        this.election = election;
        votes = new ArrayList<Vote>(election.getOffices().size());
    }
}

package com.csci360.electionapp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;

public class CandidatesEmptyController {

    private TabPane tab;
    private ArrayList<ImageView> imgs;

    @FXML
    private Text text;

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
    void nextButton(){
        tab.getSelectionModel().selectNext();
    }

    @FXML
    void radioSelected(){


    }

    @FXML
    void initialize(){
        //text.setText("Test");
        imgs = new ArrayList<ImageView>();
        imgs.add(img1);
        imgs.add(img2);
        imgs.add(img3);
        imgs.add(img4);
        imgs.add(img5);
        imgs.add(img6);
        imgs.add(img7);
        imgs.add(img8);
        text.setText("");

    }

    public void updatePictureCount(int numOfCandidates){
        for(int i = numOfCandidates; i < imgs.size();i++){
            imgs.get(i).setImage(null);
        }

    }

    public void updateCurrentlySelectedName(String name){
        text.setText(name);
    }

    public void setTab(TabPane tab) throws IOException { this.tab = tab; tab.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE); }
}

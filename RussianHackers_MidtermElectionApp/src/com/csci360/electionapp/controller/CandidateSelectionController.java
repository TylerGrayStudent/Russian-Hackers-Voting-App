package com.csci360.electionapp.controller;

import com.csci360.electionapp.model.Election;
import com.csci360.electionapp.model.Office;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import javax.swing.*;
import java.io.IOException;

public class CandidateSelectionController {

    private Main main;
    private TabPane tab;

    @FXML
    void submitClicked(MouseEvent event) throws Exception {
        main.initRootLayout();
        main.showBallotCastedScreen();
    }

    @FXML
    void initialize() {


    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setTab(TabPane tab) throws IOException { this.tab = tab; tab.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE); addTabs();}

    @FXML
    void nextButton(MouseEvent event) throws Exception{
        tab.getSelectionModel().selectNext();
       // addTabs();
        //main.nextTab();
    }

    public void addTabs() throws IOException {
        for(Office o: main.getElection().getOffices()){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/CandidatesEmptyUI.fxml"));
            GridPane candidates = (GridPane) loader.load();
            CandidatesEmptyController emptyController = loader.getController();
            emptyController.setTab(tab);
            ToggleGroup tg = new ToggleGroup();
            int col = 0;
            int row = 1;
            int i = 0;
            for(String candidate: o.getCandidates()){

                RadioButton rb = new RadioButton(candidate);
                rb.setText(candidate);
                rb.setToggleGroup(tg);
                candidates.add(rb,col,row);
                System.out.println(col + " , " + row);
                i++;
                col = i%4;
                if(i%4 == 0 && i > 3){
                    row++;
                    row++;
                }


            }



            Tab t = new Tab(o.getNameOfOffice());
            t.setContent(candidates);
            tab.getTabs().add(t);
        }
    }
}

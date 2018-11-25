package com.csci360.electionapp.controller;

import com.csci360.electionapp.model.Ballot;
import com.csci360.electionapp.model.Election;
import com.csci360.electionapp.model.Office;
import com.csci360.electionapp.model.Voter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class CandidateSelectionController {

    private Main main;
    private TabPane tab;
    private ToggleGroup tg;
    private CandidatesEmptyController emptyController;
    private ArrayList<CandidatesEmptyController> emptyControllersList = new ArrayList<CandidatesEmptyController>();

    @FXML
    private Label currentlySelectedName;

    @FXML
    void submitClicked(MouseEvent event) throws Exception {
        main.initRootLayout();
        main.showBallotCastedScreen();
    }

    @FXML
    void radioClicked(RadioButton rb){
        boolean isSelected = rb.isSelected();
        if(isSelected){
            //System.out.println(rb.getText());
            //currentlySelectedName.setText(rb.getText());
            CandidatesEmptyController controller = emptyControllersList.get(tab.getSelectionModel().getSelectedIndex());
            controller.updateCurrentlySelectedName(rb.getText());

        }

    }
    @FXML
    void initialize() {


    }

    public void setMain(Main main) {
        this.main = main;
        main.setBallot(new Ballot(new Voter("Temp" , "123")));
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
            emptyController = loader.getController();
            emptyController.setTab(tab);
            emptyControllersList.add(emptyController);
            tg = new ToggleGroup();
            int col = 0;
            int row = 1;
            int i = 0;
            for(String candidate: o.getCandidates()){

                RadioButton rb = new RadioButton(candidate);
                rb.setText(candidate);
                rb.setToggleGroup(tg);
                rb.selectedProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue ) {
                        radioClicked(rb);
                    }
                });
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
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../view/CandidateOverviewUI.fxml"));
        GridPane overview = (GridPane) loader.load();
        CandidateOverviewController controller = loader.getController();
        controller.setMain(main);
        Tab t = new Tab("Overview");
        t.setContent(overview);
        tab.getTabs().add(t);
    }
}

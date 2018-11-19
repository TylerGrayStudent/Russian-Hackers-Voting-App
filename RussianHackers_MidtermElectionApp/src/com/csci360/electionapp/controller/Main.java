package com.csci360.electionapp.controller;

import com.csci360.electionapp.model.Office;
import com.csci360.electionapp.model.Voter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private Voter mainVoter;
    private Office office;


    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Election System");

        initRootLayout();
       // showLogInWindow();
        showCandidateCreationScreen();


    }

    public void setMainVoter(Voter v){
        mainVoter = v;
    }

    public Voter getMainVoter(){
        return mainVoter;
    }

    public Office getOffice(){return office;}

    public void setOffice(Office o){office = o;}

    public void initRootLayout() {
        try {
            // Load root layout from fxml file.

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/rootUI.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showRegistrationWindow() throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/NewVoterRegistrationUI.fxml"));
            AnchorPane registrationWindow = (AnchorPane) loader.load();

            rootLayout.setCenter(registrationWindow);
            NewVoterRegistrationController controller = loader.getController();
            controller.setMain(this);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void showLogInWindow() throws Exception

    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/LoginUI.fxml"));
            //Accordion
            AnchorPane loginWindow = (AnchorPane) loader.load();
            rootLayout.setCenter(loginWindow);
            LogInUIController controller = loader.getController();
            controller.setMain(this);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void showBallotCastedScreen() throws Exception  {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/BallotCastedUI.fxml"));

            AnchorPane window = (AnchorPane) loader.load();
            rootLayout.setCenter(window);
            BallotCastedController controller = loader.getController();
            controller.setMain(this);

        } catch (Exception e) {
            e.printStackTrace();

        }


    }

    public void showCandidateCreationScreen() throws Exception  {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/CandidateCreationUI.fxml"));
            AnchorPane window = (AnchorPane) loader.load();
            rootLayout.setCenter(window);
            CandidateCreationController controller = loader.getController();
            controller.setMain(this);

        } catch (Exception e) {
            e.printStackTrace();

        }


    }

    public void showSettingsWindow() throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/SettingsUI.fxml"));
            BorderPane registrationWindow = (BorderPane) loader.load();
            rootLayout.setCenter(registrationWindow);
            /*
            Will be called from a controller. Just used to test for now
             */
            FXMLLoader load = new FXMLLoader();
            load.setLocation(Main.class.getResource("../view/ElectionResultsUI.fxml"));
            AnchorPane electionResults = (AnchorPane) load.load();
            registrationWindow.setCenter(electionResults);

            SettingsController controller = loader.getController();
            controller.setMain(this);

        } catch (Exception e) {
            e.printStackTrace();

        }


    }

    public void showCandidateSelectionScreen() throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/CandidateSelectionUI.fxml"));
            TabPane candidateSelectionWindow = (TabPane) loader.load();
            rootLayout.setCenter(candidateSelectionWindow);

            CandidateSelectionController controller = loader.getController();
            controller.setMain(this);



        } catch (Exception e) {
            e.printStackTrace();

        }


    }

    public void nextTab() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../view/CandidateSelectionUI.fxml"));
        TabPane candidateSelectionWindow = (TabPane) loader.load();
        rootLayout.setCenter(candidateSelectionWindow);

        CandidateSelectionController controller = loader.getController();
        controller.setMain(this);
        candidateSelectionWindow.getSelectionModel().selectNext();
    }



    public static void main(String[] args) {
        launch(args);
    }
}

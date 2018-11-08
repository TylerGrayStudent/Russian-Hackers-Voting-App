package com.csci360.electionapp.view;

import com.csci360.electionapp.foundation.MySQLAccess;
import com.csci360.electionapp.tech.security.Security;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import jdk.jfr.EventType;


import java.awt.event.MouseListener;
import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;


    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Election System");

        initRootLayout();
        showLogInWindow();


    }


    public void initRootLayout() {
        try {
            // Load root layout from fxml file.

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("RootUI.fxml"));
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
            loader.setLocation(Main.class.getResource("NewVoterRegistrationUI.fxml"));
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
            loader.setLocation(Main.class.getResource("LoginUI.fxml"));
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
            loader.setLocation(Main.class.getResource("BallotCastedUI.fxml"));

            AnchorPane window = (AnchorPane) loader.load();
            rootLayout.setCenter(window);
            BallotCastedController controller = loader.getController();
            controller.setMain(this);

        } catch (Exception e) {
            e.printStackTrace();

        }


    }

    public void showSettingsWindow() throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("SettingsUI.fxml"));
            BorderPane registrationWindow = (BorderPane) loader.load();
            rootLayout.setCenter(registrationWindow);
            /*
            Will be called from a controller. Just used to test for now
             */
            FXMLLoader load = new FXMLLoader();
            load.setLocation(Main.class.getResource("ElectionResultsUI.fxml"));
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
            loader.setLocation(Main.class.getResource("CandidateSelectionUI.fxml"));
            TabPane candidateSelectionWindow = (TabPane) loader.load();
            rootLayout.setCenter(candidateSelectionWindow);

            CandidateSelectionController controller = loader.getController();
            controller.setMain(this);



        } catch (Exception e) {
            e.printStackTrace();

        }


    }




    public static void main(String[] args) {
        launch(args);
    }
}

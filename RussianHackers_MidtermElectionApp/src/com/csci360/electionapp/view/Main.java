package com.csci360.electionapp.view;

import com.csci360.electionapp.foundation.MySQLAccess;
import com.csci360.electionapp.tech.security.Security;
import javafx.application.Application;
<<<<<<< HEAD
import javafx.event.Event;
import javafx.event.EventHandler;
=======
import javafx.fxml.FXML;
>>>>>>> 1f95eca4d11dcc4d7eb905b57041be969e7ed543
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
<<<<<<< HEAD



=======
        MySQLAccess db = new MySQLAccess();
      //  db.verifyLogIn("thing" , "thing");
>>>>>>> ed6d173b10b50cf25abab17cedc52b91156b6543

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
           // MouseEvent me = new MouseEvent;
            //MouseButton btn = new getButton(me);
           //// controller.
          //  if(controller.loginclicked(me))){
          ///      showCandidateSelectionScreen();
          //  }


        } catch (Exception e) {
            e.printStackTrace();

        }


    }
    private class MyEventHandler implements EventHandler<Event> {
        @Override
        public void handle(Event evt) {
            System.out.println(((Control)evt.getSource()).getId());
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



        } catch (Exception e) {
            e.printStackTrace();

        }


    }




    public static void main(String[] args) {
        launch(args);
    }
}

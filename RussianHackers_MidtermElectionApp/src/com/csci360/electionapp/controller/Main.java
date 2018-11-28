package com.csci360.electionapp.controller;

import com.csci360.electionapp.foundation.MySQLAccess;
import com.csci360.electionapp.model.*;
import com.csci360.electionapp.tech.security.Decryption;
import com.csci360.electionapp.tech.security.Encryption;
import com.csci360.electionapp.tech.security.Security;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private Voter mainVoter;
    private Office office;
    private Election election;
    private Ballot ballot;
    private UnofficalBallotBox ballotBox;
    /*TODO: Dynamically Generate the Candiadte Screen. Allow the user to tab through the tabs. Create way to store votes. Create Unoffical Tally. Create Official Tally.


     */

    public void createTempElection(){

        //Here for quick testing instead of making new Elections every test.
        election = new Election("Temp");
        election.addOffice(new Office("Temp"));
        office = election.getOfficeByName("Temp");
        office.addCandidate("Tyler");
        office.addCandidate("Tyler2");
        office.addCandidate("Tyler3");
        office.addCandidate("Tyler4");
        office.addCandidate("Tyler5");
        office.addCandidate("Tyler6");
        office.addCandidate("Tyler7");
        office.addCandidate("Tyler8");

        Office office2 = new Office("Temp3");
        office2.addCandidate("Tyler");
        office2.addCandidate("Tyler2");
        office2.addCandidate("Tyler3");
        office2.addCandidate("Tyler4");
        office2.addCandidate("Tyler5");
        office2.addCandidate("Tyler6");
        office2.addCandidate("Tyler7");
        office2.addCandidate("Tyler8");

        election.addOffice(office2);
        //election.publishElection();
        createBallotBox();

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Election System");
        createTempElection();
        initRootLayout();
        showLogInWindow();

    }


    public void addToUnofficalBox(Ballot ballot){
        ballotBox.addBallot(ballot);
    }
    public UnofficalBallotBox getBallotBox(){
        return ballotBox;
    }

    public void createBallotBox(){
        ballotBox = new UnofficalBallotBox(election.getOffices());
    }
    public Ballot getBallot(){
        return ballot;
    }
    public void setBallot(Ballot ballot){
        this.ballot = ballot;
    }
    public Election getElection(){return election;}

    public void setElection(Election elec){
        this.election = elec;
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

    public void showAdminLogInWindow() throws Exception

    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/AdminLogIn.fxml"));
            AnchorPane loginWindow = (AnchorPane) loader.load();
            rootLayout.setCenter(loginWindow);
            AdminLogInController controller = loader.getController();
            controller.setMain(this);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void showBallotCastedScreen(Ballot ballot) throws Exception  {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/BallotCastedUI.fxml"));

            AnchorPane window = (AnchorPane) loader.load();
            rootLayout.setCenter(window);
            BallotCastedController controller = loader.getController();
            controller.initialize(ballot);
            controller.setMain(this);
            //controller.setBallot(ballot);



        } catch (Exception e) {
            e.printStackTrace();

        }


    }

    public void showVoterInfoUpdateScreen(String username) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/SettingsUI.fxml"));
            BorderPane registrationWindow = (BorderPane) loader.load();
            rootLayout.setCenter(registrationWindow);

            FXMLLoader load = new FXMLLoader();
            load.setLocation(Main.class.getResource("../view/VoterInformationUI.fxml"));
            AnchorPane voterInfo = (AnchorPane) load.load();
            registrationWindow.setCenter(voterInfo);

            SettingsController controller = loader.getController();
            VoterUpdateController eController = load.getController();

            controller.setMain(this);
            eController.setMain(this);
            eController.initialize(username);

        } catch (Exception e) {
            e.printStackTrace();

        }




    }

    public void showCandidateCreationScreen(Office officeFromElection) throws Exception  {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/CandidateCreationUI.fxml"));
            AnchorPane window = (AnchorPane) loader.load();
            rootLayout.setCenter(window);
            CandidateCreationController controller = loader.getController();
            controller.setMain(this);
            controller.getOfficeData(officeFromElection);

        } catch (Exception e) {
            e.printStackTrace();

        }


    }

    public void showOfficeCreationScreen(Election election) throws Exception  {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/OfficeCreationUI.fxml"));
            AnchorPane window = (AnchorPane) loader.load();
            rootLayout.setCenter(window);
            OfficeCreationController controller = loader.getController();
            controller.setMain(this);
            controller.setElection(election);

        } catch (Exception e) {
            e.printStackTrace();

        }


    }

    public void showElectionResultsWindow() throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/SettingsUI.fxml"));
            BorderPane registrationWindow = (BorderPane) loader.load();
            rootLayout.setCenter(registrationWindow);

            FXMLLoader load = new FXMLLoader();
            load.setLocation(Main.class.getResource("../view/ElectionResultsUI.fxml"));
            AnchorPane electionResults = (AnchorPane) load.load();
            registrationWindow.setCenter(electionResults);

            SettingsController controller = loader.getController();
            ElectionResultsController eController = load.getController();

            controller.setMain(this);
            eController.setMain(this);
            eController.initialize(this);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void showSettingsWindow() throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/SettingsUI.fxml"));
            BorderPane registrationWindow = (BorderPane) loader.load();
            rootLayout.setCenter(registrationWindow);
           // FXMLLoader load = new FXMLLoader();
           // load.setLocation(Main.class.getResource("../view/ElectionResultsUI.fxml"));
          //  AnchorPane electionResults = (AnchorPane) load.load();
          //  ElectionResultsController eController = load.getController();
         //   eController.initialize(this);

         //   registrationWindow.setCenter(electionResults);




            SettingsController controller = loader.getController();
            controller.setMain(this);

        } catch (Exception e) {
            e.printStackTrace();

        }


    }

    public void showElectionCreatorWindow() throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/SettingsUI.fxml"));
            BorderPane registrationWindow = (BorderPane) loader.load();
            rootLayout.setCenter(registrationWindow);

            FXMLLoader load = new FXMLLoader();
            load.setLocation(Main.class.getResource("../view/ElectionCreationUI.fxml"));
            AnchorPane electionCreation = (AnchorPane) load.load();
            registrationWindow.setCenter(electionCreation);

            SettingsController controller = loader.getController();
            ElectionCreationController eController = load.getController();

            controller.setMain(this);
            eController.setMain(this);

            eController.updateText();

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
            controller.setTab(candidateSelectionWindow);



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

package com.csci360.electionapp.view;

//import java.awt.*;

import com.gluonhq.charm.glisten.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class logInUIController {

    private Main main;


    @FXML
    private TextField userName;

    @FXML
    private TextField authentication;

    @FXML
    private TextField password;

    @FXML
    private Button registerButton;

    @FXML
    private Button logInButton;

    @FXML
    private TextArea text;

    public  logInUIController(){

    }
    @FXML
    void loginclicked(MouseEvent event) {
        System.out.println("User Info");
        System.out.println(userName.getText());
        System.out.println(password.getText());
        System.out.println(authentication.getText());

    }

    @FXML
    void registerclicked(MouseEvent event) throws Exception {
        main.showRegistationWindow();;
    }

    @FXML
    void initialize() {

    }

    public void setMain(Main main){
        this.main = main;
    }
}

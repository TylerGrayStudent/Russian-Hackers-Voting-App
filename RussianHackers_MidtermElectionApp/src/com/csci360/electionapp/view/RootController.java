package com.csci360.electionapp.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RootController {

    @FXML
    private Label topbartitle;

    public void setTopbartitle(String s) {
        this.topbartitle.setText(s);
    }

}

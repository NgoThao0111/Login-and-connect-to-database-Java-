package com.example.login_signup.controller;

import com.example.login_signup.Main;
import com.example.login_signup.database.UserDAO;
import com.example.login_signup.session.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

import static javafx.scene.text.TextAlignment.CENTER;

public class LoggedInController {
    public LoggedInController() {
    }

    Integer userID = SessionManager.getInstance().getUserID();
    @FXML
    private Label label_welcome;

    public void initialize() {
        Integer userID = SessionManager.getInstance().getUserID();
        if (userID != null) {
            UserDAO userDAO = new UserDAO();
            label_welcome.setText("Welcome " + userDAO.getFirstname(userID) + " " + userDAO.getLastname(userID) + "!");
            label_welcome.setTextAlignment(CENTER);
        }
    }

    public void userLogout(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("login.fxml");
    }
}
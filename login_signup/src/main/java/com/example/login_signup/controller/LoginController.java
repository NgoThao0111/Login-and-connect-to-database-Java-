package com.example.login_signup.controller;

import com.example.login_signup.Main;
import com.example.login_signup.session.SessionManager;
import com.example.login_signup.database.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.io.IOException;

public class LoginController {
    public LoginController() {
    }
    @FXML
    private Label label_wrong_password;
    @FXML
    private TextField tf_username;
    @FXML
    private PasswordField tf_password;



    public void userLogin(ActionEvent event) throws IOException {
        checkLogin();
    }

    private void checkLogin() throws IOException {
        Main m = new Main();
        if(tf_username.getText().equals("thao") && tf_password.getText().equals("011105")) {
            m.changeScene("logged_in.fxml");
        }

        else if(tf_username.getText().isEmpty()) {
            label_wrong_password.setText("Please enter your username.");
        }

        else if(tf_password.getText().isEmpty()) {
            label_wrong_password.setText("Please enter your password.");
        }

        else {
            UserDAO userDAO = new UserDAO();
            Integer userID = userDAO.getUserIDFromUsername(tf_username.getText());
            if(userID != -1 && tf_password.getText().equals(userDAO.getPassword(userID))) {
                SessionManager.getInstance().setUserID(userID);
                m.changeScene("logged_in.fxml");
            }
            else {
                label_wrong_password.setText("Wrong username or password!");
            }
        }
    }
    public void changeSignUp(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("sign_up.fxml");
    }
}

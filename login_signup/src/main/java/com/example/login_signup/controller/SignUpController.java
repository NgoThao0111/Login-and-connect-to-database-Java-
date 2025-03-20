package com.example.login_signup.controller;

import com.example.login_signup.Main;
import com.example.login_signup.session.SessionManager;
import com.example.login_signup.database.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SignUpController {
    public SignUpController() {
    }
    @FXML
    private Label label_warning;
    @FXML
    private TextField tf_firstname;
    @FXML
    private TextField tf_lastname;
    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_phone_number;
    @FXML
    private PasswordField tf_password;
    @FXML
    private PasswordField tf_confirm_password;

    public void userSignUp(ActionEvent event) throws IOException {
        checkSignUp();
    }

    private void checkSignUp() throws IOException {
        Main m = new Main();

        if(tf_firstname.getText().isEmpty()) {
            label_warning.setText("Please enter your firstname.");
        }

        else if(tf_firstname.getText().length() > 255) {
            label_warning.setText("Firstname must be 255 characters or fewer.");
        }

        else if(tf_lastname.getText().isEmpty()) {
            label_warning.setText("Please enter your lastname.");
        }

        else if(tf_lastname.getText().length() > 255) {
            label_warning.setText("Lastname must be 255 characters or fewer.");
        }

        else if(tf_username.getText().isEmpty()) {
            label_warning.setText("Please enter your username.");
        }

        else if(tf_username.getText().length() < 6) {
            label_warning.setText("Username must be at least 6 characters long.");
        }

        else if(tf_username.getText().length() > 255) {
            label_warning.setText("Username must be 255 characters or fewer.");
        }

        else if(!tf_username.getText().matches("[a-zA-Z0-9@#$%^&*_+!]+")) {
            label_warning.setText("Username can only include letters, numbers, and @ # $ % ^ & * _ + !.");
        }

        else if(tf_phone_number.getText().isEmpty()) {
            label_warning.setText("Please enter your phone number.");
        }

        else if(!tf_phone_number.getText().matches("[0-9]+")) {
            label_warning.setText("Phone number can only include numbers.");
        }

        else if(tf_phone_number.getText().length() > 15) {
            label_warning.setText("Phone number must be 15 characters or fewer.");
        }

        else if(tf_password.getText().isEmpty()) {
            label_warning.setText("Please enter your password.");
        }

        else if(tf_confirm_password.getText().isEmpty()) {
            label_warning.setText("Please confirm your password.");
        }

        else if(!tf_confirm_password.getText().equals(tf_password.getText())) {
            label_warning.setText("Confirm password does not match.");
        }

        else if(tf_password.getText().length() < 6) {
            label_warning.setText("Password must be at least 6 characters long.");
        }

        else if(!tf_password.getText().matches("[a-zA-Z0-9@#$%^&*_+!]+")) {
            label_warning.setText("Password can only include letters, numbers, and @ # $ % ^ & * _ + !.");
        }

        else {
            UserDAO userDAO = new UserDAO();
            Integer userID = userDAO.getUserIDFromUsername(tf_username.getText());
            if(userID != -1) {
                label_warning.setText("Username already exists.");
            }
            else {
                userDAO.createUser(tf_username.getText(), tf_password.getText(), tf_firstname.getText(), tf_lastname.getText(), tf_phone_number.getText());
                userID = userDAO.getUserIDFromUsername(tf_username.getText());
                SessionManager.getInstance().setUserID(userID);
                m.changeScene("logged_in.fxml");
            }
        }
    }
    public void changeLogin(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("login.fxml");
    }
}

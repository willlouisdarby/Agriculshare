package Agriculshare.Controller;

import Agriculshare.Application;
import Agriculshare.Model.DBManagement.DatabaseConnection;
import Agriculshare.Model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

public class RegisterViewController {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField2;

    @FXML
    private Button registerButton;

    @FXML
    private Label errorLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField surnameLabel;

    @FXML
    private TextField firstnameLabel;

    @FXML
    private TextField emailLabel;

    @FXML
    void register(ActionEvent event) {
        System.out.println("Registering user");
        String username = usernameField.getText();
        String password = passwordField.getText();
        String password2 = passwordField2.getText();
        String firstname = firstnameLabel.getText();
        String surname = surnameLabel.getText();
        String email = emailLabel.getText();

        if (username == null || password == null || password2 == null || firstname == null || surname == null || email == null) {
            errorLabel.setText("Please ensure all fields are entered");
        } else if (!password.equals(password2)) {
            errorLabel.setText("Passwords do not match");
            passwordField.setStyle("-fx-border-color: red");
            passwordField2.setStyle("-fx-border-color: red");
        } else {
            MessageDigest md;
            byte[] salt = new byte[16];
            byte[] hashedPassword = new byte[32];

            StringBuilder sb = new StringBuilder();
            try {
                md = MessageDigest.getInstance("SHA-256");

                SecureRandom random = new SecureRandom();

                random.nextBytes(salt);

                md.update(salt);

                hashedPassword = md.digest(password.getBytes());


                for (byte b : hashedPassword)
                    sb.append(String.format("%02x", b));

                System.out.println(sb);
                System.out.println(hashedPassword);




            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            String sql = "INSERT INTO User(Username, Firstname, Surname, Email, PasswordHash, Salt) VALUES (?,?,?,?,?,?)";

            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, firstname);
                preparedStatement.setString(3, surname);
                preparedStatement.setString(4, email);
                preparedStatement.setBytes(5, hashedPassword);
                preparedStatement.setBytes(6, salt);

                System.out.println(preparedStatement);
                preparedStatement.execute();


                Application.user = new User(username, firstname, surname, email);
                SwitchScenes.DASHBOARD.SwitchScene(event);

            } catch (SQLIntegrityConstraintViolationException e) {
                usernameField.setStyle("-fx-border-color: red");
                errorLabel.setText("Username already exists");
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public RegisterViewController() {
        connection = DatabaseConnection.getConnection();
    }

}
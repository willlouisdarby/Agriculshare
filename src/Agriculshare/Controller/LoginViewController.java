package Agriculshare.Controller;

import Agriculshare.Model.DBManagement.UserManagement;
import Agriculshare.Model.DBManagement.DatabaseConnection;
import Agriculshare.Controller.SwitchScenes;
import Agriculshare.Application;
import Agriculshare.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class LoginViewController {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @FXML
    private Button loginButton;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button registerButton;

    @FXML
    private Label errorLabel;

    @FXML
    void login(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        StringBuilder sb;

        MessageDigest md;

        String sql = "SELECT * FROM User WHERE Username = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            System.out.println(preparedStatement);
            resultSet = preparedStatement.executeQuery();

            if(!resultSet.next()){
                errorLabel.setText("Incorrect username");
            }else {

                byte[] dbHashedPassword = resultSet.getBytes("PasswordHash");
                byte[] dbSalt = resultSet.getBytes("Salt");
                String dbUsername = resultSet.getString("Username");

                try {
                    md = MessageDigest.getInstance("SHA-256");
                    md.reset();

                    md.update(dbSalt);

                    byte[] hashedPassword = md.digest(password.getBytes());


                    System.out.println(dbUsername);

                    StringBuilder sb2 = new StringBuilder();

                    for (byte b : dbHashedPassword)
                        sb2.append(String.format("%02x", b));

                    StringBuilder sb3 = new StringBuilder();

                    for (byte b : hashedPassword)
                        sb3.append(String.format("%02x", b));

                    System.out.println(sb2);
                    System.out.println(sb3);

                    if(sb2.toString().equals(sb3.toString())) {
                        System.out.println("Logging in");
                        Application.user = new User(resultSet.getString("Username"), resultSet.getString("Firstname"), resultSet.getString("Surname"), resultSet.getString("Email"));
                        SwitchScenes.DASHBOARD.SwitchScene(event);
                    } else {
                        errorLabel.setText("Incorrect password");
                    }

                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openRegister(ActionEvent e) {
        SwitchScenes.REGISTER.SwitchScene(e);
    }

    public LoginViewController() {
        connection = DatabaseConnection.getConnection();
    }

}

package Agriculshare.Model.DBManagement;

import Agriculshare.Model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserManagement {

    private String username;
    private String password;

    public User[] users;


    public UserManagement(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean isRegistered() {
        return true;
    }

//    public boolean checkRegistered(String username) {
//        // Check if user is registered
//    }
//
//    public boolean registerUser() {
//        // Register user
//    }
//
//    public boolean loginUser() {
//        // Login existing user
//        if (isRegistered()) {
//
//        }
//    }

    public void loadUsers() {
        // Load user profiles
        DatabaseConnection connect = new DatabaseConnection();
        Connection connection = connect.getConnection();

        String connectQuery = "SELECT * FROM User";

        try {
            Statement statement = connection.createStatement();
            ResultSet output = statement.executeQuery(connectQuery);

            while (output.next()) {
                int userID = output.getInt("UserID");
                String username = output.getString("Username");
                String firstname = output.getString("Firstname");
                String surname = output.getString("Surname");
                String email = output.getString("Email");

                System.out.println("User: " + username + firstname + surname + email);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

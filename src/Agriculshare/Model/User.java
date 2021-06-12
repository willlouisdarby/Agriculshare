package Agriculshare.Model;

public class User {
    private String username;
    private String firstname;
    private String surname;
    private String email;

    public User(String username, String firstname, String surname, String email) {
        this.username = username;
        this.firstname = firstname;
        this.surname = surname;
        this.email = email;
    }

    //////// ACCESSOR METHODS ////////////

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    //////////////////////////////////

//    public boolean isRegistered() {
//        // Check if user is registered
//    }

}

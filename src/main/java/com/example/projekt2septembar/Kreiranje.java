package com.example.projekt2september;

public class Create {

    String ID;
    String Username;
    String E-Mail;
    String Password;

    public Create(String ID, String username, String email, String password) {

        this.ID = ID;
        this.Username = username;
        this.Email = email;
        this.Password = password;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}

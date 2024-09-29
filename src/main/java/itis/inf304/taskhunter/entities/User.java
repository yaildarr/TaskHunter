package itis.inf304.taskhunter.entities;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String email;
    private String password;
    private String username;
    private String photoURL;
    private String number;

    public User(int id,String email, String password, String username, String photoURL, String number) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.photoURL = photoURL;
        this.number = number;
    }

    public User(String email, String password, String username, String photoURL, String number) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.photoURL = photoURL;
        this.number = number;
    }

    public User(String email, String password, String username, String number) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.number = number;
    }

    public User(int id,String email, String password, String username, String number) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.number = number;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}

package itis.inf304.taskhunter.dto;


public class UserRegistrationDto {
    private String username;
    private String email;
    private String number;
    private String password;
    private String photoURL;

    public UserRegistrationDto(String login, String email, String number, String password) {
        this.username = login;
        this.email = email;
        this.number = number;
        this.password = password;
    }

    public UserRegistrationDto(String username, String email, String number, String password, String photoURL) {
        this.username = username;
        this.email = email;
        this.number = number;
        this.password = password;
        this.photoURL = photoURL;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getNumber() {
        return number;
    }

    public String getPassword() {
        return password;
    }

    public String getPhotoURL() {
        return photoURL;
    }
}
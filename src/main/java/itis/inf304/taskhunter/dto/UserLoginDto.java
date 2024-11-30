package itis.inf304.taskhunter.dto;


public class UserLoginDto{
    private String email;
    private String password;

    public UserLoginDto(String login, String password) {
        this.email = login;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}

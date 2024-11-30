package itis.inf304.taskhunter.entities;

public class Message {
    private Integer userId;
    private String name;
    private String email;
    private String message;


    public Message(Integer userId, String name, String email, String message) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.message = message;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMessage() {
        return message;
    }
}


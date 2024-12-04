package itis.inf304.taskhunter.dto;

public class UserForJobDto {
    private int id;
    private String username;
    private String number;

    public UserForJobDto(int id, String username, String number) {
        this.id = id;
        this.username = username;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getNumber() {
        return number;
    }
}

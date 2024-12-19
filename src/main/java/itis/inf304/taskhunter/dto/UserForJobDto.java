package itis.inf304.taskhunter.dto;

public class UserForJobDto {
    private int id;
    private String username;
    private String number;
    private String photoURL;

    public UserForJobDto(int id, String username, String number, String photoURL) {
        this.id = id;
        this.username = username;
        this.number = number;
        this.photoURL = photoURL;
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

    public String getPhotoURL() {
        return photoURL;
    }
}

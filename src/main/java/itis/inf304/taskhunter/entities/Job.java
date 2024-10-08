package itis.inf304.taskhunter.entities;


public class Job {
    private final int id;
    private final int userId;
    private final String title;
    private final String location;
    private final String description;
    private final Double payment;
    private final int categoryId;
    private final String createdAt;

    public Job(int id, int userId, String title, String location, String description, Double payment, int categoryId, String createdAt) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.location = location;
        this.description = description;
        this.payment = payment;
        this.categoryId = categoryId;
        this.createdAt = createdAt;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Double getPayment() {
        return payment;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getTitle() {
        return title;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

}


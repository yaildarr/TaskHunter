package itis.inf304.taskhunter.dto;

public class CreateJobDto {
    private final int userId;
    private final String title;
    private final String location;
    private final String description;
    private final Double payment;
    private final int categoryId;
    private final String createdAt;

    public CreateJobDto(int userId, String title, String location, String description, Double payment, int categoryId, String createdAt) {
        this.userId = userId;
        this.title = title;
        this.location = location;
        this.description = description;
        this.payment = payment;
        this.categoryId = categoryId;
        this.createdAt = createdAt;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public Double getPayment() {
        return payment;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}

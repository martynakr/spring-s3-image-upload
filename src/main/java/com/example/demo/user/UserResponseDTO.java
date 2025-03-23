package com.example.demo.user;

public class UserResponseDTO {
    private Long id;
    private String firstName;
    private String imageUrl;

    public UserResponseDTO() {}

    public UserResponseDTO(Long id, String firstName, String imageUrl) {
        this.id = id;
        this.firstName = firstName;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

package com.example.demo.user;

import org.springframework.web.multipart.MultipartFile;

public class CreateUserDTO {

    private String firstName;
    private MultipartFile profileImage;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setProfileImage(MultipartFile profileImage) {
        this.profileImage = profileImage;
    }

    public MultipartFile getProfileImage() {
        return profileImage;
    }

}

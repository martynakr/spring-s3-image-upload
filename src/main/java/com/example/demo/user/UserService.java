package com.example.demo.user;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.S3.S3Service;

import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;

@Service
public class UserService {

    private final S3Service s3Service;
    private UserRepository userRepository;

    UserService(UserRepository userRepo, S3Service s3Service) {
        this.userRepository = userRepo;
        this.s3Service = s3Service;
    }

    private UserResponseDTO mapToDto(User user) {
        String bucketName = "springimageupload";
        String presignedUrl = this.s3Service.getPresignedUrl(bucketName, user.getImageKey());
        UserResponseDTO userResponse = new UserResponseDTO(user.getId(), user.getFirstName(), presignedUrl);
        return userResponse;
    }

    public List<UserResponseDTO> getAll() {
        return this.userRepository.findAll().stream().map((user) -> this.mapToDto(user)).collect(Collectors.toList());
    }

    public UserResponseDTO createUser(CreateUserDTO userData) throws Exception {
        String bucketName = "springimageupload";
        String keyName;
        String imageUrl = null;
        User newUser = new User();
        newUser.setFirstName(userData.getFirstName());
        if (userData.getProfileImage() != null) {
            try {
                keyName = this.s3Service.uploadImage(userData.getProfileImage(), bucketName);
                newUser.setImageKey(keyName);
                imageUrl = this.s3Service.getPresignedUrl(bucketName, keyName);
            } catch (AwsServiceException | SdkClientException | IOException e) {
                e.printStackTrace();
                throw e;
            }
        }
        User saved = this.userRepository.save(newUser);

        UserResponseDTO userResponse = new UserResponseDTO(saved.getId(), saved.getFirstName(), imageUrl);

        return userResponse;

    }

}

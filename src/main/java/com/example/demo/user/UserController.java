package com.example.demo.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestParam("file") MultipartFile file,
            @RequestParam("firstName") String name) throws Throwable {
        CreateUserDTO createUser = new CreateUserDTO();
        createUser.setFirstName(name);
        createUser.setProfileImage(file);
        UserResponseDTO user = this.userService.createUser(createUser);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAll() {
        return new ResponseEntity<>(this.userService.getAll(), HttpStatus.OK);
    }
    

}

package sportpath.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sportpath.ApiResponse;
import sportpath.dao.UserDAOImpl;
import sportpath.models.User;
import sportpath.services.UserRegistrationService;
import sportpath.services.UserValidationService;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserValidationService userValidationService;
    private final UserRegistrationService userRegistrationService;

    @Autowired
    public UserController(UserValidationService userValidationService, UserRegistrationService userRegistrationService) {
        this.userValidationService = userValidationService;
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> addUser(@RequestBody User user) {
        int status = userValidationService.isDataValid(user);
        return userRegistrationService.registerUser(user, status);
    }
}

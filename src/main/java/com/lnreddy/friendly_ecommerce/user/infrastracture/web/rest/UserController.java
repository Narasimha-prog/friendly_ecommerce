package com.lnreddy.friendly_ecommerce.user.infrastracture.web.rest;

import com.lnreddy.friendly_ecommerce.user.application.UserApplicationService;
import com.lnreddy.friendly_ecommerce.user.application.dio.RegisterUserRequest;
import com.lnreddy.friendly_ecommerce.user.application.dio.UserView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    private final UserApplicationService userApplicationService;

    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    // -----------------------------
    // Register a new user
    // -----------------------------
    @PostMapping("/register")
    public ResponseEntity<UserView> register(@RequestBody RegisterUserRequest request) {
        UserView userView = userApplicationService.registerUser(request);
        return ResponseEntity.ok(userView);
    }

    // -----------------------------
    // Find user by ID
    // -----------------------------
    @GetMapping("/{id}")
    public ResponseEntity<UserView> findById(@PathVariable UUID id) {
        UserView userView = userApplicationService.findByUserId(id);
        return ResponseEntity.ok(userView);
    }

    // -----------------------------
    // Authenticate user (validate credentials)
    // -----------------------------
    @PostMapping("/authenticate")
    public ResponseEntity<UserView> authenticate(
            @RequestParam String email,
            @RequestParam String password
    ) {
        UserView userView = userApplicationService.authenticateUser(email, password);
        return ResponseEntity.ok(userView);
    }
}

package com.lnreddy.friendlyecommerce.user.infrastracture.web.rest;

import com.lnreddy.friendlyecommerce.user.application.UserApplicationService;
import com.lnreddy.friendlyecommerce.user.application.dio.AuthUserRequest;
import com.lnreddy.friendlyecommerce.user.application.dio.RegisterUserRequest;
import com.lnreddy.friendlyecommerce.user.application.dio.UserView;
import com.lnreddy.friendlyecommerce.user.domain.port.in.IUserController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@Slf4j
@Tag(name = "User Management", description = "Operations for users")
public class UserController implements IUserController {

    private final UserApplicationService userApplicationService;

    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    // -----------------------------
    // Register a new user
    // -----------------------------
    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Creates a new user and returns the user details")
    public ResponseEntity<UserView> register(@RequestBody RegisterUserRequest request) {
        UserView userView = userApplicationService.registerUser(request);
        return ResponseEntity.ok(userView);
    }

    // -----------------------------
    // Find user by ID
    // -----------------------------
    @Operation(summary = "Find user using user-id", description = "Fetches the user details by their unique UUID")
    @GetMapping("/{id}")

    public ResponseEntity<UserView> findById(@PathVariable UUID id) {
        UserView userView = userApplicationService.findByUserId(id);
        return ResponseEntity.ok(userView);
    }

    // -----------------------------
    // Authenticate user (validate credentials)
    // -----------------------------
    @Operation(summary = "Authenticate user", description = "Validates user credentials")
    @PostMapping("/authenticate")
    public ResponseEntity<UserView> authenticate(@RequestBody AuthUserRequest request) {
        log.info("Request to UserController : {} - {}",request.email(),request.password());
        UserView userView = userApplicationService.authenticateUser(request.email(), request.password());
        return ResponseEntity.ok(userView);
    }

}

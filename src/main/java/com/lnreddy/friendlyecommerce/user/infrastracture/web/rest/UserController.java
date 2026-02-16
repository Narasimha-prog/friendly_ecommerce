package com.lnreddy.friendlyecommerce.user.infrastracture.web.rest;

import com.lnreddy.friendlyecommerce.shared.util.JwtUtil;
import com.lnreddy.friendlyecommerce.user.application.PasswordRestApplicationService;
import com.lnreddy.friendlyecommerce.user.application.UserApplicationService;
import com.lnreddy.friendlyecommerce.user.application.dio.*;
import com.lnreddy.friendlyecommerce.user.domain.port.in.IUserController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@Slf4j
@Tag(name = "User Management", description = "Operations for users")
public class UserController implements IUserController {


    private final long  jwtExpireTime;
    private final JwtUtil jwtUtil;
    private final UserApplicationService userApplicationService;

    private final PasswordRestApplicationService restApplicationService;


    public UserController(
            JwtUtil jwtUtil,
            UserApplicationService userApplicationService,
            @Value("${jwt.expiration}") long jwtExpireTime,
            PasswordRestApplicationService restApplicationService
    ) {
        this.jwtUtil = jwtUtil;
        this.userApplicationService = userApplicationService;
        this.jwtExpireTime = jwtExpireTime;
        this.restApplicationService = restApplicationService;
    }

    // Register a new user

    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Creates a new user and returns the user details")
    public ResponseEntity<UserView> register(@RequestBody RegisterUserRequest request) {
        log.info("USER_REGISTER_REQUEST email={}", request.email());
        UserView userView = userApplicationService.registerUser(request);
        URI uri = URI.create("/users/" + userView.userId());
        log.info("USER_REGISTER_SUCCESS userId={}", userView.userId());
        return ResponseEntity.created(uri).body(userView);
    }

    // Find user by ID
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Find user using user-id", description = "Fetches the user details by their unique UUID",
            security = @io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/{id}")

    public ResponseEntity<UserView> findById(@PathVariable UUID id) {
        // Debug the current user's roles
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        assert auth != null;
        auth.getAuthorities().forEach(a -> log.info("User role: {}", a.getAuthority()));
        UserView userView = userApplicationService.findByUserId(id);
        return ResponseEntity.ok(userView);
    }



    // Login user
    @PostMapping("/login")
    @Operation(summary = "login user", description = "login user to get jwt token")
    public  ResponseEntity<AuthUserResponse> login(@RequestBody AuthUserRequest request) {
        log.info("USER_LOGIN_REQUEST email={}", request.email());
        UserView userView = userApplicationService.authenticateUser(request.email(), request.password());// check password
        Set<String> roles=userApplicationService.rolesFromUserId(UUID.fromString(userView.userId()));
        String token = jwtUtil.generateToken(userView.email(), roles);
        long now=System.currentTimeMillis();
        log.info("USER_LOGIN_SUCCESS userId={}", userView.userId());
        return ResponseEntity.ok().body(
                new AuthUserResponse(token,"Bearer",jwtExpireTime,now)
        );  // generate JWT
    }

    @PostMapping("/forgot-password")
    @Operation(summary = "for forget password",description = "to change password using email")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgetPasswordRequest request) {
        restApplicationService.requestReset(request.email());
        return ResponseEntity.ok("Reset link sent to email if it exists.");
    }

    @PostMapping("/reset-password")
    @Operation(summary = "for password  reset",description = "to change password using new email")
    public ResponseEntity<Void> resetPassword(
            @RequestBody ResetPasswordRequest request
    ) {
        restApplicationService.resetPassword(
                request.token(),
                request.newPassword()
        );
        return ResponseEntity.ok().build();
    }

}

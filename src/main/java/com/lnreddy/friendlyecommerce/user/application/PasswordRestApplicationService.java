package com.lnreddy.friendlyecommerce.user.application;

import com.lnreddy.friendlyecommerce.user.domain.exception.InvalidTokenException;
import com.lnreddy.friendlyecommerce.user.domain.exception.TokenExpiredException;
import com.lnreddy.friendlyecommerce.user.domain.exception.UserNotFound;
import com.lnreddy.friendlyecommerce.user.domain.model.aggrigate.PasswordResetToken;
import com.lnreddy.friendlyecommerce.user.domain.model.aggrigate.User;
import com.lnreddy.friendlyecommerce.user.domain.model.valueobject.Email;
import com.lnreddy.friendlyecommerce.user.domain.model.valueobject.Password;
import com.lnreddy.friendlyecommerce.user.domain.model.valueobject.ResetToken;
import com.lnreddy.friendlyecommerce.user.domain.model.valueobject.UserId;
import com.lnreddy.friendlyecommerce.user.domain.port.out.IMailService;
import com.lnreddy.friendlyecommerce.user.domain.port.out.IPasswordHasher;
import com.lnreddy.friendlyecommerce.user.domain.port.out.IPasswordResetTokenRepository;
import com.lnreddy.friendlyecommerce.user.domain.port.out.IUserRepository;
import com.lnreddy.friendlyecommerce.user.infrastracture.persistence.entity.PasswordResetTokenEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordRestApplicationService {

    private final IPasswordResetTokenRepository tokenRepository;
    private final IUserRepository userRepository;
    private final IMailService mailService;
    private final IPasswordHasher passwordHasher;

    @Transactional
    public void requestReset(String email) {

       userRepository.findByEmail(new Email(email))
                .ifPresent(user -> {

                    String token = UUID.randomUUID().toString();
                    Instant expiry = Instant.now().plus(Duration.ofMinutes(15));

                    tokenRepository.save(
                             new ResetToken(token),
                            user.getId(),
                            expiry
                    );

                    mailService.sendMail(
                            email,
                            "Reset password",
                            "Click link: http://localhost:8080/reset?token=" + token
                    );



                }  );
    }

    @Transactional
    public void resetPassword(String token, String newPassword) {

        PasswordResetToken resetToken =
                tokenRepository.findPasswordResetTokenByToken(new ResetToken(token))
                        .orElseThrow(() -> new InvalidTokenException("In"));

        if (resetToken.getExpiry().isBefore(Instant.now())) {
            throw new TokenExpiredException("Token is expired...");
        }

        User user = userRepository.findById(resetToken.getUserId())
                .orElseThrow(() -> new UserNotFound("User not found"));

        user.changePassword(Password.fromPlainText(newPassword,passwordHasher )); // should hash inside domain or use port

        userRepository.save(user);

        tokenRepository.deleteByToken(  new ResetToken(token));
    }

    }
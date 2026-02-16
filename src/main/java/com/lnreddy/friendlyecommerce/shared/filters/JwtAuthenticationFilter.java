package com.lnreddy.friendlyecommerce.shared.filters;

import com.lnreddy.friendlyecommerce.shared.security.CustomeUserDetails;
import com.lnreddy.friendlyecommerce.shared.util.JwtUtil;
import com.lnreddy.friendlyecommerce.user.application.UserApplicationService;
import com.lnreddy.friendlyecommerce.user.domain.model.aggrigate.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    private final UserApplicationService userService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
log.info("incoming request for jwtFilter: {}",request.getRequestURI());
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            if (jwtUtil.validateToken(token)) {

                String username = jwtUtil.getUsername(token);

                // 1️⃣ Get domain user
                User domainUser = userService.findByUserEmail(username);

                // 2️⃣ Wrap in UserDetails
                CustomeUserDetails userDetails = new CustomeUserDetails(domainUser);

                // 3️⃣ Set Authentication with proper authorities
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                SecurityContextHolder.getContext().setAuthentication(authToken);

                log.info(
                        "Authentication is success for: {}",
                        request.getRequestURI()
                );
                userDetails.getAuthorities().forEach(a -> log.info(
                        "Granted: {}",
                        a.getAuthority()
                ));
            }
        }
        filterChain.doFilter(request,response);

    }

}

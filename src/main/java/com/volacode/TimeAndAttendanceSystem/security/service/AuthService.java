package com.volacode.TimeAndAttendanceSystem.security.service;

import com.volacode.TimeAndAttendanceSystem.models.AppUser;
import com.volacode.TimeAndAttendanceSystem.security.JwtService;
import com.volacode.TimeAndAttendanceSystem.security.config.SecureUser;
import com.volacode.TimeAndAttendanceSystem.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;

    public AuthResponse login(AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException e) {
            throw new RuntimeException("invalid username or password");
        }
        AppUser user = userService.findUserByEmail(authRequest.getEmail());
        SecureUser secureUser = new SecureUser(user);
        String token = jwtService.generateToken(secureUser);
        return new AuthResponse(token);
    }
}

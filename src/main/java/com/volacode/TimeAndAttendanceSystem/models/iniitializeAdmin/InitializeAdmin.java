package com.volacode.TimeAndAttendanceSystem.models.iniitializeAdmin;

import com.volacode.TimeAndAttendanceSystem.models.AppUser;
import com.volacode.TimeAndAttendanceSystem.models.Role;
import com.volacode.TimeAndAttendanceSystem.repositories.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class InitializeAdmin {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initAdmin() {
        if (!appUserRepository.existsByEmail("admin@gmail.com")) {
            appUserRepository.save(
                    AppUser.builder()
                            .role(Role.ADMIN)
                            .password(passwordEncoder.encode("P@$$word123"))
                            .email("admin@gmail.com")
                            .build()
            );
        }

    }
}

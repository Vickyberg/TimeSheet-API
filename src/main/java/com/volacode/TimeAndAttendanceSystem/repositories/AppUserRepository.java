package com.volacode.TimeAndAttendanceSystem.repositories;

import com.volacode.TimeAndAttendanceSystem.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {

    Optional<AppUser> findByEmail(String email);

    boolean existsByEmail(String email);
    boolean existsAppUserByEmail(String email);

}

package com.volacode.TimeAndAttendanceSystem.repositories;

import com.volacode.TimeAndAttendanceSystem.models.TAAUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TAAUserRepository extends JpaRepository<TAAUser,Long> {


    Optional<TAAUser> findByEmail(String email);
}

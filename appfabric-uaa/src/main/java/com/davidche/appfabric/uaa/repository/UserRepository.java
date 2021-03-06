package com.davidche.appfabric.uaa.repository;

import com.davidche.appfabric.uaa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByPhone(String phone);

    Optional<User> findByPhone(String phone);

}

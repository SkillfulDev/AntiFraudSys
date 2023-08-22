package ua.chernonog.working.antifraud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;
import ua.chernonog.working.antifraud.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCase(String username);

    Optional<UserEntity> findByUsernameIgnoreCase(String username);

//    Optional<User> findByUsernameIgnoreCase(String username);







}
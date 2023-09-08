package ua.chernonog.working.antifraud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.chernonog.working.antifraud.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByNameIgnoreCase(String username);

    boolean existsByUsernameIgnoreCase(String username);

    Optional<UserEntity> findByUsernameIgnoreCase(String username);

    long deleteByUsernameIgnoreCase(String username);



}
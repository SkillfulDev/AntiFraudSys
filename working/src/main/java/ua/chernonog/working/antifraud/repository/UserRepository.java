package ua.chernonog.working.antifraud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.chernonog.working.antifraud.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
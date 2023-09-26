package ua.chernonog.working.antifraud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ua.chernonog.working.antifraud.entity.UserEntity;
import ua.chernonog.working.antifraud.model.emuns.Role;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Transactional
    @Modifying
    @Query("update UserEntity u set u.status = ?1 where u.username = ?2")
    void updateStatusByUsername(String status, String username);

    @Transactional
    @Modifying
    @Query("update UserEntity u set u.username = ?1 where u.status = ?2")
    void updateUsernameByStatus(String username, String status);

    @Transactional
    @Modifying
    @Query("update UserEntity u set u.role = ?1 where u.username = ?2")
    void updateRoleByUsername(Role role, String username);

    @Transactional
    @Modifying
    @Query("update UserEntity u set u.role = ?1")
    void updateRoleBy(Role role);


    boolean existsByNameIgnoreCase(String username);

    boolean existsByUsernameIgnoreCase(String username);

    Optional<UserEntity> findByUsernameIgnoreCase(String username);

    void deleteByUsernameIgnoreCase(String username);


}
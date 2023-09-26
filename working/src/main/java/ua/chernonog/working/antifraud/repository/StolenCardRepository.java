package ua.chernonog.working.antifraud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.chernonog.working.antifraud.entity.StolenCardEntity;

import java.util.Optional;

public interface StolenCardRepository extends JpaRepository<StolenCardEntity,Long> {
    StolenCardEntity findFirstByIdOrderByNumberAsc(Long id);
    boolean existsByNumberIgnoreCase(String number);

    Optional<StolenCardEntity> findByNumber(String number);


}

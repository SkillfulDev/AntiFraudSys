package ua.chernonog.working.antifraud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.chernonog.working.antifraud.entity.IpEntity;
import ua.chernonog.working.antifraud.model.request.IpRequest;

import java.util.Optional;


public interface IpRepository extends JpaRepository<IpEntity, Long> {
    boolean existsByIp(String ip);

    Optional<IpEntity> findByIp(String ip);


}

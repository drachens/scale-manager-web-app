package com.marsol.balanzaappweb.repository;

import com.marsol.balanzaappweb.model.Scale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface ScaleRepository extends JpaRepository<Scale, Long> {
    Optional<Scale> findByIpBalanza(String ipString);
}

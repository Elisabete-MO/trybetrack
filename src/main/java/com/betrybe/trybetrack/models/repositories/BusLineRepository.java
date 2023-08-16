package com.betrybe.trybetrack.models.repositories;

import com.betrybe.trybetrack.models.entities.BusLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusLineRepository extends JpaRepository<BusLine, Long> {
}

package com.betrybe.trybetrack.models.repositories;

import com.betrybe.trybetrack.models.entities.BusLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusLineRepository extends JpaRepository<BusLine, Long>, RevisionRepository<BusLine, Long, Long> {
  Optional<BusLine> findByCode(String code);

  @Query(
      value = "SELECT bl.* FROM bus_lines AS bl " +
          "JOIN station_bus_lines as sbl ON bl.id = sbl.bus_line_id " +
          "WHERE sbl.station_id = :id",
      nativeQuery = true
  )
  List<BusLine> findBusLinesFromStationId(@Param("id") Long id);

  @Query(value = "SELECT DISTINCT bl FROM BusLine bl " +
      "JOIN FETCH bl.schedules s " +
      "WHERE s.available = true")
  List<BusLine> findBusLinesWithAvailableSchedules();
}

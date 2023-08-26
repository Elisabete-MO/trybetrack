package com.betrybe.trybetrack.models.repositories;

import com.betrybe.trybetrack.models.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

  UserDetails findByUsername(String username);
}

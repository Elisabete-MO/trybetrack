package com.betrybe.trybetrack.services;

import com.betrybe.trybetrack.models.entities.Person;
import com.betrybe.trybetrack.models.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements UserDetailsService {

  private final PersonRepository personRepository;

  @Autowired
  public PersonService(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  public Person insert(Person person) {
    String hashedPassword = new BCryptPasswordEncoder().encode(person.getPassword());
    person.setPassword(hashedPassword);

    return personRepository.save(person);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return personRepository.findByUsername(username);
  }
}

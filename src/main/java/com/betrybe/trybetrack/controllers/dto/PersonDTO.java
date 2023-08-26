package com.betrybe.trybetrack.controllers.dto;

import com.betrybe.trybetrack.models.entities.Person;

public record PersonDTO(Long id, String name, String email,
                        String username, String password, String role, int age) {
  public Person toEntity(){
    return new Person(id, name, email, username, password, role, age);
  }
}

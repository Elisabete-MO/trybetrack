package com.betrybe.trybetrack.controllers;

import com.betrybe.trybetrack.controllers.dto.AuthenticationDTO;
import com.betrybe.trybetrack.controllers.dto.PersonDTO;
import com.betrybe.trybetrack.controllers.dto.ResponseDTO;
import com.betrybe.trybetrack.models.entities.Person;
import com.betrybe.trybetrack.services.PersonService;
import com.betrybe.trybetrack.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  private final AuthenticationManager authenticationManager;

  private final PersonService personService;

  private final TokenService tokenService;

  @Autowired
  public AuthenticationController(AuthenticationManager authenticationManager,
                                  PersonService personService, TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.personService = personService;
    this.tokenService = tokenService;
  }

  @PostMapping("/register")
  public ResponseEntity<ResponseDTO<Person>> register(@RequestBody PersonDTO personDTO){
    UserDetails userDetails = personService.loadUserByUsername(personDTO.username());
    if(userDetails != null) {
      ResponseDTO<Person> response = new ResponseDTO<>(
          "Não foi possível cadastrar, nome de usuário já existe!", null);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    Person person = personService.insert(personDTO.toEntity());
    ResponseDTO<Person> response = new ResponseDTO<>(
        "Pessoa cadastrada com sucesso!", person);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PostMapping("/login")
  public ResponseEntity<ResponseDTO<String>> login(@RequestBody AuthenticationDTO authenticationDTO){

    UsernamePasswordAuthenticationToken usernamePassword =
        new UsernamePasswordAuthenticationToken(authenticationDTO.username(), authenticationDTO.password());
    Authentication auth = authenticationManager.authenticate(usernamePassword);
    Person person = (Person) auth.getPrincipal();

    String token = tokenService.generateToken(person);

    ResponseDTO<String> response = new ResponseDTO<>("Pessoa autenticada com sucesso!", token);

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

}

package com.betrybe.trybetrack.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.betrybe.trybetrack.models.entities.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

  @Value("${api.security.token.secret}") //application-local.properties
  private String secret;

  public String generateToken(Person person){
    Algorithm algorithm = Algorithm.HMAC256(secret);
    return JWT.create()
        .withIssuer("trybetrack") //quem criou o token
        .withSubject(person.getUsername()) //proprietário do token
        .withExpiresAt(generateExpirationDate())
        .sign(algorithm);
  }

  private Instant generateExpirationDate(){
    return LocalDateTime.now()
        .plusHours(2)
        .toInstant(ZoneOffset.of("-03:00"));
  }

  public String validateToken(String token) {
    Algorithm algorithm = Algorithm.HMAC256(secret);
    return JWT.require(algorithm)
        .withIssuer("trybetrack")
        .build()
        .verify(token)
        .getSubject();
  }
}

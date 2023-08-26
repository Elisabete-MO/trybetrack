package com.betrybe.trybetrack.listeners;

import com.betrybe.trybetrack.models.entities.Schedule;
import com.betrybe.trybetrack.services.impl.EmailService;
import jakarta.persistence.PostUpdate;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class ScheduleListener {

  private EmailService emailService;

  @Autowired
  public ScheduleListener(EmailService emailService) {
    this.emailService = emailService;
  }

  @PostUpdate
  public void postUpdate(Schedule schedule){
    String message = String.format("O horário da linha %s foi alterada para %s às %s",
        schedule.getBusLine().getCode(),
        schedule.getDepartureDate().toString(),
        schedule.getDepartureTime().toString());

    emailService.sendEmail("jn@gmail.com", "Alteração de data", message);
  }
}

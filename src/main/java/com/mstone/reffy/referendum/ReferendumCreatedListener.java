package com.mstone.reffy.referendum;

import com.mstone.reffy.email.EmailService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ReferendumCreatedListener {
  private final ReferendumRepository referendums;
  private final EmailService emailService;

  public ReferendumCreatedListener(ReferendumRepository referendums, EmailService emailService) {
    this.referendums = referendums;
    this.emailService = emailService;
  }

  @EventListener
  @Async
  public void onReferendumCreated(ReferendumCreatedEvent e) {
    var referendum = referendums.getOne(e.getReferendumId());
    emailService.sendAdminEmail(
      "New referendum: " + referendum.getQuestion(),
      String.format(
        "A new referendum was created:\n\n%s\n\n%s",
        referendum.getQuestion(),
        referendum.getDescription()
      )
    );
  }
}

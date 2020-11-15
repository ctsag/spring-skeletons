package gr.nothingness.springskeletons.datarestmicroservices.tickets.bootstrap;

import gr.nothingness.springskeletons.datarestmicroservices.tickets.domain.Ticket;
import gr.nothingness.springskeletons.datarestmicroservices.tickets.repositories.TicketRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class TicketsApplicationLoader implements ApplicationListener<ContextRefreshedEvent> {

  private TicketRepository repository;

  @Autowired
  public void setTicketRepository(TicketRepository repository) {
    this.repository = repository;
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    repository.save(createTicket("App1: Add the ability to sort by release date", "A URGENT: The users need this new feature", 1, 2));
    repository.save(createTicket("App2: Updates are not saved correctly to task name", "This is a bug impacting this feature in production", 1, 4));
    repository.save(createTicket("App3: Provide a list of applications for users", "The users need this new feature", 2, 1));
    repository.save(createTicket("App4: Provide a list of tickets for an application", "The users need this new feature", 2, 2));
    repository.save(createTicket("App5: Provide a list of tickets for a given release", "The users need this new feature", 3, 3));
    repository.save(createTicket("App6: Give user the ability to add tickets to a release", "The users need this new feature", 4, 3));
    repository.save(createTicket("App7: Add the ability to sort by release date", "The users need this new feature", 1, 1));
    repository.save(createTicket("App8: Updates are not saved correctly to task name", "B URGENT: This is a bug impacting this feature in production", 1, 2));
    repository.save(createTicket("App9: Provide a list of applications for users", "The users need this new feature", 2, 2));
    repository.save(createTicket("App10: Provide a list of tickets for an application", "The users need this new feature", 2, 2));
    repository.save(createTicket("App11: Provide a list of tickets for a given release", "The users need this new feature", 3, 3));
    repository.save(createTicket("App12: Give user the ability to add tickets to a release", "The users need this new feature", 4, 5));
    repository.save(createTicket("App13: Add the ability to sort by release date", "The users need this new feature", 1, 5));
    repository.save(createTicket("App14: Updates are not saved correctly to task name", "C URGENT: This is a bug impacting this feature in production", 1, 3));
    repository.save(createTicket("App15: Provide a list of applications for users", "The users need this new feature", 2, 1));
    repository.save(createTicket("App16: Provide a list of tickets for an application", "The users need this new feature", 2, 1));
    repository.save(createTicket("App17: Provide a list of tickets for a given release", "The users need this new feature", 3, 5));
    repository.save(createTicket("App18: Give user the ability to add tickets to a release", "The users need this new feature", 4, 4));
    repository.save(createTicket("App19: Add the ability to sort by release date", "The users need this new feature", 1, 3));
    repository.save(createTicket("App20: Updates are not saved correctly to task name", "This is a bug impacting this feature in production", 1, 3));
    repository.save(createTicket("App21: Provide a list of applications for users", "The users need this new feature", 2, 3));
    repository.save(createTicket("App22: Provide a list of tickets for an application", "The users need this new feature", 2, 3));
    repository.save(createTicket("App23: Provide a list of tickets for a given release", "The users need this new feature", 3, 3));
    repository.save(createTicket("App24: Give user the ability to add tickets to a release", "The users need this new feature", 4, 1));
    repository.save(createTicket("App25: Add the ability to sort by release date", "D URGENT: The users need this new feature", 1, 2));
    repository.save(createTicket("App26: Updates are not saved correctly to task name", "This is a bug impacting this feature in production", 1, 3));
    repository.save(createTicket("App27: Provide a list of applications for users", "The users need this new feature", 2, 1));
    repository.save(createTicket("App28: Provide a list of tickets for an application", "The users need this new feature", 2, 5));
    repository.save(createTicket("App29: Provide a list of tickets for a given release", "The users need this new feature", 3, 5));
    repository.save(createTicket("App30: Give user the ability to add tickets to a release", "E URGENT: The users need this new feature", 4, 1));
  }

  private Ticket createTicket(String title, String description, Integer appId, Integer userId) {
    Ticket ticket = new Ticket();

    ticket.setTitle(title);
    ticket.setDescription(description);
    ticket.setAppId(appId);

    return ticket;
  }

}

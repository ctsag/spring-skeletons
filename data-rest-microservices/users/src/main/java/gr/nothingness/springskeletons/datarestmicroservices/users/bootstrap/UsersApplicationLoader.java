package gr.nothingness.springskeletons.datarestmicroservices.users.bootstrap;

import gr.nothingness.springskeletons.datarestmicroservices.users.domain.User;
import gr.nothingness.springskeletons.datarestmicroservices.users.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class UsersApplicationLoader implements ApplicationListener<ContextRefreshedEvent> {

  private UserRepository repository;

  @Autowired
  public void setApplicationRepository(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    repository.save(createUser("Jane Doe", "Business Owner"));
    repository.save(createUser("Mary Doe", "Scrum Master"));
    repository.save(createUser("Kate Doe", "Developer"));
    repository.save(createUser("John Doe", "QA Tester"));
    repository.save(createUser("Mark Doe", "Business Analyst"));
  }

  private User createUser(String name, String role) {
    User user = new User();

    user.setName(name);
    user.setRole(role);

    return user;
  }

}

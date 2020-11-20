package gr.nothingness.springskeletons.datarestmicroservices.applications.bootstrap;

import gr.nothingness.springskeletons.datarestmicroservices.applications.domain.Application;
import gr.nothingness.springskeletons.datarestmicroservices.applications.repositories.ApplicationRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class ApplicationsApplicationLoader implements ApplicationListener<ContextRefreshedEvent> {

  private ApplicationRepository repository;

  @Autowired
  public void setApplicationRepository(ApplicationRepository repository) {
    this.repository = repository;
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    repository.save(createApplication("TrackZilla", "Bug tracking app", 1));
    repository.save(createApplication("Order Management", "Bug tracking app", 1));
    repository.save(createApplication("Expense Reporting", "Bug tracking app", 2));
    repository.save(createApplication("Schedule Manager", "Bug tracking app", 1));
    repository.save(createApplication("Time Tracker", "Online timesheet", 2));
  }

  private Application createApplication(String name, String description, Integer ownerId) {
    Application application = new Application();

    application.setName(name);
    application.setDescription(description);
    application.setOwnerId(ownerId);

    return application;
  }

}

package gr.nothingness.springskeletons.datarestmicroservices.applications.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.nothingness.springskeletons.datarestmicroservices.applications.domain.Application;
import gr.nothingness.springskeletons.datarestmicroservices.applications.repositories.ApplicationRepository;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@BasePathAwareController
@RequiredArgsConstructor
@Log4j2
public class ApplicationController {

  private final ApplicationRepository repository;

  @RequestMapping(path = "apps", method = RequestMethod.GET, produces = "application/hal+json")
  public @ResponseBody ResponseEntity<?> getApplications() {
    List<Application> applications = repository.findAll();
    log.info("Application count: " + applications.size());

    applications.forEach(app -> getPersonInfo(app));

    CollectionModel<Application> collectionModel = CollectionModel.of(applications);
    collectionModel.add(linkTo(methodOn(ApplicationController.class).getApplications()).withSelfRel());

    return ResponseEntity.ok(collectionModel);
  }

  @RequestMapping(path = "apps/{id}", method = RequestMethod.GET, produces = "application/hal+json")
  public @ResponseBody ResponseEntity<?> getApplication(@PathVariable Integer id) {
    Optional<Application> application = repository.findById(id);

    if (application.isPresent()) {
      getPersonInfo(application.get());
      EntityModel<Application> entityModel = EntityModel.of(application.get());
      entityModel.add(linkTo(methodOn(ApplicationController.class).getApplication(id)).withSelfRel());

      return ResponseEntity.ok(entityModel);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  private void getPersonInfo(Application application) {
    try {
      RestTemplate restTemplate = new RestTemplate();
      String userManagementService = "http://localhost:50002/users/" + application.getOwnerId();
      ResponseEntity<String> response = restTemplate.getForEntity(userManagementService, String.class);
      ObjectMapper mapper = new ObjectMapper();
      JsonNode root = mapper.readTree(response.getBody());
      JsonNode name = root.path("name");
      JsonNode role = root.path("role");
      application.setOwnerName(name.asText());
      application.setOwnerRole(role.asText());
    } catch (IOException ex) {
      application.setOwnerName("Undefined");
      application.setOwnerRole("Undefined");
    }
  }

}

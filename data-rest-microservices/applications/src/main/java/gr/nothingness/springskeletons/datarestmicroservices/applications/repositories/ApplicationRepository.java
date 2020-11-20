package gr.nothingness.springskeletons.datarestmicroservices.applications.repositories;

import gr.nothingness.springskeletons.datarestmicroservices.applications.domain.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "apps")
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

}

package gr.nothingness.springskeletons.restfuldatajpaservice.repositories;

import gr.nothingness.springskeletons.restfuldatajpaservice.entities.AuthenticatedUser;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface AuthenticatedUserRepository extends CrudRepository<AuthenticatedUser, UUID> {

  public Optional<AuthenticatedUser> findByUsername(String username);

}

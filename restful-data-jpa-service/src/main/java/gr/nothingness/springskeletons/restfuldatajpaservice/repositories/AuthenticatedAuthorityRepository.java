package gr.nothingness.springskeletons.restfuldatajpaservice.repositories;

import gr.nothingness.springskeletons.restfuldatajpaservice.entities.AuthenticatedAuthority;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface AuthenticatedAuthorityRepository extends CrudRepository<AuthenticatedAuthority, UUID> {

}

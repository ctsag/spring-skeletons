package gr.nothingness.springskeletons.datarestmicroservices.users.repositories;

import gr.nothingness.springskeletons.datarestmicroservices.users.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "users")
public interface UserRepository extends JpaRepository<User, Integer> {

}

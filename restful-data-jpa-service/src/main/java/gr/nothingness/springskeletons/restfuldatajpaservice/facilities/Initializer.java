package gr.nothingness.springskeletons.restfuldatajpaservice.facilities;

import gr.nothingness.springskeletons.restfuldatajpaservice.entities.AuthenticatedUser;
import gr.nothingness.springskeletons.restfuldatajpaservice.repositories.AuthenticatedUserRepository;
import java.util.UUID;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Initializer implements SmartInitializingSingleton {

  private final AuthenticatedUserRepository repository;
  private final PasswordEncoder encoder;

  public Initializer(AuthenticatedUserRepository repository, PasswordEncoder encoder) {
    this.repository = repository;
    this.encoder = encoder;
  }

  @Override
  public void afterSingletonsInstantiated() {
    UUID joshId = UUID.fromString("d0e7af9c-10ff-402c-9f16-7cd61e6297ec");
    UUID carolId = UUID.fromString("0e9ee411-fe40-475a-a194-87368531a9bf");

    AuthenticatedUser josh = new AuthenticatedUser(joshId, "josh", encoder.encode("josh"), true);
    AuthenticatedUser carol = new AuthenticatedUser(carolId, "carol", encoder.encode("carol"), true);

    josh.addAuthority("READ");
    carol.addAuthority("READ");
    carol.addAuthority("WRITE");

    repository.save(josh);
    repository.save(carol);
  }

}

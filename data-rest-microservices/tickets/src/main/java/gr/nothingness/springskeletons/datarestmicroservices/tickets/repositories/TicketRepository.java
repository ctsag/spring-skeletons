package gr.nothingness.springskeletons.datarestmicroservices.tickets.repositories;

import gr.nothingness.springskeletons.datarestmicroservices.tickets.domain.Ticket;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "bugs")
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

  @RestResource(path = "apps")
  public List<Ticket> findByAppId(Integer id);

  @RestResource(path = "findByDescription")
  public Page<Ticket> findByDescriptionIgnoreCaseContaining(String description, Pageable page);

  @RestResource(exported = false)
  @Override
  public void deleteById(Integer id);

  @RestResource(exported = false)
  @Override
  public void delete(Ticket ticket);

}

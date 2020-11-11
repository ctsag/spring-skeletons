package gr.nothingness.springskeletons.datatests.entities;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class FlightTest {

  @Autowired
  private EntityManager entityManager;

  @Test
  public void verifyFlightCanBeSaved() {
    Flight flight = new Flight();

    flight.setOrigin("Amsterdam");
    flight.setDestination("New York");
    flight.setScheduledAt(LocalDateTime.parse("2011-12-13T12:12:00"));

    entityManager.persist(flight);

    TypedQuery<Flight> query = entityManager.createQuery("SELECT f FROM Flight f", Flight.class);
    List<Flight> results = query.getResultList();

    assertThat(results)
        .hasSize(1)
        .first()
        .isEqualTo(flight);
  }

}

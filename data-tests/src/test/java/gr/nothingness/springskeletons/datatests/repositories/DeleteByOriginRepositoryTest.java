package gr.nothingness.springskeletons.datatests.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import gr.nothingness.springskeletons.datatests.entities.Flight;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class DeleteByOriginRepositoryTest {

  @Autowired
  private FlightRepository repository;

  @Test
  public void shouldSaveFlight() {
    Flight toDelete = createFlightByOrigin("London");
    Flight toKeep = createFlightByOrigin("Paris");

    repository.save(toDelete);
    repository.save(toKeep);

    repository.deleteByOrigin("London");

    assertThat(repository.findAll())
        .hasSize(1)
        .first()
        .isEqualToComparingFieldByField(toKeep);
  }

  private Flight createFlightByOrigin(String origin) {
    return createFlight(origin, "Madrid", LocalDateTime.parse("2011-12-13T12:00:00"));
  }

  private Flight createFlight(String origin, String destination, LocalDateTime scheduledAt) {
    Flight flight = new Flight();

    flight.setOrigin(origin);
    flight.setDestination(destination);
    flight.setScheduledAt(scheduledAt);

    return flight;
  }

}

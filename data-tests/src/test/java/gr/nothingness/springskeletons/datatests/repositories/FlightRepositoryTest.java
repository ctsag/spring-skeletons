package gr.nothingness.springskeletons.datatests.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import gr.nothingness.springskeletons.datatests.entities.Flight;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class FlightRepositoryTest {

  @Autowired
  private FlightRepository repository;

  @BeforeEach
  public void setUp() {
    repository.deleteAll();
  }

  @Test
  public void shouldPerformCRUDOperatoins() {
    Flight flight = new Flight();

    flight.setOrigin("London");
    flight.setDestination("New York");
    flight.setScheduledAt(LocalDateTime.parse("2011-12-13T12:12:00"));

    repository.save(flight);

    assertThat(repository.findAll())
        .hasSize(1)
        .first()
        .isEqualToComparingFieldByField(flight);

    repository.deleteById(flight.getId());

    assertThat(repository.count())
        .isZero();
  }

  @Test
  public void shouldFindFlightsFromLondon() {
    Flight flight1 = createFlight("London");
    Flight flight2 = createFlight("London");
    Flight flight3 = createFlight("New York");

    repository.save(flight1);
    repository.save(flight2);
    repository.save(flight3);

    List<Flight> flights = repository.findByOrigin("London");

    assertThat(flights).hasSize(2);
    assertThat(flights.get(0)).isEqualToComparingFieldByField(flight1);
    assertThat(flights.get(1)).isEqualToComparingFieldByField(flight2);
  }

  @Test
  public void shouldFindFlightsFromLondonToParis() {
    Flight flight1 = createFlight("London", "Paris");
    Flight flight2 = createFlight("London", "New York");
    Flight flight3 = createFlight("Madrid", "Paris");

    repository.save(flight1);
    repository.save(flight2);
    repository.save(flight3);

    List<Flight> flights = repository.findByOriginAndDestination("London", "Paris");

    assertThat(flights)
        .hasSize(1)
        .first()
        .isEqualToComparingFieldByField(flight1);
  }

  @Test
  public void shouldFindFlightsFromLondonOrParis() {
    Flight flight1 = createFlight("London", "New York");
    Flight flight2 = createFlight("Tokyo", "New York");
    Flight flight3 = createFlight("Paris", "New York");

    repository.save(flight1);
    repository.save(flight2);
    repository.save(flight3);

    List<Flight> flights = repository.findByOriginIn("London", "Paris");

    assertThat(flights).hasSize(2);
    assertThat(flights.get(0)).isEqualToComparingFieldByField(flight1);
    assertThat(flights.get(1)).isEqualToComparingFieldByField(flight3);
  }

  @Test
  public void shouldFindFlightsFromLondonIgnoringCase() {
    Flight flight = createFlight("LONDON");

    repository.save(flight);

    List<Flight> flights = repository.findByOriginIgnoreCase("London");

    assertThat(flights)
        .hasSize(1)
        .first()
        .isEqualToComparingFieldByField(flight);
  }

  private Flight createFlight(String origin, String destination) {
    Flight flight = new Flight();

    flight.setOrigin(origin);
    flight.setDestination(destination);
    flight.setScheduledAt(LocalDateTime.parse("2011-12-13T12:00:00"));

    return flight;
  }

  private Flight createFlight(String origin) {
    return createFlight(origin, "Madrid");
  }


}

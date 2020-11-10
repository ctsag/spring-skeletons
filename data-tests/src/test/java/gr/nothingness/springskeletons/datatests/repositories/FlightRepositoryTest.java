package gr.nothingness.springskeletons.datatests.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.domain.Sort.Direction.DESC;

import gr.nothingness.springskeletons.datatests.entities.Flight;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

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
    Flight flight1 = createFlightByOrigin("London");
    Flight flight2 = createFlightByOrigin("London");
    Flight flight3 = createFlightByOrigin("New York");

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
    Flight flight1 = createFlightByOriginAndDestination("London", "Paris");
    Flight flight2 = createFlightByOriginAndDestination("London", "New York");
    Flight flight3 = createFlightByOriginAndDestination("Madrid", "Paris");

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
    Flight flight1 = createFlightByOriginAndDestination("London", "New York");
    Flight flight2 = createFlightByOriginAndDestination("Tokyo", "New York");
    Flight flight3 = createFlightByOriginAndDestination("Paris", "New York");

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
    Flight flight = createFlightByOrigin("LONDON");

    repository.save(flight);

    List<Flight> flights = repository.findByOriginIgnoreCase("London");

    assertThat(flights)
        .hasSize(1)
        .first()
        .isEqualToComparingFieldByField(flight);
  }

  @Test
  public void shouldSortFlightsByDestination() {
    Flight flight1 = createFlightByDestination("New York");
    Flight flight2 = createFlightByDestination("Madrid");
    Flight flight3 = createFlightByDestination("Paris");

    repository.save(flight1);
    repository.save(flight2);
    repository.save(flight3);

    Iterable<Flight> flights = repository.findAll(Sort.by("destination"));
    Iterator<Flight> flightIterator = flights.iterator();

    assertThat(flights).hasSize(3);
    assertThat(flightIterator.next().getDestination()).isEqualTo("Madrid");
    assertThat(flightIterator.next().getDestination()).isEqualTo("New York");
    assertThat(flightIterator.next().getDestination()).isEqualTo("Paris");
  }

  @Test
  public void shouldSortFlightsByDestinationAndScheduledAt() {
    LocalDateTime now = LocalDateTime.now();
    Flight flight1 = createFlightByDestinationAndScheduledAt("Paris", now);
    Flight flight2 = createFlightByDestinationAndScheduledAt("Paris", now.plusHours(2));
    Flight flight3 = createFlightByDestinationAndScheduledAt("Paris", now.minusHours(1));
    Flight flight4 = createFlightByDestinationAndScheduledAt("London", now);
    Flight flight5 = createFlightByDestinationAndScheduledAt("London", now.plusHours(1));

    repository.save(flight1);
    repository.save(flight2);
    repository.save(flight3);
    repository.save(flight4);
    repository.save(flight5);

    Iterable<Flight> flights = repository.findAll(Sort.by("destination", "scheduledAt"));
    Iterator<Flight> flightIterator = flights.iterator();

    assertThat(flights).hasSize(5);
    assertThat(flightIterator.next()).isEqualToComparingFieldByField(flight4);
    assertThat(flightIterator.next()).isEqualToComparingFieldByField(flight5);
    assertThat(flightIterator.next()).isEqualToComparingFieldByField(flight3);
    assertThat(flightIterator.next()).isEqualToComparingFieldByField(flight1);
    assertThat(flightIterator.next()).isEqualToComparingFieldByField(flight2);
  }

  @Test
  public void shouldPageResults() {
    for (int i = 0; i < 50; i++) {
      repository.save(createFlightByOrigin(String.valueOf(i)));
    }

    Page<Flight> page = repository.findAll(PageRequest.of(2, 5));

    assertThat(page.getTotalElements()).isEqualTo(50);
    assertThat(page.getNumberOfElements()).isEqualTo(5);
    assertThat(page.getTotalPages()).isEqualTo(10);
    assertThat(page.getContent())
        .extracting(Flight::getOrigin)
        .containsExactly("10", "11", "12", "13", "14");
  }

  @Test
  public void shouldPageAndSortResults() {
    for (int i = 0; i < 50; i++) {
      repository.save(createFlightByDestination(String.valueOf(i)));
    }

    Page<Flight> page = repository.findAll(PageRequest.of(2, 5, Sort.by(DESC, "destination")));

    assertThat(page.getTotalElements()).isEqualTo(50);
    assertThat(page.getNumberOfElements()).isEqualTo(5);
    assertThat(page.getTotalPages()).isEqualTo(10);
    assertThat(page.getContent())
        .extracting(Flight::getDestination)
        .containsExactly("44", "43", "42", "41", "40");
  }

  @Test
  public void shouldPageAndSortADerivedQuery() {
    for (int i = 0; i < 10; i++) {
      Flight flight = createFlightByDestination(String.valueOf(i));
      flight.setOrigin("Paris");
      repository.save(flight);
    }

    for (int i = 0; i < 10; i++) {
      Flight flight = createFlightByDestination(String.valueOf(i));
      flight.setOrigin("London");
      repository.save(flight);
    }

    Page<Flight> page = repository.findByOrigin(
        "London",
        PageRequest.of(0, 5, Sort.by(DESC, "destination"))
    );

    assertThat(page.getTotalElements()).isEqualTo(10);
    assertThat(page.getNumberOfElements()).isEqualTo(5);
    assertThat(page.getTotalPages()).isEqualTo(2);
    assertThat(page.getContent())
        .extracting(Flight::getDestination)
        .containsExactly("9", "8", "7", "6", "5");
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

  private Flight createFlightByDestination(String destination) {
    return createFlight("London", destination, LocalDateTime.parse("2011-12-13T12:00:00"));
  }

  private Flight createFlightByOriginAndDestination(
      String origin,
      String destination
  ) {
    return createFlight(origin, destination, LocalDateTime.parse("2011-12-13T12:00:00"));
  }

  private Flight createFlightByDestinationAndScheduledAt(
      String destination,
      LocalDateTime scheduledAt
  ) {
    return createFlight("London", destination, scheduledAt);
  }


}

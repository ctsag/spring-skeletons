package gr.nothingness.springskeletons.datatests.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import gr.nothingness.springskeletons.datatests.entities.Flight;
import gr.nothingness.springskeletons.datatests.services.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FlightServiceTest {

  @Autowired
  private FlightRepository repository;

  @Autowired
  private FlightService service;

  @BeforeEach
  public void setUp() {
    repository.deleteAll();
  }

  @Test
  public void shouldNotRollbackWhenNoTransaction() {
    try {
      service.save(new Flight());
    } catch (Exception e) {
      // Do nothing
    } finally {
      assertThat(repository.findAll()).isNotEmpty();
    }
  }

  @Test
  public void shouldRollbackWhenInTransaction() {
    try {
      service.saveTransactional(new Flight());
    } catch (Exception e) {
      // Do nothing
    } finally {
      assertThat(repository.findAll()).isEmpty();
    }
  }

}

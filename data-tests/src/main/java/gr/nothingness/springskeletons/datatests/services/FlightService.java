package gr.nothingness.springskeletons.datatests.services;

import gr.nothingness.springskeletons.datatests.entities.Flight;
import gr.nothingness.springskeletons.datatests.repositories.FlightRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlightService {

  private final FlightRepository repository;

  public void save(Flight flight) {
    repository.save(flight);
    throw new RuntimeException("I failed");
  }

  @Transactional
  public void saveTransactional(Flight flight) {
    repository.save(flight);
    throw new RuntimeException("I failed");
  }

}

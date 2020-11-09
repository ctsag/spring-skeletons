package gr.nothingness.springskeletons.datatests.repositories;

import gr.nothingness.springskeletons.datatests.entities.Flight;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface FlightRepository extends CrudRepository<Flight, Long> {

  List<Flight> findByOrigin(String origin);

  List<Flight> findByOriginAndDestination(String origin, String destination);

  List<Flight> findByOriginIn(String ... origins);

  List<Flight> findByOriginIgnoreCase(String origin);

}

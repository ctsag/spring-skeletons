package gr.nothingness.springskeletons.datatests.repositories;

import gr.nothingness.springskeletons.datatests.entities.Flight;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FlightRepository extends PagingAndSortingRepository<Flight, Long>, DeleteByOriginRepository {

  List<Flight> findByOrigin(String origin);

  List<Flight> findByOriginAndDestination(String origin, String destination);

  List<Flight> findByOriginIn(String ... origins);

  List<Flight> findByOriginIgnoreCase(String origin);

  Page<Flight> findByOrigin(String origin, Pageable destination);

}

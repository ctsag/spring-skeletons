package gr.nothingness.springskeletons.datajpapostgres.repositories;

import gr.nothingness.springskeletons.datajpapostgres.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {

}

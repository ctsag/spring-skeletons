package gr.nothingness.springskeletons.datajpapostgres.repositories;

import gr.nothingness.springskeletons.datajpapostgres.models.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeakerRepository extends JpaRepository<Speaker, Long> {

}

package gr.nothingness.springskeletons.datatests.entities;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
public class Flight {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Getter private Long id;

  @Getter @Setter private String origin;
  @Getter @Setter private String destination;
  @Getter @Setter private LocalDateTime scheduledAt;

}

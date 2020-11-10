package gr.nothingness.springskeletons.datatests.entities;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Flight {

  @Getter private String id;

  @Getter @Setter private String origin;
  @Getter @Setter private String destination;
  @Getter @Setter private LocalDateTime scheduledAt;

}

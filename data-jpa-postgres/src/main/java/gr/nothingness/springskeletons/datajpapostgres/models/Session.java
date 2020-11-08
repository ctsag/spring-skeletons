package gr.nothingness.springskeletons.datajpapostgres.models;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity(name = "sessions")
public class Session {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "session_id")
  private Long id;

  @Column(name = "session_description")
  @Getter @Setter private String description;

  @Column(name = "session_length")
  @Getter @Setter private Integer length;

  @Column(name = "session_name")
  @Getter @Setter private String name;

  @ManyToMany
  @JoinTable(
      name = "session_speakers",
      joinColumns = @JoinColumn(name = "session_id"),
      inverseJoinColumns = @JoinColumn(name = "speaker_id")
  )
  @Getter @Setter private List<Speaker> speakers;

}

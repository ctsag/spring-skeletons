package gr.nothingness.springskeletons.datajpapostgres.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

@NoArgsConstructor
@Entity(name = "speakers")
public class Speaker {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "speaker_id")
  private Long id;

  @Column(name = "company")
  @Getter @Setter private String company;

  @Column(name = "first_name")
  @Getter @Setter private String firstName;

  @Column(name = "last_name")
  @Getter @Setter private String lastName;

  @Column(name = "speaker_bio")
  @Getter @Setter private String bio;

  @Column(name = "title")
  @Getter @Setter private String title;

  @Lob @Type(type = "org.hibernate.type.BinaryType")
  @Column(name = "speaker_photo")
  @Getter @Setter private byte[] photo;

  @JsonIgnore
  @ManyToMany(mappedBy = "speakers")
  @Getter @Setter private List<Session> sessions;

}

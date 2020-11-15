package gr.nothingness.springskeletons.datarestmicroservices.applications.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
public class Application {

  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  @Getter private Integer id;

  @Column
  @Getter @Setter private String name;

  @Column
  @Getter @Setter private String description;

  @Column
  @Getter @Setter private Integer ownerId;

  @Column @Transient
  @Getter @Setter private String ownerName;

  @Column @Transient
  @Getter @Setter private String ownerRole;

}

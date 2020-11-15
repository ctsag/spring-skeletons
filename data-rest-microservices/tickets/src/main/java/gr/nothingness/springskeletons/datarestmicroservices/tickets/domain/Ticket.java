package gr.nothingness.springskeletons.datarestmicroservices.tickets.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
public class Ticket {

  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  @Getter private Integer id;

  @Column
  @Getter @Setter private String title;

  @Column
  @Getter @Setter private String description;

  @Column
  @Getter @Setter private Integer appId;

  @Column
  @Getter @Setter private Integer userId;

}

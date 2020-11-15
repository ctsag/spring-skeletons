package gr.nothingness.springskeletons.datarestmicroservices.users.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.ToString;

@Entity
@ToString
public class User {

  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "person_id")
  @Getter private Integer id;

  @Column
  @Getter
  private String name;

  @Column
  @Getter
  private String role;

  public void setName(String name) {
    this.name = name;
  }

  public void setRole(String role) {
    this.role = role;
  }
}

package gr.nothingness.springskeletons.datajpabasicaccess;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Entity
public class Customer {

  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  @Getter private Long id;

  @Getter private String firstName;
  @Getter private String lastName;

  public Customer(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

}

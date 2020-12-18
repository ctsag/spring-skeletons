package gr.nothingness.springskeletons.restfuldatajpaservice.entities;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="authorities")
@NoArgsConstructor
public class AuthenticatedAuthority {

  @Id
  @Getter @Setter private UUID id;

  @Column
  @Getter @Setter private String authority;

  @ManyToOne
  @Getter @Setter private AuthenticatedUser user;

  public AuthenticatedAuthority(AuthenticatedUser user, String authority) {
    this.id = UUID.randomUUID();
    this.user = user;
    this.authority = authority;
  }

}

package gr.nothingness.springskeletons.restfuldatajpaservice.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="users")
@NoArgsConstructor
public class AuthenticatedUser {

  @Id
  @Getter @Setter private UUID id;

  @Column(name="username", unique = true)
  @Getter @Setter private String username;

  @Column
  @Getter @Setter private String password;

  @Column
  @Getter @Setter private Boolean enabled = true;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @Getter @Setter List<AuthenticatedAuthority> userAuthorities = new ArrayList<>();

  public AuthenticatedUser(UUID id, String username, String password, Boolean enabled) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.enabled = enabled;
  }

  public AuthenticatedUser(AuthenticatedUser user) {
    this.id = user.id;
    this.username = user.username;
    this.password = user.password;
    this.enabled = user.enabled;
    this.userAuthorities = user.userAuthorities;
  }

  public void addAuthority(String authority) {
    userAuthorities.add(new AuthenticatedAuthority(this, authority));
  }

}

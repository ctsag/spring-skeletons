package gr.nothingness.springskeletons.restfuldatajpaservice.services;

import gr.nothingness.springskeletons.restfuldatajpaservice.entities.AuthenticatedUser;
import gr.nothingness.springskeletons.restfuldatajpaservice.repositories.AuthenticatedUserRepository;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatedUserRepositoryUserDetailsService implements UserDetailsService {

  private final AuthenticatedUserRepository repository;

  public AuthenticatedUserRepositoryUserDetailsService(AuthenticatedUserRepository repository) {
    this.repository = repository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return repository.findByUsername(username)
        .map(BridgeAuthenticatedUser::new)
        .orElseThrow(() -> new UsernameNotFoundException(username));
  }

  private static class BridgeAuthenticatedUser extends AuthenticatedUser implements UserDetails {

    public BridgeAuthenticatedUser(AuthenticatedUser user) {
      super(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return super.getUserAuthorities().stream()
          .map(authenticatedAuthority -> new SimpleGrantedAuthority(authenticatedAuthority.getAuthority()))
          .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
      return true;
    }

    @Override
    public boolean isAccountNonLocked() {
      return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
      return true;
    }

    @Override
    public boolean isEnabled() {
      return super.getEnabled();
    }

  }

}

package gr.nothingness.springskeletons.datajpapostgres.controllers;

import gr.nothingness.springskeletons.datajpapostgres.models.Session;
import gr.nothingness.springskeletons.datajpapostgres.repositories.SessionRepository;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionController {

  @Autowired
  private SessionRepository repository;

  @GetMapping
  public List<Session> list() {
    return repository.findAll();
  }

  @GetMapping("{id}")
  public Session get(@PathVariable Long id) {
    return repository.getOne(id);
  }

  @PostMapping @ResponseStatus(HttpStatus.CREATED)
  public Session create(@RequestBody Session session) {
    return repository.saveAndFlush(session);
  }

  @DeleteMapping("{id}")
  public void delete(@PathVariable Long id) {
    repository.deleteById(id);
  }

  @PutMapping("{id}")
  public Session update(@PathVariable Long id, @RequestBody Session session) {
    Session existingSession = repository.getOne(id);
    BeanUtils.copyProperties(session, existingSession, "session_id");

    return repository.saveAndFlush(existingSession);
  }

}

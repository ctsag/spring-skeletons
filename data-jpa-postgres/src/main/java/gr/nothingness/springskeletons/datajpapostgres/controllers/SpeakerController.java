package gr.nothingness.springskeletons.datajpapostgres.controllers;

import gr.nothingness.springskeletons.datajpapostgres.models.Speaker;
import gr.nothingness.springskeletons.datajpapostgres.repositories.SpeakerRepository;
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
@RequestMapping("/api/v1/speakers")
public class SpeakerController {

  @Autowired
  private SpeakerRepository repository;

  @GetMapping
  public List<Speaker> list() {
    return repository.findAll();
  }

  @GetMapping("{id}")
  public Speaker get(@PathVariable Long id) {
    return repository.getOne(id);
  }

  @PostMapping @ResponseStatus(HttpStatus.CREATED)
  public Speaker create(@RequestBody Speaker speaker) {
    return repository.saveAndFlush(speaker);
  }

  @DeleteMapping("{id}")
  public void delete(@PathVariable Long id) {
    repository.deleteById(id);
  }

  @PutMapping("{id}")
  public Speaker update(@PathVariable Long id, @RequestBody Speaker speaker) {
    Speaker existingSpeaker = repository.getOne(id);
    BeanUtils.copyProperties(speaker, existingSpeaker, "speaker_id");

    return repository.saveAndFlush(existingSpeaker);
  }

}

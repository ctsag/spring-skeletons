package gr.nothingness.springskeletons.restfulhateoasservice;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

public class Greeting extends RepresentationModel<Greeting> {

  @Getter private final String content;

  @JsonCreator
  public Greeting(@JsonProperty("context") String content) {
    this.content = content;
  }

}

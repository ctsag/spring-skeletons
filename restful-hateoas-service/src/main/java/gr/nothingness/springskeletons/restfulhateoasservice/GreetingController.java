package gr.nothingness.springskeletons.restfulhateoasservice;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

  public static final String GREETING = "Hello, %s!";

  @GetMapping("/greeting")
  public HttpEntity<Greeting> greeting(
      @RequestParam(value = "name", defaultValue = "World") String name
  ) {
    Greeting greeting = new Greeting(String.format(GREETING, name));

    greeting.add(
        linkTo(methodOn(GreetingController.class).greeting(name)).withSelfRel()
    );

    return new ResponseEntity<>(greeting, HttpStatus.OK);
  }

}

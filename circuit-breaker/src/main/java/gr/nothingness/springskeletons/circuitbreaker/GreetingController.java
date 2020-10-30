package gr.nothingness.springskeletons.circuitbreaker;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@EnableCircuitBreaker
@RestController
public class GreetingController {

  private final GreetingService greetingService;

  @GetMapping("/greetings")
  public String toRead() {
    return greetingService.greetingList();
  }

}

package gr.nothingness.springskeletons.cucumberexternal.service;

import gr.nothingness.springskeletons.cucumberexternal.lib.FibonacciSequencer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FibonacciController {

  private final FibonacciSequencer fibonacci = new FibonacciSequencer();

  @GetMapping("/fibonacci")
  public String fibonacci() {
    return fibonacci.getSequence();
  }

}

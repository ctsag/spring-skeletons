package gr.nothingness.springskeletons.circuitbreaker;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.net.URI;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GreetingService {

  public static final String GREETING_SERVICE = "http://localhost:8080/greeting";

  private final RestTemplate restTemplate;

  public GreetingService() {
    this.restTemplate = new RestTemplateBuilder().build();
  }

  @HystrixCommand(fallbackMethod = "reliable")
  public String greetingList() {
    URI uri = URI.create(GREETING_SERVICE);

    return restTemplate.getForObject(uri, String.class);
  }

  public String reliable() {
    return "Hello from reliable()";
  }

}

package gr.nothingness.springskeletons.restconsumer;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Log4j2
@Component
public class QuoteServiceConsumer {

  private static final String QUOTE_SERVICE_URI = "https://gturnquist-quoters.cfapps.io/api/random";

  private final RestTemplate restTemplate;

  public QuoteServiceConsumer() {
    this.restTemplate = new RestTemplateBuilder().build();
    consume();
  }

  private void consume() {
    Quote quote = restTemplate.getForObject(QUOTE_SERVICE_URI, Quote.class);
    log.info(quote);
  }

}

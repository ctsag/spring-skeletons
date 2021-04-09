package gr.nothingness.springskeletons.routedlogging;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Scope("prototype")
@Log4j2
public class FirstRandomNumberFetcher extends Thread {

  private static final String RANDOM_SERVICE_URI = "http://www.randomnumberapi.com/api/v1.0/random";
  private final RestTemplate restTemplate = new RestTemplateBuilder().build();

  @Override
  public void run() {
    int threadId = Integer.parseInt(getName().substring(getName().length() - 1));
    if (threadId % 2 == 0) {
      if (threadId % 3 == 0) {
        setLogContext("Group1");
      } else {
        setLogContext("Group2");
      }
    } else {
      setLogContext("Group3");
    }

    while (true) {
      try {
        log.info(restTemplate.getForObject(RANDOM_SERVICE_URI, String.class));
        Thread.sleep(2000);
      } catch (InterruptedException exception) {
        log.error("Thread interrupted, existing");
        break;
      } catch (Exception exception) {
        log.error(exception.getMessage());
      }
    }
  }

  private void setLogContext(String value) {
    ThreadContext.put("route", value);
  }
}

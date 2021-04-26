package gr.nothingness.springskeletons.routedlogging;

import java.util.Random;
import org.apache.logging.log4j.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FirstRandomNumberFetcher extends Thread {

  private static final Logger log = LoggerFactory.getLogger(FirstRandomNumberFetcher.class);

  public FirstRandomNumberFetcher(String name) {
    super(name);
  }

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
        Random rand = new Random();
        log.info(String.valueOf(rand.nextInt(100)));
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

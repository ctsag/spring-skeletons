package gr.nothingness.springskeletons.routedlogging;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class RoutedLoggingApplication {

  public static void main(String[] args) {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

    FirstRandomNumberFetcher firstFetcher1 = (FirstRandomNumberFetcher) ctx.getBean("firstRandomNumberFetcher");
    firstFetcher1.setName("1st Random Thread 1");

    FirstRandomNumberFetcher firstFetcher2 = (FirstRandomNumberFetcher) ctx.getBean("firstRandomNumberFetcher");
    firstFetcher2.setName("1st Random Thread 2");

    SecondRandomNumberFetcher secondFetcher1 = (SecondRandomNumberFetcher) ctx.getBean("secondRandomNumberFetcher");
    secondFetcher1.setName("2nd Random Thread 1");

    SecondRandomNumberFetcher secondFetcher2 = (SecondRandomNumberFetcher) ctx.getBean("secondRandomNumberFetcher");
    secondFetcher2.setName("2nd Random Thread 2");

    firstFetcher1.start();
    firstFetcher2.start();
    secondFetcher1.start();
    secondFetcher2.start();
  }

}

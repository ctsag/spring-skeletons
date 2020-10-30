package gr.nothingness.springskeletons.autoconfigurecontextexplorer;

import java.util.Arrays;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class BeanExplorer {

  private final String[] beanNames;

  public BeanExplorer(ApplicationContext ctx) {
    this.beanNames = ctx.getBeanDefinitionNames();

    logBeans();
  }

  private void logBeans() {
    Arrays.stream(beanNames).sorted().forEach(System.out::println);
  }

}

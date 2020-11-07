package gr.nothingness.springskeletons.weblayertests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FullContextTest {

  @Autowired
  private HomeController controller;

  @Test
  public void contextLoads() {
    assertThat(controller).isNotNull();
  }

}

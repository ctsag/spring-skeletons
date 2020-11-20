package gr.nothingness.springskeletons.datarestmicroservices.applications;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import gr.nothingness.springskeletons.datarestmicroservices.applications.domain.Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class ApplicationsApplicationTests {

  @Autowired
  private TestRestTemplate restTemplate;

  public static final String URL = "http://localhost:50001/apps";

  @Test
  public void testGetApplication() throws Exception {
    ResponseEntity<Application> responseEntity = restTemplate.getForEntity(
        URL + "/1",
        Application.class
    );
    Application application = responseEntity.getBody();

    assertAll(
        () -> assertEquals(HttpStatus.OK, responseEntity.getStatusCode()),
        () -> assertEquals("TrackZilla", application.getName()),
        () -> assertEquals("Jane Doe", application.getOwnerName())
    );
  }

  @Test
  public void testCreateApplication() throws Exception {
    Application application = new Application();
    application.setName("New Application");
    application.setDescription("New tracking app");
    application.setOwnerId(3);

    ResponseEntity<Application> responseEntity = restTemplate.postForEntity(
        URL,
        application,
        Application.class
    );

    assertAll(
        () -> assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode()),
        () -> assertNotNull(responseEntity.getBody())
    );
  }

  @Test
  public void testUpdateApplication() throws Exception {
    Application application = new Application();
    application.setId(2);
    application.setName("New TrackZilla Name");
    application.setDescription("New app description");
    application.setOwnerId(2);

    HttpEntity<Application> requestEntity = new HttpEntity<>(application);

    ResponseEntity<Application> responseEntity = restTemplate.exchange(
        URL + "/2",
        HttpMethod.PUT,
        requestEntity,
        Application.class
    );

    Application updatedApplication = responseEntity.getBody();

    assertAll(
        () -> assertEquals(HttpStatus.OK, responseEntity.getStatusCode()),
        () -> assertEquals(application.getName(), updatedApplication.getName())
    );
  }

  @Test
  public void testDeleteApplication() throws Exception {
    ResponseEntity<Void> responseEntity = restTemplate.exchange(
        URL + "/2",
        HttpMethod.DELETE,
        null,
        Void.class
    );

    assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
  }


}

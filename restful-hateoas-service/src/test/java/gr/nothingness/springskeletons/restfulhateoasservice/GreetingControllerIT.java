package gr.nothingness.springskeletons.restfulhateoasservice;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

import gr.nothingness.springskeletons.restfulhateoasservice.testfacilities.GreetingResponse;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class GreetingControllerIT {

  private static RequestSpecification requestSpec;
  private static ResponseSpecification responseSpec;

  @BeforeAll
  public static void setUp() {
    requestSpec = new RequestSpecBuilder()
        .setBaseUri("http://localhost:8080/greeting")
        .build();

    responseSpec = new ResponseSpecBuilder()
        .expectStatusCode(200)
        .expectContentType(ContentType.JSON)
        .build();

  }

  @Test
  public void returnsExpectedDefaultGreeting() {
    given().
        spec(requestSpec).
    when().
        get().
    then().
        assertThat().
        body("context", equalTo("Hello, World!"));
  }

  @ParameterizedTest
  @ValueSource(strings = { "Amy", "Nick", "Anthony" })
  public void returnsExpectedCustomGreeting(String name) {
    given().
        spec(requestSpec).
    when().
        get("?name=" + name).
    then().
        spec(responseSpec).
    and().
        assertThat().
        body("context", equalTo("Hello, " + name + "!"));
  }

  @Test
  public void returnsExpectedStatusCode() {
    given().
        spec(requestSpec).
    when().
        get().
    then().
        assertThat().
        statusCode(200);
  }

  @Test
  public void returnsExpectedContentType() {
    given().
        spec(requestSpec).
        log().all().
    when().
        get().
    then().
        log().body().
    and().
        assertThat().
        contentType(ContentType.JSON);
  }

  @Test
  public void extractedValueMatchesExpectations() {
    String greeting =
        given().
            spec(requestSpec).
        when().
            get().
        then().
            extract().
            path("context");

    assertEquals(greeting, "Hello, World!");
  }

  @Test
  public void deserializesResponse() {
    GreetingResponse response =
        given().
            spec(requestSpec).
        when().
            get().
            as(GreetingResponse.class);

    assertEquals("Hello, World!", response.getContext());
  }

}

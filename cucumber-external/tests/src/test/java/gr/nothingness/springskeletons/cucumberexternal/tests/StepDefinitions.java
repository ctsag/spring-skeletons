package gr.nothingness.springskeletons.cucumberexternal.tests;

import static org.junit.Assert.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import gr.nothingness.springskeletons.cucumberexternal.lib.FibonacciSequencer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class StepDefinitions {

  private static final String SERVICE_URI = "http://localhost:8080/fibonacci";
  private String retrievedSequence;

  private HttpURLConnection connection;

  @Given("a fibonacci service has been provided")
  public void aFibonacciServiceHasBeenProvided() throws Exception {
    URL url = new URL(SERVICE_URI);
    connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("GET");
  }

  @When("I hit the fibonacci service")
  public void iHitTheFibonacciService() throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    StringBuffer response = new StringBuffer();
    String inputLine;

    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }
    in.close();

    retrievedSequence = response.toString();
  }


  @Then("I get back the expected sequence")
  public void iGetBack() {
    FibonacciSequencer sequencer = new FibonacciSequencer();
    assertEquals(sequencer.getSequence(), retrievedSequence);
  }
}

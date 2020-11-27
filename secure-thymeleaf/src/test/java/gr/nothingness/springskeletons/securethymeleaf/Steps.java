package gr.nothingness.springskeletons.securethymeleaf;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Steps extends SpringIntegrationTest {

  private WebDriver webDriver;

  @Before
  public void setUp() {
    System.setProperty(
        "webdriver.chrome.driver",
        "C:\\Users\\Christos\\Downloads\\chromedriver.exe\\"
    );
    webDriver = new ChromeDriver();
  }

  @After
  public void tearDown() {
    webDriver.quit();
  }

  @Given("I am in the login page")
  @Given("I am in the login page of the web application")
  public void iAmInTheLoginPageOfTheWebApplication() {
    webDriver.get("localhost:8080/hello");
  }

  @When("I enter valid credentials")
  public void iEnterValidCredentials(DataTable dataTable) {
    List<String> loginForm = dataTable.asList();
    String username = loginForm.get(0);
    String password = loginForm.get(1);

    webDriver.findElement(By.name("username")).sendKeys(username);
    webDriver.findElement(By.name("password")).sendKeys(password);
    webDriver.findElement(By.name("username")).submit();
  }

  @When("I enter a valid {string} and {string}")
  public void iEnterValidUsernameAndPassword(String username, String password) {
    webDriver.findElement(By.name("username")).sendKeys(username);
    webDriver.findElement(By.name("password")).sendKeys(password);
    webDriver.findElement(By.name("username")).submit();
  }

  @Then("I should be taken to the hello page")
  public void iShouldBeTakenToTheHelloPage() throws Exception {
    Thread.sleep(100);

    webDriver.findElement(By.name("title")).isDisplayed();
    webDriver.findElement(By.name("logout")).click();
  }

  @When("I enter a valid <username> and <password>")
  public void iEnterAValidUsernameAndPassword() {
  }

}

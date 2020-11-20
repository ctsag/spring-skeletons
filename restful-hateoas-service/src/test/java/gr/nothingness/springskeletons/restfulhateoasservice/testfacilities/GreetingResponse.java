package gr.nothingness.springskeletons.restfulhateoasservice.testfacilities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@JsonIgnoreProperties(value = "_links")
public class GreetingResponse {

  @Getter private String context;

}

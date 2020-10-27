package gr.nothingness.springskeletons.restconsumer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Value {

  @Getter @Setter private Long id;
  @Getter @Setter private String quote;

}

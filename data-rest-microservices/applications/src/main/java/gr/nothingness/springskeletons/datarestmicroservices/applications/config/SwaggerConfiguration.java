package gr.nothingness.springskeletons.datarestmicroservices.applications.config;

import static java.util.Collections.singletonList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .build()
        .useDefaultResponseMessages(false)
        .globalResponses(
            HttpMethod.GET,
            singletonList(new ResponseBuilder()
                .code("500")
                .description("The server is down. Please make sure that the Users microservice is running")
                .representation(MediaType.TEXT_XML)
                .apply(r ->
                    r.model(m ->
                        m.referenceModel(ref ->
                            ref.key(k ->
                                k.qualifiedModelName(q ->
                                    q.namespace("some:namespace").name("ERROR")
                                )
                            )
                        )
                    )
                )
                .build()
            )
        );
  }

}

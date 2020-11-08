package gr.nothingness.springskeletons.datajpapostgres.configuration;

import javax.sql.DataSource;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Configuration
public class PersistenceConfiguration {

  @Bean
  public DataSource dataSource() {
    DataSourceBuilder builder = DataSourceBuilder.create();

    builder.url("jdbc:postgresql://localhost:5432/conference_app");
    builder.username("postgres");
    builder.password("Welcome");
    log.info("Custom data source built");

    return builder.build();
  }

}

# Contents
A list of skeleton apps for Spring Boot :

* data-jpa-basic-access
    - Data JPA entity
    - Data JPA CRUD repository interface
    - Data JPA repository interface overrides
    - H2 database

* rest-consumer
    - REST Template
    - JSON marshalling

* restful-data-jpa-service
    - Data REST basic customization
    - Data JPA entity
    - Data JPA PagingAndSorting repository interface
    - Data JPA repository interface overrides
    - H2 database

* restful-service
    - REST controller
    - JSON marshalling

* auto-configure-context-explorer
    - Actuator
    - REST controller
    - REST controller integration test
    - REST controller unit test
    - Application context exploration

* restful-hateoas-service
    - HATEOAS representation model class
    - REST controller
    - Jackson

* circuit-breaker
    - Circuit Breaker pattern
    - Netflix Hystrix
    - REST controller
    - REST template

# Rules
The following rules will apply for all skeleton apps :

* The project properties present in the pom.xml file will follow this pattern :

```xml
<groupId>gr.nothingness.spring-skeletons</groupId>
<artifactId>restful-service</artifactId>
<version>0.0.1-SNAPSHOT</version>
<name>RESTful Service</name>
<description>Spring skeleton for a RESTful service</description>
```

* Lombok will always be a dependency and will be used for the following :
    - Getters and setters that do not require custom code
    - Any constructor that does not require custom code
    - Setting up loggers
    - toString()

* A README.md file will exist for all skeleton apps describing the areas involved and references on how the application was built

* Prefer @Component instead of @Bean

* No comments unless absolutely necessary

* No javadoc unless we're talking about a public library or API

* All IntelliJ IDEA warnings must be resolved or justifiably suppressed

* Prefer simple lamdas instead of loops, and prefer method references instead of lamda expressions

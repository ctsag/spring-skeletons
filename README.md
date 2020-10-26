# Contents
A list of skeleton apps for Spring Boot

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

* A README.md file will exist for all skeleton apps describing the areas involved and references on how the application was built

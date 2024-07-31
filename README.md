# blossom-tech

### How to build and run the application locally

To run locally the application use the following mvn command:

```
mvn clean package --projects :container-module --also-make
```

This command will clean all the modules and the will build and package the start up module with its dependencies.

After the previous step can be simplified by executing the .jar file located in the target using the following command:

```
java -jar **.jar --spring.profiles.active=prod
```

the last flag allows you to run the production profile of properties.

To improve this process and decouple the application from our OS, Docker is the best option to achieve this.
Using Docker, after running the `mvn clean package` command you can build and the docker image with the
command `docker build -t blossom-tech .` and `docker run -p 8080:8080 blossom-tech
`

### How to deploy the application in AWS

An RDS instance is needed and a MySQL engine as well. It is also necessary to create an ECR, ECS cluster and ECS task
using Fargate.

### Missing parts

Since the requirements of this tech proof were very overwhelming, completing every one of these is out of the question,
here is a list of some missing requirements:

* OpenApi Swagger implementation
* Validations implementation: despite this requirement was not achieved, the exception handlers were added to handle the
  exceptions.
* Transactional in Creation (Commands) use cases.
* Unit tests and integration tests: Bear in mind that the unit coverage in product service is up to 100% and the
  integration (JPA Testing) testing environment was set up as well. Despite the fact the other services do not have
  tests, the quality of the product service tests are enough to prove what can be done in a future.
* I would like to mention that the used project structure and architecture was designed to be scalable with
  microservices, however, by the time, it was only possible to communicate the services with each other through POM
  modules and dependency injection.

### Utilized design patterns

To this exercise I used several patterns, among them are included: CQRS, Mediator, Repository, DTO, etc. And I also want
to mention the use of SOLID principles, and Clean architecture.

### Theoretical Questions

* Explain how Spring handles dependency injection and why it is beneficial for the
  development of enterprise applications.
    * There are two was to handle the DI in spring boot: through annotations (`@Service, @Component, @Repository`) and
      through `@Configuration` classes where `@Bean` can be defined and set up the specific implementation for each
      abstraction. It is beneficial for enterprise applications because it allows to outsource object instantiation and be handled by the framework, achieving successfully code decoupling.
* Discuss the differences between JPA and Hibernate, and why you would choose one over
  the other.
  * Hibernate is an ORM that allows to handle the DB operations and it is an implementation for the Jakarta Persistence Api (JPA) and they both work together very well.


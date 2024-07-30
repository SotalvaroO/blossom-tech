FROM amazoncorretto:17
COPY ./container-module/target/*.jar ./blossom-tech.jar
EXPOSE 8080
CMD ["java","-jar","/blossom-tech.jar","--spring.profiles.active=prod"]

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)
# Page Numbers Reducer Project

Spring-based Project for transforming a range of page numbers to a reduced printer friendly format.
_______

### Description:

Page Numbers Reducer Project's only functionality is as follows:

1) Client shall make a GET-request with a line of page numbers. Example:

```http request
GET /reducedPageNumbers?rawPageNumbers=<1,3,32,5,11,7,6,19,2,21,4,8,22,23>
```

2) App will return JSON response object with both request original line and reduced page numbers line. Example:

```json
{
  "original": "original page numbers list",
  "reduced": "reduced page numbers list"
}
```

3) All "0" and spaces between commas will be ignored. Any negative and non-Integer numbers as well as non-digit symbols
   will result a Bad Request response.
4) Look springdoc documentation for more details.

_______

### Configuration:

- JDK 11;
- Spring 5;
- REST API & Jackson;
- Spring Boot 2.7.8;
- Documentation: OpenAPI 3: springdoc 1.6.14, incl. swagger UI;
- JUnit 5 tests.

_______

### App use, running and deployment:

In order to build the app, you need to download and unpack the recent version of
Maven (https://maven.apache.org/download.cgi). Then, you will need to install a
Java JDK 11 (or higher). Make sure that you can run java and Maven from the command line.

Run from the command line:
```
mvn clean install
```
to compile the code, run tests, put results in a jar file in the target directory and place it to a local repository.

You can also use Maven commands via command line:
```
mvn compile
```
to compile the code and check for errors;
```
mvn test
```
to compile the code and run tests;
```
mvn package
```
to compile and pack into jar file with no tests run. 

You can run the app locally at [Localhost address](http://localhost:8080/reducedPageNumbers) via:

1) PagenumberreducerApplication Spring Boot starter in IDE (like NetBeans, Intellij IDEA, or
   Eclipse), or
2) command line:
   ```
   java -jar <path to a jar file>
   ```
3) Docker container with Dockerfile and docker-compose.yml in the app root.
   Or you can deploy it to your server (you may need to amend docker-compose.yml).

_______

### OpenAPI Documentation:

Use the address http://localhost:8080/v3/api-docs to see the full springdoc documentation.

Use the address http://localhost:8080/swagger-ui/index.html#/ to use the swagger ui.


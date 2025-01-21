# API Testing Example

This is a small project that using Java, Maven, RestAssured, TestNG and SnakeYAML to automate API testing of few endpoints with GET requests.

## Table of Contents
- [Prerequisites](#prerequisites)
- [Setting Up the Project](#setting-up-the-project)
- [Adding Dependencies](#adding-dependencies)
- [Project Structure](#writing-api-tests)
- [Adding Bearer Token](#writing-api-tests)
- [Running Tests](#running-tests)
- [Test Report](#running-tests)


## Prerequisites

Before you get started with this project, make sure you have the following prerequisites in place:

- **Java**: Ensure that you have Java installed on your machine. You can download it from [Oracle's website](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).

- **Maven**: Install Maven to manage your project dependencies and build processes. You can download Maven [here](https://maven.apache.org/download.cgi).

- **IDE**: You can use any Java IDE of your choice, such as IntelliJ IDEA or Eclipse.

## Setting Up the Project

To set up the project, you can use Maven to create a basic project structure:

```bash
mvn archetype:generate -DgroupId=com.yourcompany -DartifactId=api-test-framework -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```
## Open the project in your preferred IDE for further development.

## Adding Dependencies
In your pom.xml file, you should add the necessary dependencies for TestNG, RestAssured and SnakeYAML. Run mvn clean install to download these dependencies.

## Project Structure

```
📦 
├─ .gitignore
├─ .idea
│  ├─ .gitignore
│  ├─ encodings.xml
│  ├─ misc.xml
│  └─ vcs.xml
├─ README.md
├─ pom.xml
├─ src
│  ├─ main
│  │  ├─ java
│  │  │  ├─ api
│  │  │  │  ├─ Endpoints.java
│  │  │  │  ├─ Routes.java
│  │  │  │  └─ TestData.java
│  │  │  └─ config
│  │  │     └─ ConfigReader.java
│  │  └─ resources
│  │     └─ config.yaml
│  └─ test
│     └─ java
│        └─ api
│           └─ ValidateEndpointsTest.java
└─ testng.xml
```

## Adding Bearer Token

Before running the tests, you will need to add the correct Bearer Token in "config.yaml" file. You can find the Bearer Token in the "QA_Automation_Task.pdf" file.
Example:
```
bearerToken: 000123456789000
```

## Running Tests

You can run your API tests using Maven:
```bash
mvn clean test
```

## Test Report

You can find Test Report in this file path:
```
ProjectFile/target/surefire-reports/Surefire suite/Surefire test.html
```

Target package will be created after the project is set up, and test report will be created after tests are run using maven command.

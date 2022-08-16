## QA Coding challenge

> The task is:
> - To create a test automation framework skeleton.
> - To perform the validations for the comments for the post made by a
specific user.

## How to use

### Locally

You must have Java 11 and Maven installed. Clone the project and type the following command:
```sh
mvn clean test
```
In case you require a report you could run:
```sh
mvn allure:serve
```

### CircleCI

CircleCI is integrated for Continuous Integration. You can find pipeline execution history here: https://app.circleci.com/pipelines/github/evgeny-pretko/mobiquity

Allure automation result report is stored in `ARTIFACTS` tab: _allure/index.html_

## Technologies

- Java
- Maven
- TestNG
- Rest Assured
- Allure
- CircleCI
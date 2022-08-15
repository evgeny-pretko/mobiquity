import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import models.UserResponse;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.EmailValidator;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

public class EmailTest {

    @Test
    @Description("Search for the user with username 'Delphine'. Search for the posts written by the user. " +
            "For each post, fetch the comments and validate if the emails in the comment section are in the proper format.")
    public void evgTest() {
        SoftAssert soft = new SoftAssert();

        List<UserResponse> users = given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .basePath("/users")
                .contentType(ContentType.JSON)
                .when().get()
                .then().statusCode(SC_OK)
                .extract().jsonPath().getList("", UserResponse.class);

        int userId = users.stream().filter(x -> "Delphine".equals(x.getUsername())).findAny().get().getId();

        List<Integer> postIds = given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .basePath("/posts")
                .contentType(ContentType.JSON)
                .queryParam("userId", userId)
                .when().get()
                .then().statusCode(SC_OK)
                .extract().jsonPath().getList("id");

        for (Integer postId: postIds) {
            List<String> emails = given()
                    .baseUri("https://jsonplaceholder.typicode.com")
                    .contentType(ContentType.JSON)
                    .pathParam("post", postId)
                    .get("/posts/{post}/comments")
                    .then().statusCode(SC_OK)
                    .extract().jsonPath().getList("email");

            for (String email : emails) {
                soft.assertTrue(EmailValidator.isValidEmail(email), "Invalid format for email: " + email);
            }
        }

        soft.assertAll();
    }

}

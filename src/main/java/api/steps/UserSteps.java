package api.steps;

import api.objects.UserPojo;
import api.specs.BaseSpecs;


import static io.restassured.RestAssured.given;

public class UserSteps {

    public static int getUserIdByUsername(String username) {
        UserPojo user = given()
                .spec(BaseSpecs.requestSpec())
                .queryParam("username", username)
                .get("/users")
                .jsonPath().getList("", UserPojo.class).get(0);

        return user.getId();
    }

}
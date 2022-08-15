package api;

import api.objects.UserPojo;
import api.specs.BaseSpecs;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserApi {

    public static int getUserIdByUsername(String username) {
        UserPojo user = given()
                .spec(BaseSpecs.requestSpec())
                .queryParam("username", username)
                .get("/users")
                .jsonPath().getList("", UserPojo.class).get(0);

        return user.getId();
    }

}
package api;

import api.objects.PostPojo;
import api.specs.BaseSpecs;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostApi {

    public static List<PostPojo> getPostsByUserId(int userId) {
        return given().spec(BaseSpecs.requestSpec())
                .queryParam("userId", userId)
                .get("/posts")
                .jsonPath().getList("", PostPojo.class);
    }
}

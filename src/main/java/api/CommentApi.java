package api;

import api.objects.CommentPojo;
import api.specs.BaseSpecs;

import java.util.List;

import static io.restassured.RestAssured.given;

public class CommentApi {

    public static List<CommentPojo> getCommentsByPostId(int postId) {
        return given().spec(BaseSpecs.requestSpec())
                .pathParam("post", postId)
                .get("/posts/{post}/comments")
                .jsonPath().getList("", CommentPojo.class);
    }

}

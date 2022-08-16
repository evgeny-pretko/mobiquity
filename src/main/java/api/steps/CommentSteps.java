package api.steps;

import api.objects.CommentPojo;
import api.specs.BaseSpecs;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class CommentSteps {

    public static Response getAllCommentsResponse() {
        return given().spec(BaseSpecs.requestSpec())
                .get("/comments")
                .then().extract().response();
    }

    public static List<CommentPojo> getCommentsByPostId(int postId) {
        return given().spec(BaseSpecs.requestSpec())
                .pathParam("post", postId)
                .get("/posts/{post}/comments")
                .jsonPath().getList("", CommentPojo.class);
    }

    public static CommentPojo getCommentById(int id) {
        return given().spec(BaseSpecs.requestSpec())
                .pathParam("id", id)
                .get("/comments/{id}")
                .as(CommentPojo.class);
    }

    public static CommentPojo createComment(CommentPojo comment) {
        return given().spec(BaseSpecs.requestSpec())
                .body(comment)
                .post("/comments")
                .as(CommentPojo.class);
    }

    public static void deleteCommentById(int id) {
        given().spec(BaseSpecs.requestSpec())
                .pathParam("id", id)
                .delete("/comments/{id}");
    }

}

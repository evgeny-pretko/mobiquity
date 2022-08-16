package comment;

import api.steps.CommentSteps;
import api.steps.PostSteps;
import api.steps.UserSteps;
import api.objects.CommentPojo;
import api.objects.PostPojo;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.CommentGenerator;
import utils.EmailValidator;
import utils.FailedTestRepeater;

import java.util.Arrays;
import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

@Feature("Comment")
public class CommentTest {

    @BeforeSuite
    public void setUp(ITestContext context) {
        Arrays.stream(context.getAllTestMethods()).forEach(x -> x.setRetryAnalyzerClass(FailedTestRepeater.class));
    }

    @Test(groups = "contract")
    public void testContract() {
        Response response = CommentSteps.getAllCommentsResponse();

        response.then().body(matchesJsonSchemaInClasspath("schemas/comment_schema.json"));
    }

    @Test(groups = "workflow", dependsOnGroups = "contract")
    @Parameters("username")
    @Description("Search for the user with username 'Delphine'. Search for the posts written by the user. " +
            "For each post, fetch the comments and validate if the emails in the comment section are in the proper format.")
    public void testEmailFormatForCommentsToAllPostsOfSpecifiedUser(String username) {
        SoftAssert softAssert = new SoftAssert();

        int userId = UserSteps.getUserIdByUsername(username);
        List<PostPojo> posts = PostSteps.getPostsByUserId(userId);

        for (PostPojo post: posts) {
            List<CommentPojo> comments = CommentSteps.getCommentsByPostId(post.getId());

            for (CommentPojo comment : comments) {
                softAssert.assertTrue(EmailValidator.isValidEmail(comment.getEmail()), "Invalid format for email: " + comment.getEmail());
            }
        }

        softAssert.assertAll();
    }

    @Test(groups = "functional", dependsOnGroups = "contract")
    public void testCreateCommentWithAllFields() {
        CommentPojo request = CommentGenerator.getSimpleComment();
        CommentPojo response = CommentSteps.createComment(request);

        assertNotNull(response.getEmail());
        assertNotNull(response.getName());
        assertNotNull(response.getBody());
        assertEquals(501, response.getId());
    }

    @Test(groups = "functional", dependsOnGroups = "contract")
    public void testCreateCommentWithoutEmail() {
        CommentPojo request = CommentGenerator.getCommentWithoutEmail();
        CommentPojo response = CommentSteps.createComment(request);

        assertNotNull(response);
        assertNull(response.getEmail());
        assertEquals(501, response.getId());
    }

    @Test(groups = "functional", dependsOnGroups = "contract")
    public void testDeleteCommentById() {
        Response response = CommentSteps.deleteCommentById(1);

        assertEquals(200, response.getStatusCode());
    }

}
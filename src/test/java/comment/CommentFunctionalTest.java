package comment;

import api.steps.CommentSteps;
import api.steps.PostSteps;
import api.steps.UserSteps;
import api.objects.CommentPojo;
import api.objects.PostPojo;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.CommentGenerator;
import utils.EmailValidator;
import utils.FailedTestRepeater;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

@Feature("Comment")
public class CommentFunctionalTest {

    @BeforeSuite
    public void setUp(ITestContext context) {
        Arrays.stream(context.getAllTestMethods()).forEach(x -> x.setRetryAnalyzerClass(FailedTestRepeater.class));
    }

    @Test(groups = "workflow")
    @Description("Search for the user with username 'Delphine'. Search for the posts written by the user. " +
            "For each post, fetch the comments and validate if the emails in the comment section are in the proper format.")
    public void testEmailFormatForCommentsToAllPostsOfSpecifiedUser() {
        SoftAssert softAssert = new SoftAssert();

        int userId = UserSteps.getUserIdByUsername("Delphine");
        List<PostPojo> posts = PostSteps.getPostsByUserId(userId);

        for (PostPojo post: posts) {
            List<CommentPojo> comments = CommentSteps.getCommentsByPostId(post.getId());

            for (CommentPojo comment : comments) {
                softAssert.assertTrue(EmailValidator.isValidEmail(comment.getEmail()), "Invalid format for email: " + comment.getEmail());
            }
        }

        softAssert.assertAll();
    }

    @Test(groups = "functional")
    public void testCreateCommentWithoutEmail() {
        CommentPojo request = CommentGenerator.getSimpleComment();
        CommentPojo response = CommentSteps.createComment(request);

        assertNotNull(response);
        assertNull(response.getEmail());
        assertEquals(501, response.getId());
    }

    @Test(groups = "functional")
    public void testDeleteCommentById() {
        CommentSteps.deleteCommentById(1);
        CommentPojo response = CommentSteps.getCommentById(1);

        assertNull(response.getName());
    }


}

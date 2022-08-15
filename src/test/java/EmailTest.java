import api.CommentApi;
import api.PostApi;
import api.UserApi;
import api.objects.CommentPojo;
import api.objects.PostPojo;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.EmailValidator;

import java.util.List;

public class EmailTest {

    @Test
    @Description("Search for the user with username 'Delphine'. Search for the posts written by the user. " +
            "For each post, fetch the comments and validate if the emails in the comment section are in the proper format.")
    public void testEmailFormatForCommentsToAllPostsOfSpecifiedUser() {
        SoftAssert softAssert = new SoftAssert();

        int userId = UserApi.getUserIdByUsername("Delphine");
        List<PostPojo> posts = PostApi.getPostsByUserId(userId);

        for (PostPojo post: posts) {
            List<CommentPojo> comments = CommentApi.getCommentsByPostId(post.getId());

            for (CommentPojo comment : comments) {
                softAssert.assertTrue(EmailValidator.isValidEmail(comment.getEmail()), "Invalid format for email: " + comment.getEmail());
            }
        }

        softAssert.assertAll();
    }

}

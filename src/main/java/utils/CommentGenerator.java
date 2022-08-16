package utils;

import api.objects.CommentPojo;
import org.apache.commons.lang3.RandomStringUtils;

public class CommentGenerator {

    public static CommentPojo getSimpleComment() {
        return CommentPojo.builder()
                .postId(1)
                .email(RandomStringUtils.randomAlphanumeric(10) + "@test.com")
                .name("Comment Name " + RandomStringUtils.randomAlphanumeric(10))
                .body("Comment Body " + RandomStringUtils.randomAlphanumeric(40))
                .build();
    }

    public static CommentPojo getCommentWithoutEmail() {
        return CommentPojo.builder()
                .postId(1)
                .name("Comment Name " + RandomStringUtils.randomAlphanumeric(10))
                .body("Comment Body " + RandomStringUtils.randomAlphanumeric(40))
                .build();
    }
}

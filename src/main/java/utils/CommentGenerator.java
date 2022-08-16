package utils;

import api.objects.CommentPojo;
import org.apache.commons.lang3.RandomStringUtils;

public class CommentGenerator {

    public static CommentPojo getSimpleComment() {
        return CommentPojo.builder()
                .postId(1)
                .name("Comment Name " + RandomStringUtils.randomAlphanumeric(10))
                .body("Comment Body " + RandomStringUtils.randomAlphanumeric(40))
                .build();
    }
}

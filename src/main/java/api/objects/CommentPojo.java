package api.objects;

import lombok.Data;

@Data
public class CommentPojo {
    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;

}

package api.objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class CommentPojo {
    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;

}

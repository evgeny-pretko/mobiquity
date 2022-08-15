package api.objects;

import lombok.Data;

@Data
public class PostPojo {
    private int userId;
    private int id;
    private String title;
    private String body;

}

package socialMedia.models;

import java.time.LocalDateTime;
import java.util.List;

public class Post {
    private String postId;
    private String caption;
    private String imageURL;
    private List<String>comments;
    private List<String>likes;
    private List<String> tags;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

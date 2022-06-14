package com.blogafac.kocirfan.response;

import com.blogafac.kocirfan.entity.Post;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PostResponse {

    Long id;
    Long userId;
    String userName;
    String title;
    String text;
    List<CommentResponse> postComment;
    Date date;


    public PostResponse(Post entity) {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.userName = entity.getUser().getUsername();
        this.title = entity.getTitle();
        this.text = entity.getText();
        this.postComment = getPostComment();
        this.date = entity.getCreateDate();

    }
}
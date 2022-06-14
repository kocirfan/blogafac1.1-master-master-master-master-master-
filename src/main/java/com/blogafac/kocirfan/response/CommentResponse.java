package com.blogafac.kocirfan.response;

import com.blogafac.kocirfan.entity.Comment;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentResponse {

    Long id;
    Long userId;
    String text;
    Long postId;
    int durum;
    String userName;

    public CommentResponse(Comment entity) {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.text = entity.getText();
        this.postId = entity.getPost().getId();
        this.durum = entity.getDurum();
        this.userName = entity.getUser().getUsername();
    }
}

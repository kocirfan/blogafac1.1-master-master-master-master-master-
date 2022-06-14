package com.blogafac.kocirfan.entity.dto;


import com.blogafac.kocirfan.entity.Post;
import com.blogafac.kocirfan.entity.User;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import javax.annotation.PostConstruct;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2
public class CommentDTO {


    Long id;
    Long userId;
    Long postId;
    String text;
    int durum;
    String username;
    User user;
    Post post;


}

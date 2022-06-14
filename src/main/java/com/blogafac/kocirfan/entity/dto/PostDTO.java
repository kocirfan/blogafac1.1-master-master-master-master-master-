package com.blogafac.kocirfan.entity.dto;

import com.blogafac.kocirfan.entity.Post;
import com.blogafac.kocirfan.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2
public class PostDTO {

    Long id;
    String text;
    String title;
    Long userId;
    User user;
    String username;


}

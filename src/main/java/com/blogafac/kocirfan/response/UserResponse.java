package com.blogafac.kocirfan.response;

import com.blogafac.kocirfan.entity.Post;
import com.blogafac.kocirfan.entity.User;
import lombok.Data;

@Data
public class UserResponse {
    Long id;
    String userName;
    String lastName;
    String password;


    public UserResponse(User user) {
        this.id = user.getId();
        this.userName = user.getUsername();
        this.lastName = user.getLastname();
        this.password = user.getPassword();

    }
}

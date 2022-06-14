package com.blogafac.kocirfan.request;

import lombok.Data;

@Data
public class UserCreateRequest {

    Long id;
    String username;
    String lastname;
    String password;
}

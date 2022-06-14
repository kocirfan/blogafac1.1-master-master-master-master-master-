package com.blogafac.kocirfan.request;

import lombok.Data;

@Data
public class RefreshRequest {

    Long userId;
    String refreshToken;
}

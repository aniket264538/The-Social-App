package com.socialapp.entities;

import lombok.Data;

import javax.persistence.Id;

@Data
public class Bookmark {

    @Id
    private Long userId;

    private Long postId;
}

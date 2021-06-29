package com.example.Library.entity;

import javax.persistence.Id;

public class AbstractEntity {

    @Id
    private Long id;

    private Long createdAt;
    private Long updatedAt;
}

package com.example.Library.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "book_items")
@Data
public class BookItems extends AbstractEntity{

    private Book book;
    private boolean borrowed;
    private Long dueDate;
    private UserEntity user;
}

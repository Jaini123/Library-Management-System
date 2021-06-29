package com.example.Library.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "book")
public class Book extends AbstractEntity{

    private String author;
    private String title;
    private String genre;
    private String year;
    private Double amount;
}

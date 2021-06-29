package com.example.Library.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookRequest {
    private String author;
    private String title;
    private String publication;
    private String genre;
    private String year;
    private BigDecimal amount;
}

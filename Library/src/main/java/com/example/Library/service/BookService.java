package com.example.Library.service;

import com.example.Library.entity.BookItems;
import com.example.Library.request.BookRequest;

import java.util.List;

public interface BookService {

    public void addBook(BookRequest bookRequest);
    public List<BookItems> getBooks(String author, String title, String genre, String year, String publication);
}

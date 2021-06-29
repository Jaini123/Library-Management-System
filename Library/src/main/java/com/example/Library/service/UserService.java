package com.example.Library.service;

import com.example.Library.request.UserRequest;

public interface UserService {

    public void addUser(UserRequest userRequest);
    public boolean borrowBook(Long userId, Long bookId);
    public boolean returnBook(Long userId, Long bookId);
}

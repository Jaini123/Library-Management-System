package com.example.Library.service;

import com.example.Library.entity.Book;
import com.example.Library.entity.BookItems;
import com.example.Library.entity.UserEntity;
import com.example.Library.repository.BookItemsRepository;
import com.example.Library.repository.BookRepository;
import com.example.Library.repository.UserRepository;
import com.example.Library.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpAnd;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.DAYS;

public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookItemsRepository bookItemsRepository;

    @Override
    public void addUser(UserRequest userRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userEntity.getEmail());
        userRepository.save(userEntity);
    }

    @Override
    public boolean borrowBook(Long userId, Long bookId) {
        BookItems bookItem= bookItemsRepository.findById(bookId).get();
        UserEntity userEntity = userRepository.findById(userId).get();

        if(userEntity==null) return false;
        if(bookItem==null) return false;
        if(bookItem.isBorrowed()) return false;

        List<BookItems> existingBooks = userEntity.getBooks();
        existingBooks.add(bookItem);
        userEntity.setBooks(existingBooks);
        return true;
    }

    @Override
    public boolean returnBook(Long userId, Long bookId) {
        BookItems bookItem= bookItemsRepository.findById(bookId).get();
        UserEntity userEntity = userRepository.findById(userId).get();

        if(userEntity==null) return false;
        if(bookItem==null) return false;

        Double penaltyAmount = checkForPenalty(userEntity, bookItem);
        userEntity.setPenaltySoFar(userEntity.getPenaltySoFar() + penaltyAmount);
        List<BookItems> existingBooks = userEntity.getBooks();
        existingBooks.remove(bookItem);
        return true;
    }

    private Double checkForPenalty(UserEntity userEntity, BookItems bookItems){
        Double penaltyAmount = 0d;
        Double bookPrice = bookItems.getBook().getAmount();
        Long noOfDays = 0l;
        if(bookItems.getDueDate()<System.currentTimeMillis()/1000){
            noOfDays = DAYS.convert(System.currentTimeMillis()/1000-bookItems.getDueDate(), TimeUnit.SECONDS)
        }
        if(noOfDays>5){
            penaltyAmount += 5*(0.5*bookPrice/100);
            if(penaltyAmount>3*bookPrice){
                return 3*bookPrice;
            }
            noOfDays= noOfDays-5;
        }
        else{
            penaltyAmount += noOfDays*(0.5*bookPrice/100);
            if(penaltyAmount>3*bookPrice){
                return 3*bookPrice;
            }
            noOfDays = 0l;
        }
        if(noOfDays>0){
            Double prevDayPenalty = 0.5*bookItems.getBook().getAmount()/100;
            while(noOfDays-->0){
                double currPenalty = prevDayPenalty/100;
                penaltyAmount+=currPenalty;
                if(penaltyAmount>3*bookPrice){
                    return 3*bookPrice;
                }
                prevDayPenalty = currPenalty;
            }
        }
        return penaltyAmount;
    }
}

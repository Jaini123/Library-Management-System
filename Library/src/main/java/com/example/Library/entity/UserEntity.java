package com.example.Library.entity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data
@Table(name = "user")
public class UserEntity extends AbstractEntity{
    private String name;
    private String email;
    private int noOfBooks;
    private Long onboardedDate;
    private List<BookItems> books;
    private Double penaltySoFar;
}



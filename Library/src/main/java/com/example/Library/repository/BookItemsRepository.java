package com.example.Library.repository;

import com.example.Library.entity.BookItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookItemsRepository extends JpaRepository<BookItems, Long> {
}

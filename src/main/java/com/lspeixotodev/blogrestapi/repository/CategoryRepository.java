package com.lspeixotodev.blogrestapi.repository;

import com.lspeixotodev.blogrestapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

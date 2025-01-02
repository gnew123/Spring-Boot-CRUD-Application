package com.spring.blog.repository;

import com.spring.blog.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long>  {
    List<Author> findByName(String name) ;
    List<Author> findByEmail(String email);

    List<Author> findByEmailOrName (String name, String email) ;
}

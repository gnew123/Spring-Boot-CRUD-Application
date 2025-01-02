package com.spring.blog.services;

import com.spring.blog.payload.AuthorDTO;
import com.spring.blog.payload.onlyAuthorDTO;

import java.util.List;

public interface AuthorService {
    AuthorDTO createAuthor(AuthorDTO authorDTO);
    AuthorDTO getAuthorById(Long id);
    List<AuthorDTO> getAllAuthors();
    AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO);
    void deleteAuthor(Long id);
    List<onlyAuthorDTO> getAllAuthorsBasic();
    List<onlyAuthorDTO> searchAuthor(String name, String email);
}


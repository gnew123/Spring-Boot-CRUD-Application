package com.spring.blog.controller;

import com.spring.blog.payload.AuthorDTO;
import com.spring.blog.payload.onlyAuthorDTO;
import com.spring.blog.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO authorDTO) {
        AuthorDTO savedAuthor = authorService.createAuthor(authorDTO);
        return new ResponseEntity<>(savedAuthor, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id) {
        AuthorDTO authorDTO = authorService.getAuthorById(id);
        return ResponseEntity.ok(authorDTO);
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        List<AuthorDTO> authors = authorService.getAllAuthors();
        return ResponseEntity.ok(authors);
    }

    @GetMapping("/only")
    public ResponseEntity<List<onlyAuthorDTO>> onlyAuthor() {
        List<onlyAuthorDTO> onlyAuthorDTOS = authorService.getAllAuthorsBasic();
        return ResponseEntity.ok(onlyAuthorDTOS);
    }

    @GetMapping("/search")
    public ResponseEntity<List<onlyAuthorDTO>> searchAuthor(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
                List<onlyAuthorDTO> searchAuthor = authorService.searchAuthor(name,email);
                return ResponseEntity.ok(searchAuthor);
    }


    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(
            @PathVariable Long id,
            @RequestBody AuthorDTO authorDTO) {
        AuthorDTO updatedAuthor = authorService.updateAuthor(id, authorDTO);
        return ResponseEntity.ok(updatedAuthor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }
}
package com.spring.blog.serviceImpl;

import com.spring.blog.entity.Book;
import com.spring.blog.entity.Author;
import com.spring.blog.exception.ResourceNotFoundException;
import com.spring.blog.payload.BookDTO;
import com.spring.blog.repository.BookRepository;
import com.spring.blog.repository.AuthorRepository;
import com.spring.blog.services.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        // Convert DTO to entity
        Book book = modelMapper.map(bookDTO, Book.class);

        // Set the author
        Author author = authorRepository.findById(bookDTO.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", bookDTO.getAuthorId()));
        book.setAuthor(author);

        // Save the book
        Book savedBook = bookRepository.save(book);

        // Convert back to DTO
        BookDTO response = modelMapper.map(savedBook, BookDTO.class);
        response.setAuthorId(author.getId());

        return response;
    }

    @Override
    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));
        return modelMapper.map(book, BookDTO.class);
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));

        book.setBookName(bookDTO.getBookName());
        book.setAbout(bookDTO.getAbout());
        if (!book.getAuthor().getId().equals(bookDTO.getAuthorId())) {
            Author author = authorRepository.findById(bookDTO.getAuthorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Author", "id", bookDTO.getAuthorId()));
            book.setAuthor(author);
        }

        Book updatedBook = bookRepository.save(book);
        return modelMapper.map(updatedBook, BookDTO.class);
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));
        bookRepository.delete(book);
    }

    @Override
    public List<BookDTO> getBooksByAuthorId(Long authorId) {
        return List.of();
    }

//    @Override
//    public List<BookDTO> getBooksByAuthorId(Long authorId) {
//        List<Book> books = bookRepository.findByAuthorId(authorId);
//        return books.stream()
//                .map(book -> modelMapper.map(book, BookDTO.class))
//                .collect(Collectors.toList());
//    }
}
package com.spring.blog.serviceImpl;

import com.spring.blog.entity.Author;
import com.spring.blog.exception.ResourceNotFoundException;
import com.spring.blog.payload.AuthorDTO;
import com.spring.blog.payload.onlyAuthorDTO;
import com.spring.blog.repository.AuthorRepository;
import com.spring.blog.services.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author author = modelMapper.map(authorDTO, Author.class);
        Author savedAuthor = authorRepository.save(author);
        return modelMapper.map(savedAuthor, AuthorDTO.class);
    }

    @Override
    public AuthorDTO getAuthorById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", id));
        return modelMapper.map(author, AuthorDTO.class);
    }

    @Override
    public List<AuthorDTO> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
                .map(author -> modelMapper.map(author, AuthorDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", id));

        author.setName(authorDTO.getName());
        author.setEmail(authorDTO.getEmail());
        author.setAbout(authorDTO.getAbout());

        Author updatedAuthor = authorRepository.save(author);
        return modelMapper.map(updatedAuthor, AuthorDTO.class);
    }

    @Override
    public void deleteAuthor(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", id));
        authorRepository.delete(author);
    }

    @Override
    public List<onlyAuthorDTO> getAllAuthorsBasic() {
        List<Author> onlyAuthor = authorRepository.findAll();
        return onlyAuthor.stream().map(author -> modelMapper.map(author, onlyAuthorDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<onlyAuthorDTO> searchAuthor(String name, String email) {
        List<Author> authors;

        if(name != null && email != null) {
            authors = authorRepository.findByEmailOrName(name,email);
        } else if (name != null) {
            authors = authorRepository.findByName(name) ;
        } else if (email != null) {
            authors = authorRepository.findByEmail(email);
        } else {
            authors = authorRepository.findAll();
        }
        return authors.stream().map(author -> modelMapper.map(author, onlyAuthorDTO.class)).collect(Collectors.toList());
    }
}
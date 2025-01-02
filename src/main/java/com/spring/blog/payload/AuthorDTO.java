package com.spring.blog.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AuthorDTO {
    private Long id;
    private String name;
    private String email;
//    private String password;
    private String about;
    private List<BookDTO> books;
}

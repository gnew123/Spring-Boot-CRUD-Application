package com.spring.blog.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class onlyAuthorDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String about;
}

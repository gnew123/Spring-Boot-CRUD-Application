package com.spring.blog.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookDTO {
    private Long id ;
    private String bookName;
    private String about ;
    private Long authorId;
}

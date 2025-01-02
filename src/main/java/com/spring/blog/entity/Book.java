package com.spring.blog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String bookName;
    @Column(nullable = false)
    private String about;
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author ;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

//    @ManyToOne
//    @JoinColumn(name = "price_range_id")
//    private PriceRange priceRange;
}

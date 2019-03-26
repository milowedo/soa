package com.milosz.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String title;
    private String author;
    private String type;
    private Integer price;
    private String currency;
    private int pageNumber;
}

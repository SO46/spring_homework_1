package org.example.web.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Book {
    private Integer id;
    private String author;
    private String title;
    private Integer size;
}

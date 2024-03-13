package com.example.exservice.Model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class NewsArticle {

    @NotNull(message = "id cannot be null")
    private Integer ID ;


    @NotEmpty(message = "title cannot be empty")
    @Size(max = 100 , message = "title maximum length of 100 characters")
    private String title ;


    @NotEmpty(message = "title cannot be empty")
    @Size(min = 4 , message = "author must be more than 4 characters")
    @Size(max = 20 , message = "author maximum length of 20 characters")
    private String author ;


    @NotEmpty(message = "content cannot be empty")
//    @Size(min = 200 , message = "content must be more than 200 characters")
    private String content ;


    @NotEmpty(message = "category cannot be empty")
    @Pattern(regexp = "^(politics|sports|technology)$" , message = "category must be either \"politics\", \" sports\" or \" technology\" only")
    private String category ;


    @NotEmpty(message = "imageUrl cannot be empty")
    private String imageUrl ;


    @AssertFalse
    private Boolean isPublished ;


    @DateTimeFormat
    private LocalDate publishDate ;




}

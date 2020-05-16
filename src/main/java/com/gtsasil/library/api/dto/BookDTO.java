package com.gtsasil.library.api.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private Integer id;
    private String title;
    private String author;
    private String isbn;
}

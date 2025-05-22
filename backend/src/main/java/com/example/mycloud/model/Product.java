package com.example.mycloud.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String imageUrl;

    private String filename;
    private String filetype;
    private Long filesize;
    private Integer width;
    private Integer height;
}

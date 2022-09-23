package com.example.cafe_menu.entity;


import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Product implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private String price;
    private String description;
    private String category;
    private String isAvailable;

}

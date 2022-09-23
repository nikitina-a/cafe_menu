package com.example.cafe_menu.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductDTOResponse {

    private String name;
    private String price;
    private String description;
    private String category;
    private String isAvailable;

}

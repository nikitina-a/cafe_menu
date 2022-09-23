package com.example.cafe_menu.utils;


import com.example.cafe_menu.entity.Product;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class ProductInfoCloner {

    private final ProductFileWriter productFileWriter;

    @SneakyThrows
    public void copyProductToFileWithListOfAllProducts(String product) {


        String DIRECTORY = "cafe";
        File directory = new File(DIRECTORY);

        if (!directory.exists()) {
          directory.mkdir();
        }

        String FILENAME = "products.cafe";
        File productsList = new File(directory, FILENAME);

        if(!productsList.exists()) {
            productsList.createNewFile();
        }


        productFileWriter.writeToFileWithListOfProducts(product,productsList);


    }


}

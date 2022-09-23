package com.example.cafe_menu.utils;

import com.example.cafe_menu.entity.Product;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductFileReader {


    public String readFromFile(String filePath) {

        String product = "";


        try(
                BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
                ) {

            product = bufferedReader.readLine();


        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return product;
    }

    @SneakyThrows
    public List<String> readProductsFromFile(String path) {
        List<String> products = new ArrayList<>();

        try(
                BufferedReader bufferedReader = new BufferedReader(new FileReader(path))
                ) {

            products = bufferedReader.lines().toList();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return products;
    }
}

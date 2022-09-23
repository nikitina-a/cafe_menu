package com.example.cafe_menu.utils;

import com.example.cafe_menu.entity.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.*;


@Component
public class ProductFileWriter {

    private final ObjectMapper objectMapper = new ObjectMapper();


    @SneakyThrows
    public String writeToFile(Product product, File fileName) {

        objectMapper.writeValue(fileName, product);

        return objectMapper.writeValueAsString(product);

    }

    public void writeToFileWithListOfProducts(String product, File filePath) {


        try (
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true));
        ) {

            bufferedWriter.write(product);
            bufferedWriter.newLine();


        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @SneakyThrows
    public void rewriteToFileWithListOfProducts(String product, File filePath) {


        try (
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
        ) {

            bufferedWriter.write(product);
            bufferedWriter.newLine();


        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }


}

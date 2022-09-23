package com.example.cafe_menu.repository;

import com.example.cafe_menu.entity.Product;
import com.example.cafe_menu.utils.FileOrDirectoryEraser;
import com.example.cafe_menu.utils.ProductFileReader;
import com.example.cafe_menu.utils.ProductFileWriter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.util.List;
import java.util.stream.Stream;

@Repository
@AllArgsConstructor
public class ProductFileRepository {
    private final ProductFileWriter productFileWriter;
    private final ProductFileReader productFileReader;
    private final FileOrDirectoryEraser fileOrDirectoryEraser;


    public String saveProduct(Product product, File fileName) {

        return productFileWriter.writeToFile(product,fileName);

    }


    public String findProductByPath(String fullPath) {
        return productFileReader.readFromFile(fullPath);
    }

    public String findProductByCategoryAndName(String category,String name) {
        return  findAllProducts().stream()
                .filter(p->p.contains(category) && p.contains(name))
                .findFirst()
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }



    public List<String> findAllProducts() {
        return productFileReader.readProductsFromFile("cafe/products.cafe");


    }

    public List<String> findAllAvailableProducts() {

        return findAllProducts().stream()
                .filter(p->p.contains("true"))
                .toList();


    }

    public void deleteByPath(String fullPath) {

        fileOrDirectoryEraser.delete(fullPath);


    }

    public void rewrite(String p, String fullPath) {
        productFileWriter.rewriteToFileWithListOfProducts(p,new File(fullPath));
    }


}

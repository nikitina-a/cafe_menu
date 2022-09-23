package com.example.cafe_menu.controller;

import com.example.cafe_menu.dto.ProductDTORequest;
import com.example.cafe_menu.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/api/product")
    public void createProduct(@RequestBody ProductDTORequest request) {
        productService.createProduct(request);
    }

    @GetMapping("/api/products")
    public List<String> getAllProducts() {
       return productService.findAllProducts();
    }

    @GetMapping("/api/products/available")
    public List<String> getAllAvailableProducts() {
        return productService.findAllAvailableProducts();
    }

    @GetMapping("/api/product")
    public String findProductByPath(@RequestParam(name = "path") String path) {
        return productService.findProduct(path);
    }

    @GetMapping("/api/product/category")
    public String findProductByCategoryAndName(@RequestParam(name = "category") String category,
                                               @RequestParam(name = "name") String name) {
        return productService.findProductByCategoryAndName(category, name);
    }

    @DeleteMapping("/api/product/delete")
    public void deleteByPath(@RequestParam(name = "path") String path) {
        productService.deleteProductByFullPath(path);
    }


}

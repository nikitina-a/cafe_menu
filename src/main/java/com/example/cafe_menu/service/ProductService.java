package com.example.cafe_menu.service;

import com.example.cafe_menu.dto.ProductDTORequest;

import java.util.List;

public interface ProductService {

    public void createProduct(ProductDTORequest request);

    public List<String> findAllProducts();

    public List<String> findAllAvailableProducts();

    public String findProduct(String fullPath);

    public String findProductByCategoryAndName(String category,String name);

    public void deleteProductByFullPath(String fullPath);

    public void deleteProductByCategoryAndName(String category,String name);
}

package com.example.cafe_menu.service.impl;

import com.example.cafe_menu.dto.ProductDTORequest;
import com.example.cafe_menu.entity.Product;
import com.example.cafe_menu.repository.ProductFileRepository;
import com.example.cafe_menu.service.ProductService;
import com.example.cafe_menu.utils.ProductInfoCloner;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.util.List;


@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductFileRepository productFileRepository;
    private final ProductInfoCloner productInfoCloner;



    @Override
    @SneakyThrows
    public void createProduct(ProductDTORequest request) {


        File directory = new File(String.format("cafe/products/%s",request.getCategory()));

        if (!directory.exists()) {
            directory.mkdirs();
        }

        String productFileName = String.format("%s.cafe",request.getName());

        File productFile = new File(directory,productFileName );

        if(!productFile.exists()) {
            var product = Product.builder()
                    .name(request.getName())
                    .category(request.getCategory())
                    .description(request.getDescription())
                    .price(request.getPrice())
                    .isAvailable(request.getIsAvailable())
                    .build();

            productFile.createNewFile();
            var productString = productFileRepository.saveProduct(product,productFile);
            productInfoCloner.copyProductToFileWithListOfAllProducts(productString);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("Product with name %s in category %s already exists",
                            request.getName(),request.getCategory()));
        }




    }

    public List<String> findAllProducts() {
        return productFileRepository.findAllProducts();
    }

    public List<String> findAllAvailableProducts() {
        return productFileRepository.findAllAvailableProducts();
    }

    @Override
    public String findProduct(String fullPath) {
        return productFileRepository.findProductByPath(fullPath);
    }

    @Override
    public String findProductByCategoryAndName(String category, String name) {
        return productFileRepository.findProductByCategoryAndName(category, name);
    }

    @Override
    public void deleteProductByFullPath(String fullPath) {

        var product = findProduct(fullPath);
        File toDelete = new File(fullPath);
        toDelete.delete();
       // productFileRepository.clearSpace("cafe/products.cafe");


       // productFileRepository.deleteByPath(fullPath);
        findAllProducts().stream()
                .filter(p->!p.equals(product))
                .forEach(p->productFileRepository.rewrite(p,"cafe/products.cafe"));



    }

    @Override
    public void deleteProductByCategoryAndName(String category, String name) {


    }


}

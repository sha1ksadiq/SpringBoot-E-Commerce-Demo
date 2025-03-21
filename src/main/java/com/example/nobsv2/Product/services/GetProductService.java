package com.example.nobsv2.Product.services;

import com.example.nobsv2.Product.ProductRepository;
import com.example.nobsv2.Product.model.Product;
import com.example.nobsv2.Product.model.ProductDTO;
import com.example.nobsv2.Query;
import exception.ProductNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetProductService implements Query<Integer, ProductDTO> {

    private final ProductRepository productRepository;

    public GetProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<ProductDTO> execute(Integer input) {
        Optional<Product> productOptional = productRepository.findById(input);
        if(productRepository.existsById(input)) {
            return ResponseEntity.ok(new ProductDTO(productOptional.get()));
        }


        throw new ProductNotFoundException();
    }
}

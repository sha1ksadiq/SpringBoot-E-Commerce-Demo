package com.example.nobsv2.Product.services;

import com.example.nobsv2.Command;
import com.example.nobsv2.Product.ProductRepository;
import com.example.nobsv2.Product.model.Product;
import com.example.nobsv2.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteProductService implements Command<Integer, Void> {

    private ProductRepository productRepository;

    public DeleteProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<Void> execute(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()) {
            productRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        throw new ProductNotFoundException();
    }
}

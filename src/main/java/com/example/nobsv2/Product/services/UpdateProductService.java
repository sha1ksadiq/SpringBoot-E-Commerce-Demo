package com.example.nobsv2.Product.services;

import com.example.nobsv2.Command;
import com.example.nobsv2.Product.ProductRepository;
import com.example.nobsv2.Product.model.Product;
import com.example.nobsv2.Product.model.ProductDTO;
import com.example.nobsv2.Product.model.UpdateProductCommand;
import exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateProductService implements Command<UpdateProductCommand, ProductDTO> {

    private ProductRepository productRepository;

    public UpdateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<ProductDTO> execute(UpdateProductCommand updateProductCommand) {
        Optional<Product> optionalProduct = productRepository.findById(updateProductCommand.getId());
        if(optionalProduct.isPresent()) {
            Product product = updateProductCommand.getProduct();
            product.setId(updateProductCommand.getId());
            productRepository.save(product);
            return ResponseEntity.ok(new ProductDTO(product));
        }


        throw new ProductNotFoundException();
    }
}

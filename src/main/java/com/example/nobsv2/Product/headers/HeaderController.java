package com.example.nobsv2.Product.headers;

import com.example.nobsv2.Product.model.Product;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeaderController {

    @GetMapping("/header")
    public String getRegionalResponse(@RequestHeader(required = false, defaultValue = "US") String region) {
        if(region.equals("US")) return "Hello US!";

        if(region.equals("CAN")) return "Hello CAN!";

        return "Hello GLOBAL!";
    }

    @GetMapping(value = "/header/product", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Product> getProduct() {
        Product product = new Product();
        product.setId(1);
        product.setName("header_product_name");
        product.setDescription("header_product_description");
        product.setPrice(9.99);

        return ResponseEntity.ok(product);
    }
}

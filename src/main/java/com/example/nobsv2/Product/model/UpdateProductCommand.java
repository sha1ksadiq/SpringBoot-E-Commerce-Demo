package com.example.nobsv2.Product.model;

public class UpdateProductCommand {
    private Integer id;
    private Product product;

    public Integer getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public UpdateProductCommand(Integer id, Product product){
        this.product = product;
        this.id = id;
    }
}

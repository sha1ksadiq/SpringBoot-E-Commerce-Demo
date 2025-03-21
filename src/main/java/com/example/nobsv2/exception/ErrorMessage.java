package com.example.nobsv2.exception;

public enum ErrorMessage {
    PRODUCT_NOT_FOUND("Product Not Found!"),
    NAME_REQUIRED("Name is required!"),
    DESCRIPTION_LENGTH("Description needs to be atleast 20 characters long!"),
    PRICE_CANNOT_BE_NEGATIVE("Price cannot be null or negative!");
    // add more to this list as needed

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

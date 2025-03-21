package exception;

public enum ErrorMessage {
    PRODUCT_NOT_FOUND("Product Not Found!");
    // add more to this list as needed

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

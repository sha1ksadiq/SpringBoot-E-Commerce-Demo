package Validators;

import com.example.nobsv2.Product.model.Product;
import com.example.nobsv2.exception.ProductNotValidException;
import com.mysql.cj.util.StringUtils;

public class ProductValidator {
    private ProductValidator() {

    }

    public static void execute(Product product) {
        if(StringUtils.isEmptyOrWhitespaceOnly(product.getName())) {
            throw new ProductNotValidException("Name is required!");
        }
        if((product.getDescription().length() < 20)) {
            throw new ProductNotValidException("Description must be atleast 20 characters long!");
        }
        if(product.getPrice() == 0.0 || product.getPrice() < 0.0) {
            throw new ProductNotValidException("Price cannot be null or negative!");
        }
    }
}

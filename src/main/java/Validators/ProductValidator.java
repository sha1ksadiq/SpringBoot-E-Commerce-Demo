package Validators;

import com.example.nobsv2.Product.model.Product;
import com.example.nobsv2.exception.ErrorMessage;
import com.example.nobsv2.exception.ProductNotValidException;
import com.mysql.cj.util.StringUtils;

public class ProductValidator {
    private ProductValidator() {

    }

    public static void execute(Product product) {
        if(StringUtils.isEmptyOrWhitespaceOnly(product.getName())) {
            throw new ProductNotValidException(ErrorMessage.NAME_REQUIRED.getMessage());
        }
        if((product.getDescription().length() < 20)) {
            throw new ProductNotValidException(ErrorMessage.DESCRIPTION_LENGTH.getMessage());
        }
        if(product.getPrice() == 0.0 || product.getPrice() < 0.0) {
            throw new ProductNotValidException(ErrorMessage.PRICE_CANNOT_BE_NEGATIVE.getMessage());
        }
    }
}

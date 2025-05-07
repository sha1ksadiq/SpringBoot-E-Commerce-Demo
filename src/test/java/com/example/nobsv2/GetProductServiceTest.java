package com.example.nobsv2;

import com.example.nobsv2.Product.ProductRepository;
import com.example.nobsv2.Product.model.Product;
import com.example.nobsv2.Product.model.ProductDTO;
import com.example.nobsv2.Product.services.GetProductService;
import com.example.nobsv2.exception.ProductNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class GetProductServiceTest {

    @Mock // what to mock the response of -> need this depedency to run the test
    private ProductRepository productRepository;

    @InjectMocks //the thing that is being tested
    private GetProductService getProductService;

    @BeforeEach // things we need before the tests run to set up properly
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_product_exists_when_get_product_service_return_product_dto() {
        // Given
        Product product = new Product();
        product.setId(1);
        product.setName("test_product_name");
        product.setDescription("test_product_description");
        product.setPrice(9.99);

        when(productRepository.findById(1)).thenReturn(Optional.of(product));
        when(productRepository.existsById(1)).thenReturn(true); // Add this line

        // When
        ResponseEntity<ProductDTO> response = getProductService.execute(1);
        ProductDTO responseBody = response.getBody();

        // Then
        assertEquals(200, response.getStatusCode().value());
        assertEquals(1, responseBody.getId());
        assertEquals("test_product_name", responseBody.getName());
        assertEquals("test_product_description", responseBody.getDescription());
        assertEquals(9.99, responseBody.getPrice());
        verify(productRepository, times(1)).findById(1);
        verify(productRepository, times(1)).existsById(1);
    }

    @Test
    public void given_product_does_not_exist_when_get_product_service_throw_product_not_found_exception() {
        // Given
        when(productRepository.findById(1)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(ProductNotFoundException.class, () -> getProductService.execute(1));
        verify(productRepository, times(1)).findById(1);
    }

}

package com.example.nobsv2;

import com.example.nobsv2.Product.ProductRepository;
import com.example.nobsv2.Product.model.Product;
import com.example.nobsv2.Product.model.ProductDTO;
import com.example.nobsv2.Product.services.CreateProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CreateProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CreateProductService createProductService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_valid_product_when_create_product_then_return_saved_product_dto() {
        // Given
        Product testProduct = new Product();
        testProduct.setId(1);
        testProduct.setName("test_name");
        testProduct.setDescription("test_description_something");
        testProduct.setPrice(12.50);

        when(productRepository.save(any(Product.class))).thenReturn(testProduct);

        // When
        ResponseEntity<ProductDTO> response = createProductService.execute(testProduct);
        ProductDTO responseBody = response.getBody();

        // Then
        assertEquals(201, response.getStatusCode().value());
        assertEquals(1, responseBody.getId());
        assertEquals("test_name", responseBody.getName());
        assertEquals("test_description_something", responseBody.getDescription());
        assertEquals(12.50, responseBody.getPrice());
        verify(productRepository, times(1)).save(any(Product.class));
    }

}

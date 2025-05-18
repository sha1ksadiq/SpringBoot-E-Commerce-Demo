package com.example.nobsv2;

import com.example.nobsv2.Product.ProductRepository;
import com.example.nobsv2.Product.model.Product;
import com.example.nobsv2.Product.services.DeleteProductService;
import com.example.nobsv2.exception.ProductNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DeleteProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private DeleteProductService deleteProductService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_product_exists_when_delete_product_then_success_response() {
        // Given
        int productId = 1;
        Product product = new Product();
        product.setId(productId);
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // When
        ResponseEntity<Void> response = deleteProductService.execute(productId);

        // Then
        assertEquals(204, response.getStatusCode().value()); // NO_CONTENT is 204
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).deleteById(productId);
    }

    @Test
    public void given_product_does_not_exist_when_delete_product_then_throw_exception() {
        // Given
        int productId = 1;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(ProductNotFoundException.class, () -> deleteProductService.execute(productId));
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, never()).deleteById(productId);
    }
}

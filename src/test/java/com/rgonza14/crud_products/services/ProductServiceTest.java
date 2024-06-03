package com.rgonza14.crud_products.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rgonza14.crud_products.repositories.ProductRepository;
import com.rgonza14.crud_products.exceptions.ResourceNotFoundException;
import com.rgonza14.crud_products.models.ProductModel;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    @DisplayName("getProducts: Debería retornar todos los productos existentes")
    void testGetProducts() {
        List<ProductModel> mockProducts = new ArrayList<>();
        mockProducts.add(new ProductModel(1L, "Iphone 8", 650000, 8));
        mockProducts.add(new ProductModel(2L, "Xbox series s", 1850000, 3));

        // Se configura el comportamiento del repositorio simulado
        Mockito.when(productRepository.findAll()).thenReturn(mockProducts);
        List<ProductModel> products = productService.getProducts();

        assertEquals(2, products.size());
        Mockito.verify(productRepository, times(1)).findAll();

    }

    @Test
    @DisplayName("getProductById: Debería retornar el producto según id")
    void testGetProductById() {
        ProductModel mockProduct = new ProductModel(1L, "Tv TCL 4k", 2300000, 2);
        Mockito.when(productRepository.findById(anyLong())).thenReturn(Optional.of(mockProduct));

        ProductModel product = productService.getProductById(1L);

        assertNotNull(product);
        Mockito.verify(productRepository, times(1)).findById(1L);

    }

    @Test
    @DisplayName("getProductByIdNotFound: Debería lanzar una excepción al no existir el id especificado")
    void testGetProductByIdNotFound() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            productService.getProductById(1L);
        });
        Mockito.verify(productRepository, times(1)).findById(1L);

    }

    @Test
    @DisplayName("deleteProductById: Debería eliminar el producto según id y retornar true")
    void testDeleteProductById() {
        when(productRepository.existsById(anyLong())).thenReturn(true);
        doNothing().when(productRepository).deleteById(anyLong());

        boolean result = productService.deleteProductById(1L);

        assertTrue(result);
        Mockito.verify(productRepository, times(1)).deleteById(1L);

    }

    @Test
    @DisplayName("detelProductByIdNotFound: Debería retorar false ante un id que no existe")
    void testDeleteProductByIdNotFound() {
        when(productRepository.existsById(anyLong())).thenReturn(false);
        boolean result = productService.deleteProductById(1L);
        assertFalse(result);
    }

    @Test
    @DisplayName("updateProductById: Debería actualizar un producto según un id")
    void testUpdateProductById() {
        // Crear un producto existente en la base de datos
        ProductModel existingProduct = new ProductModel(1L, "Impresora HP", 325000, 3);
        when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));

        // Detalles actualizados del producto
        ProductModel updatedProductDetails = new ProductModel(1L, "Impresora HP actualizada", 350000, 5);
        productService.updateProductById(updatedProductDetails, 1L);

        verify(productRepository).findById(1L);
        verify(productRepository).save(updatedProductDetails);
    }

}

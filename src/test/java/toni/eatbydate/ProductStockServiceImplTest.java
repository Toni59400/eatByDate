package toni.eatbydate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import toni.eatbydate.entity.Product;
import toni.eatbydate.repository.ProductRepo;
import toni.eatbydate.service.impl.ProductStockServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductStockServiceImplTest {

    @Mock
    private ProductRepo productRepo;

    @InjectMocks
    private ProductStockServiceImpl productStockService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
//        productStockService = new ProductStockServiceImpl(productRepo);
//        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetProductById() {
        // Given
        long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        when(productRepo.getById(productId)).thenReturn(product);

        // When
        Product result = productStockService.getProductById(productId);

        // Then
        assertEquals(productId, result.getId());
    }

    @Test
    void testGetAllProducts() {
        // Given
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        productList.add(new Product());
        when(productRepo.findAll()).thenReturn(productList);

        // When
        List<Product> result = productStockService.getAllProducts();

        // Then
        assertEquals(2, result.size());
    }

    @Test
    void testGetProductsByReserveId() {
        // Given
        long reserveId = 1L;
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        when(productRepo.findByReserve(reserveId)).thenReturn(productList);

        // When
        List<Product> result = productStockService.getProductsByReserveId(reserveId);

        // Then
        assertEquals(1, result.size());
    }

    @Test
    void testSaveProduct() {
        // Given
        Product product = new Product();
        when(productRepo.save(product)).thenReturn(product);

        // When
        Product result = productStockService.saveProduct(product);

        // Then
        assertEquals(product, result);
    }

    @Test
    void testDelete() {
        // Given
        Product product = new Product();

        // When
        productStockService.delete(product);

        // Then
        verify(productRepo, times(1)).delete(product);
    }
}
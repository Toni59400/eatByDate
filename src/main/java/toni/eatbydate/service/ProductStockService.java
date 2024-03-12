package toni.eatbydate.service;

import toni.eatbydate.entity.Product;

import java.util.List;

public interface ProductStockService {

    void delete(Product p);
    Product getProductById(Long id);
    List<Product> getAllProducts();
    List<Product> getProductsByReserveId(Long reserveId);
    Product saveProduct(Product product);
}

package toni.eatbydate.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import toni.eatbydate.entity.Product;
import toni.eatbydate.repository.ProductRepo;
import toni.eatbydate.service.ProductService;
import toni.eatbydate.service.ProductStockService;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductStockServiceImpl implements ProductStockService {

    @Autowired
    private ProductRepo productRepository;


    @Override
    public Product getProductById(Long id){
        return productRepository.getById(id);
    }
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByReserveId(Long reserveId) {
        return productRepository.findByReserve(reserveId);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Product p){
        productRepository.delete(p);
    }


}

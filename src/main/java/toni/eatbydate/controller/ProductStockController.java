package toni.eatbydate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toni.eatbydate.entity.Product;
import toni.eatbydate.entity.Reserve;
import toni.eatbydate.service.ProductStockService;

import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/products")
public class ProductStockController {

    @Autowired
    private ProductStockService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("reserve/{idReserve}")
    public ResponseEntity<List<Product>> getProductsByReserve(@PathVariable Long idReserve) {
        List<Product> produits = productService.getProductsByReserveId(idReserve);
        if(produits.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(produits, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Product> saveOrUpdateProduct(@RequestBody Product product) {
        if (product.getId() != null) {
            return updateProduct(product);
        } else {
            return createProduct(product);
        }
    }

    private ResponseEntity<Product> createProduct(Product product) {
        Product savedProduct = productService.saveProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.OK);
    }

    private ResponseEntity<Product> updateProduct(Product product) {
        Product existingProduct = productService.getProductById(product.getId());
        if (existingProduct != null) {
            if(product.getQuantite() == 0 ){
                productService.delete(existingProduct);
                return new ResponseEntity<>(HttpStatus.OK);
            }else {
                existingProduct.setQuantite(product.getQuantite());

                Product updatedProduct = productService.saveProduct(existingProduct);
                return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}


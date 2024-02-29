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


}


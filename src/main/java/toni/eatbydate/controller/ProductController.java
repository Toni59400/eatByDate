package toni.eatbydate.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import toni.eatbydate.dto.ProductDTO;
import toni.eatbydate.service.ProductService;


@RestController
public class ProductController {


    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }



    @GetMapping("/product/{productCode}")
    public ResponseEntity<ProductDTO> getProductInfo(@PathVariable String productCode) {
        return ResponseEntity.ok(productService.getProductInfo(productCode));
    }


}

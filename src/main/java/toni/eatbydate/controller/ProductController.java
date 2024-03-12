package toni.eatbydate.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import toni.eatbydate.dto.ProductDTO;
import toni.eatbydate.service.ProductService;

import static org.springframework.security.authorization.AuthorityReactiveAuthorizationManager.hasRole;

@CrossOrigin("*")
@RestController
public class ProductController {


    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @CrossOrigin("*")
    @GetMapping("/product/{productCode}")
    public ResponseEntity<ProductDTO> getProductInfo(@PathVariable String productCode) {
        try {
            ProductDTO productDTO = productService.getProductInfo(productCode);
            return ResponseEntity.ok(productDTO);
        } catch (Exception e) {
            // Créer un ProductDTO avec des valeurs null
            ProductDTO nullProductDTO = new ProductDTO();
            nullProductDTO.setCode(productCode);
            nullProductDTO.setProduct_name(null);
            nullProductDTO.setImageUrl(null);

            // Renvoyer le ProductDTO avec des valeurs null
            return ResponseEntity.ok(nullProductDTO);
        }
    }


}

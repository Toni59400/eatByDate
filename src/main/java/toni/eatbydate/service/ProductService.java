package toni.eatbydate.service;
import org.springframework.http.ResponseEntity;
import toni.eatbydate.dto.ProductDTO;

public interface ProductService {
    ProductDTO getProductInfo(String productCode);
}

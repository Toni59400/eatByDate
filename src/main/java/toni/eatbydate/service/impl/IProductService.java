package toni.eatbydate.service.impl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import toni.eatbydate.dto.ProductDTO;
import toni.eatbydate.service.ProductService;

import java.io.IOException;
import java.util.Map;

@Service
public class IProductService implements ProductService {

    @Value("${openfoodfacts.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public IProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    /**
     *
     * @param productCode
     * @return ProductDTO, il contient le code barre du produit, son nom, et une url d'image.
     *
     * @description La fonction va interroger l'API d'OpenFoodFact pour récuperer les infos via le code barre du produit (productCode)
     */
    @Override
    public ProductDTO getProductInfo(String productCode) {
        String url = apiUrl + productCode;
        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

            // Get the JSON response from productService
            ResponseEntity<String> jsonResponse = responseEntity;

            // Deserialize JSON to Map
            Map<String, Object> map = objectMapper.readValue(jsonResponse.getBody(), new TypeReference<Map<String, Object>>() {});

            // Check if the product exists
            if (map.get("status_verbose") != "product not found") {
                Map<String, Object> productInfo = (Map<String, Object>) map.get("product");

                // Filter necessary information
                String code = (String) map.get("code");
                String genericName = (String) productInfo.get("product_name");
                String imageUrl = (String) productInfo.get("image_url");

                // Create a DTO object
                ProductDTO productDTO = new ProductDTO();
                productDTO.setCode(code);
                productDTO.setProduct_name(genericName);
                productDTO.setImageUrl(imageUrl);

                return productDTO;
            } else {
                // If the product does not exist, return a 404 Not Found
                return null;
            }
        } catch (IOException e) {
            return null;
        }
    }
}


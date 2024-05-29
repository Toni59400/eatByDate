package toni.eatbydate;


import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import toni.eatbydate.entity.Product;
import toni.eatbydate.repository.ProductRepo;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.Optional;


@SpringBootTest
@Transactional
@Rollback
@RunWith(SpringRunner.class)
public class TIProductRepoTest {


        @Autowired
        private ProductRepo productRepository;



        @Test
        public void testCreateProduct() {
            Product product = new Product();
            product.setApiId("api123");
            product.setExpirationDate(new Date());
            product.setQuantite(10);

            Product savedProduct = productRepository.save(product);

            assertThat(savedProduct).isNotNull();
            assertThat(savedProduct.getId()).isNotNull();
        }

        @Test
        public void testFindProductById() {
            Product product = new Product();
            product.setApiId("api123");
            product.setExpirationDate(new Date());
            product.setQuantite(10);

            Product savedProduct = productRepository.save(product);
            Optional<Product> foundProduct = productRepository.findById(savedProduct.getId());

            assertThat(foundProduct).isPresent();
            assertThat(foundProduct.get().getApiId()).isEqualTo("api123");
        }

        @Test
        public void testUpdateProduct() {
            Product product = new Product();
            product.setApiId("api123");
            product.setExpirationDate(new Date());
            product.setQuantite(10);

            Product savedProduct = productRepository.save(product);
            savedProduct.setQuantite(20);
            Product updatedProduct = productRepository.save(savedProduct);

            assertThat(updatedProduct.getQuantite()).isEqualTo(20);
        }

        @Test
        public void testDeleteProduct() {
            Product product = new Product();
            product.setApiId("api123");
            product.setExpirationDate(new Date());
            product.setQuantite(10);

            Product savedProduct = productRepository.save(product);
            productRepository.deleteById(savedProduct.getId());
            Optional<Product> foundProduct = productRepository.findById(savedProduct.getId());

            assertThat(foundProduct).isNotPresent();
        }
    }


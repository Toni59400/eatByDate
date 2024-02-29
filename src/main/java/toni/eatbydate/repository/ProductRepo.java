package toni.eatbydate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import toni.eatbydate.entity.Product;
import toni.eatbydate.entity.Reserve;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * FROM t_product WHERE reserve_id = ?1", nativeQuery = true)
    List<Product> findByReserve(Long reserve_id);
}


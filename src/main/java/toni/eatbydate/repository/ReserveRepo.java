package toni.eatbydate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import toni.eatbydate.entity.Product;
import toni.eatbydate.entity.Reserve;

import java.util.List;

@Repository
public interface ReserveRepo extends JpaRepository<Reserve, Long> {
    @Query(value = "SELECT * FROM t_reserve WHERE user_id = ?1", nativeQuery = true)
    List<Reserve> findByUserId(Long userId);
}


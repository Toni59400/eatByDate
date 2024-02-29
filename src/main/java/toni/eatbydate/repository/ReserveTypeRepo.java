package toni.eatbydate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toni.eatbydate.entity.ReserveType;

@Repository
public interface ReserveTypeRepo extends JpaRepository<ReserveType, Long> {
}

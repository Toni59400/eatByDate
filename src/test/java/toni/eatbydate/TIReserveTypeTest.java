package toni.eatbydate;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import toni.eatbydate.entity.ReserveType;
import toni.eatbydate.repository.ReserveTypeRepo;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
@Rollback
@RunWith(SpringRunner.class)
public class TIReserveTypeTest {

    @Autowired
    private ReserveTypeRepo reserveTypeRepository;

    @Test
    public void testCreateReserveType() {
        ReserveType reserveType = new ReserveType();
        reserveType.setName("testType");

        ReserveType savedReserveType = reserveTypeRepository.save(reserveType);

        assertThat(savedReserveType).isNotNull();
        assertThat(savedReserveType.getId()).isNotNull();
        assertThat(savedReserveType.getName()).isEqualTo("testType");
    }

    @Test
    public void testFindReserveTypeById() {
        ReserveType reserveType = new ReserveType();
        reserveType.setName("testType");

        ReserveType savedReserveType = reserveTypeRepository.save(reserveType);
        Optional<ReserveType> foundReserveType = reserveTypeRepository.findById(savedReserveType.getId());

        assertThat(foundReserveType).isPresent();
        assertThat(foundReserveType.get().getName()).isEqualTo("testType");
    }

    @Test
    public void testUpdateReserveType() {
        ReserveType reserveType = new ReserveType();
        reserveType.setName("testType");

        ReserveType savedReserveType = reserveTypeRepository.save(reserveType);
        savedReserveType.setName("updatedType");
        ReserveType updatedReserveType = reserveTypeRepository.save(savedReserveType);

        assertThat(updatedReserveType.getName()).isEqualTo("updatedType");
    }

    @Test
    public void testDeleteReserveType() {
        ReserveType reserveType = new ReserveType();
        reserveType.setName("testType");

        ReserveType savedReserveType = reserveTypeRepository.save(reserveType);
        reserveTypeRepository.deleteById(savedReserveType.getId());
        Optional<ReserveType> foundReserveType = reserveTypeRepository.findById(savedReserveType.getId());

        assertThat(foundReserveType).isNotPresent();
    }
}

package toni.eatbydate;

import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import toni.eatbydate.entity.Product;
import toni.eatbydate.entity.Reserve;
import toni.eatbydate.entity.ReserveType;
import toni.eatbydate.entity.User;
import toni.eatbydate.repository.ProductRepo;
import toni.eatbydate.repository.ReserveRepo;
import toni.eatbydate.repository.ReserveTypeRepo;
import toni.eatbydate.repository.UserRepo;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback
@RunWith(SpringRunner.class)
public class TIReserveTest {

    @Autowired
    private ReserveRepo reserveRepository;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private ReserveTypeRepo reserveTypeRepository;

    @Test
    public void testCreateReserve() {
        User user = new User("testuser", "password123", "testuser@example.com");
        userRepository.save(user);

        ReserveType reserveType = new ReserveType();
        reserveType.setName("testType");
        reserveTypeRepository.save(reserveType);

        Reserve reserve = new Reserve();
        reserve.setReserveNom("testReserve");
        reserve.setReserveType(reserveType);
        reserve.setUser(user);

        Reserve savedReserve = reserveRepository.save(reserve);

        assertThat(savedReserve).isNotNull();
        assertThat(savedReserve.getId()).isNotNull();
    }

    @Test
    public void testFindReserveById() {
        User user = new User("testuser", "password123", "testuser@example.com");
        userRepository.save(user);

        ReserveType reserveType = new ReserveType();
        reserveType.setName("testType");
        reserveTypeRepository.save(reserveType);

        Reserve reserve = new Reserve();
        reserve.setReserveNom("testReserve");
        reserve.setReserveType(reserveType);
        reserve.setUser(user);

        Reserve savedReserve = reserveRepository.save(reserve);
        Optional<Reserve> foundReserve = reserveRepository.findById(savedReserve.getId());

        assertThat(foundReserve).isPresent();
        assertThat(foundReserve.get().getReserveNom()).isEqualTo("testReserve");
    }

    @Test
    public void testUpdateReserve() {
        User user = new User("testuser", "password123", "testuser@example.com");
        userRepository.save(user);

        ReserveType reserveType = new ReserveType();
        reserveType.setName("testType");
        reserveTypeRepository.save(reserveType);

        Reserve reserve = new Reserve();
        reserve.setReserveNom("testReserve");
        reserve.setReserveType(reserveType);
        reserve.setUser(user);

        Reserve savedReserve = reserveRepository.save(reserve);
        savedReserve.setReserveNom("updatedReserve");
        Reserve updatedReserve = reserveRepository.save(savedReserve);

        assertThat(updatedReserve.getReserveNom()).isEqualTo("updatedReserve");
    }

    @Test
    public void testDeleteReserve() {
        User user = new User("testuser", "password123", "testuser@example.com");
        userRepository.save(user);

        ReserveType reserveType = new ReserveType();
        reserveType.setName("testType");
        reserveTypeRepository.save(reserveType);

        Reserve reserve = new Reserve();
        reserve.setReserveNom("testReserve");
        reserve.setReserveType(reserveType);
        reserve.setUser(user);

        Reserve savedReserve = reserveRepository.save(reserve);
        reserveRepository.deleteById(savedReserve.getId());
        Optional<Reserve> foundReserve = reserveRepository.findById(savedReserve.getId());

        assertThat(foundReserve).isNotPresent();
    }
}

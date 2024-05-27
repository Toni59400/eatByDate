package toni.eatbydate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import toni.eatbydate.entity.Reserve;
import toni.eatbydate.repository.ReserveRepo;
import toni.eatbydate.service.impl.ReserveServiceImpl;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
class ReserveServiceImplTest {

    @Mock
    private ReserveRepo reserveRepository;

    @InjectMocks
    private ReserveServiceImpl reserveService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllReserves() {
        Reserve reserve1 = new Reserve();
        Reserve reserve2 = new Reserve();
        when(reserveRepository.findAll()).thenReturn(Arrays.asList(reserve1, reserve2));

        List<Reserve> reserves = reserveService.getAllReserves();

        assertEquals(2, reserves.size());
        verify(reserveRepository, times(1)).findAll();
    }

    @Test
    void testGetReservesByUserId() {
        Long userId = 1L;
        Reserve reserve1 = new Reserve();
        Reserve reserve2 = new Reserve();
        when(reserveRepository.findByUserId(userId)).thenReturn(Arrays.asList(reserve1, reserve2));

        List<Reserve> reserves = reserveService.getReservesByUserId(userId);

        assertEquals(2, reserves.size());
        verify(reserveRepository, times(1)).findByUserId(userId);
    }

    @Test
    void testGetReserve() {
        Long reserveId = 1L;
        Reserve reserve = new Reserve();
        when(reserveRepository.findById(reserveId)).thenReturn(Optional.of(reserve));

        Reserve foundReserve = reserveService.getReserve(reserveId);

        assertEquals(reserve, foundReserve);
        verify(reserveRepository, times(1)).findById(reserveId);
    }

    @Test
    void testGetReserveNotFound() {
        Long reserveId = 1L;
        when(reserveRepository.findById(reserveId)).thenReturn(Optional.empty());

        Reserve foundReserve = reserveService.getReserve(reserveId);

        assertNull(foundReserve);
        verify(reserveRepository, times(1)).findById(reserveId);
    }

    @Test
    void testSaveReserve() {
        Reserve reserve = new Reserve();
        when(reserveRepository.save(any(Reserve.class))).thenReturn(reserve);

        Reserve savedReserve = reserveService.saveReserve(reserve);

        assertEquals(reserve, savedReserve);
        verify(reserveRepository, times(1)).save(reserve);
    }
}

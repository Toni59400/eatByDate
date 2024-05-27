package toni.eatbydate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import toni.eatbydate.entity.ReserveType;
import toni.eatbydate.repository.ReserveTypeRepo;
import toni.eatbydate.service.impl.ReserveTypeServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ReserveTypeServiceImplTest {

    @Mock
    private ReserveTypeRepo reserveTypeRepository;

    @InjectMocks
    private ReserveTypeServiceImpl reserveTypeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllReserveTypes() {
        ReserveType reserveType1 = new ReserveType();
        ReserveType reserveType2 = new ReserveType();
        when(reserveTypeRepository.findAll()).thenReturn(Arrays.asList(reserveType1, reserveType2));

        List<ReserveType> reserveTypes = reserveTypeService.getAllReserveTypes();

        assertEquals(2, reserveTypes.size());
        verify(reserveTypeRepository, times(1)).findAll();
    }
}

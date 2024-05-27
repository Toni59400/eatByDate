package toni.eatbydate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import toni.eatbydate.service.impl.IAppInfoService;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IAppInfoServiceTest {

    @InjectMocks
    private IAppInfoService appInfoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(appInfoService, "applicationName", "TestApp");
    }

    @Test
    void testGetAppInfo() {
        String expectedAppInfo = "TestApp - 1.0.0";
        String actualAppInfo = appInfoService.getAppInfo();

        assertEquals(expectedAppInfo, actualAppInfo);
    }
}
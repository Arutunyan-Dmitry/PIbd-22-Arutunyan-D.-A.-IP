package ru.ulstu.is.sbapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ulstu.is.sbapp.speaker.service.MatrixService;

@SpringBootTest
class SbappApplicationTests {
    @Autowired
    MatrixService matrixService;

    @Test
    void testMatrixInt() {
        final Object res = matrixService.mtrx( "int",5, 5);
        Integer[][] ts = new Integer[5][5];
        Assertions.assertEquals(ts.getClass(), res.getClass());
    }

    @Test
    void testMatrixDouble() {
        final Object res = matrixService.mtrx("double", 5,5);
        Double[][] ts = new Double[5][5];
        Assertions.assertEquals(ts.getClass(), res.getClass());
    }

    @Test
    void testMatrixFloat() {
        final Object res = matrixService.mtrx("float", 5,5);
        Float[][] ts = new Float[5][5];
        Assertions.assertEquals(ts.getClass(), res.getClass());
    }

    @Test
    void testMatrixErrorWired() {
        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> matrixService.mtrx("п",5, 5));
    }
}

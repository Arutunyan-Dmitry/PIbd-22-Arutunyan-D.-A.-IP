package ru.ulstu.is.sbapp.speaker.service;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.ulstu.is.sbapp.speaker.domain.Matrix;

@Service
public class MatrixService {
    private final ApplicationContext applicationContext;

    public MatrixService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public Object mtrx(String datatype, int n, int m) {
        final Matrix matrix = (Matrix) applicationContext.getBean(datatype);
        return matrix.matr(n, m);
    }
}

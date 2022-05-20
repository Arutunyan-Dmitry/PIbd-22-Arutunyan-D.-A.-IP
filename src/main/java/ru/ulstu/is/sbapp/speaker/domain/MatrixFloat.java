package ru.ulstu.is.sbapp.speaker.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.Random;

public class MatrixFloat implements Matrix<Float> {
    private final Logger log = LoggerFactory.getLogger(MatrixFloat.class);

    @Override
    public Float[][] matr(int n, int m) {
        Float[][] matritsa = new Float[n][m];
        Random r = new Random();

        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                matritsa[i][j] = r.nextFloat();
            }
        }
        return matritsa;
    }
}

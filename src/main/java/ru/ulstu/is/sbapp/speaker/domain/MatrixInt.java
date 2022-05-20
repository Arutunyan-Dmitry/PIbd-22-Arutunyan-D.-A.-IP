package ru.ulstu.is.sbapp.speaker.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class MatrixInt implements Matrix<Integer> {
    private final Logger log = LoggerFactory.getLogger(MatrixInt.class);

    @Override
    public Integer[][] matr(int n, int m) {
        Integer[][] matritsa = new Integer[n][m];
        Random r = new Random();

        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                matritsa[i][j] = r.nextInt();
            }
        }
        return matritsa;
    }
}

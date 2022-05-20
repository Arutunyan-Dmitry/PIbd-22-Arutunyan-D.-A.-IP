package ru.ulstu.is.sbapp.speaker.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Random;

@Component(value = "double")
public class MatrixDouble implements Matrix<Double> {
    private final Logger log = LoggerFactory.getLogger(MatrixDouble.class);

   @Override
   public Double[][] matr(int n, int m) {
       Double[][] matritsa = new Double[n][m];
       String rez = "";
       Random r = new Random();

       for (int i = 0; i < n; i++){
           for (int j = 0; j < m; j++){
               matritsa[i][j] = r.nextDouble();
           }
       }
       return matritsa;
   }

    @PostConstruct
    public void init() {
        log.info("SpeakerDeu.init()");
    }

    @PreDestroy
    public void destroy() {
        log.info("SpeakerDeu.destroy()");
    }
}

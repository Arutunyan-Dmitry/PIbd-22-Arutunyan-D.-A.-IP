package ru.ulstu.is.sbapp.speaker.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.ulstu.is.sbapp.speaker.domain.Matrix;
import ru.ulstu.is.sbapp.speaker.domain.MatrixFloat;
import ru.ulstu.is.sbapp.speaker.domain.MatrixInt;

@Configuration
public class MatrixConfiguration {
    private final Logger log = LoggerFactory.getLogger(MatrixConfiguration.class);

    @Bean(value = "int")
    public MatrixInt createIntMatrix() {
        log.info("Call createIntMatrix()");
        return new MatrixInt();
    }

    @Bean(value = "float")
    public MatrixFloat createFloatMatrix() {
        log.info("Call createFloatMatrix()");
        return new MatrixFloat();
    }
}

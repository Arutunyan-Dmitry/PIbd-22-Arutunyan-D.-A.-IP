package ru.ulstu.is.sbapp.speaker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ulstu.is.sbapp.speaker.service.MatrixService;

@RestController
public class MatrixController {
    private final MatrixService matrixService;

    public MatrixController(MatrixService matrixService) {
        this.matrixService = matrixService;
    }

    @GetMapping("/")
    public Object matrix(@RequestParam(value = "Data_type", defaultValue = "int") String Data_type,
                         @RequestParam(value = "n", defaultValue = "2") int n,
                         @RequestParam(value = "m", defaultValue = "3") int m) {
        return matrixService.mtrx(Data_type, n, m);
    }
}

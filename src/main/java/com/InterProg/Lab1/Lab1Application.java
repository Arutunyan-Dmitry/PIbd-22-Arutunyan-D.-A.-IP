package com.InterProg.Lab1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@SpringBootApplication
@RestController
public class Lab1Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab1Application.class, args);
	}

	@GetMapping("/hello")
	public String Hello(@RequestParam(value="name", defaultValue="World") String name) {
		return String.format("Hello %s!", name);
	}

	@GetMapping("matrix")
	public int[][] mtrx(@RequestParam(value="n", defaultValue = "3") int n,
					   @RequestParam(value="m", defaultValue = "2") int m) {
		int[][] matr = new int[n][m];
		Random r = new Random();
		for (int i = 0; i < n; i++){
			for (int j = 0; j < m; j++){
				matr[i][j] = r.nextInt();
			}
		}
		return matr;
	}
}


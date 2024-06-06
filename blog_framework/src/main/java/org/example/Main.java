package org.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("org.example.mapper")
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}
package com.mstone.reffy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ReffyApplication {
  public static void main(String[] args) {
    SpringApplication.run(ReffyApplication.class, args);
  }
}

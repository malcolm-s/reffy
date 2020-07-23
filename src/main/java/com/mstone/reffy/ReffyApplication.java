package com.mstone.reffy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class ReffyApplication {
  public static void main(String[] args) {
    SpringApplication.run(ReffyApplication.class, args);
  }

  @Bean
  SpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
    var templateEngine = new SpringTemplateEngine();
    templateEngine.addTemplateResolver(templateResolver);
    templateEngine.addDialect(new LayoutDialect());
    return templateEngine;
  }
}
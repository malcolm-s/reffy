package com.mstone.reffy.migrations;

import com.mstone.reffy.migrations.upgrades.AddReferendumStateMigration;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MigrationConfig {

  @Bean
  @Qualifier("migrations")
  public List<Migration> migrations(ApplicationContext context) {
    return Arrays.asList(context.getBean(AddReferendumStateMigration.class));
  }
}

package com.mstone.reffy.migrations;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MigrationRunner implements ApplicationRunner {
  private final List<Migration> migrations;
  private final DbMigrationRepository dbMigrations;

  public MigrationRunner(@Qualifier("migrations") List<Migration> migrations, DbMigrationRepository dbMigrations) {
    this.migrations = migrations;
    this.dbMigrations = dbMigrations;
  }

  @EventListener
  public void onApplicationReady(ApplicationReadyEvent e) {
    log.info("application ready");
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    log.info("migration runner args: {}", args.getOptionNames());
    var existing = existingMigrations();
    log.info("migration counts: existing={} total={}", existing.size(), migrations.size());
    
    for (var migration : migrations) {
      if (!existing.contains(migration.getName())) {
        log.info("running migration: {}", migration.getName());
        runMigration(migration);
      }
    }
  }

  private void runMigration(Migration migration) {
    migration.run();
    var dbMigration = new DbMigration();
    dbMigration.setName(migration.getName());
    dbMigrations.save(dbMigration);
  }

  private Set<String> existingMigrations() {
    return dbMigrations.findAll().stream().map(m -> m.getName()).collect(Collectors.toSet());
  }
}
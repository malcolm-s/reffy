package com.mstone.reffy.migrations;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DbMigrationRepository extends JpaRepository<DbMigration, Integer> {}

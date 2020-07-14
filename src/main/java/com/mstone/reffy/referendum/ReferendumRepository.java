package com.mstone.reffy.referendum;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReferendumRepository extends JpaRepository<Referendum, Integer> {
}
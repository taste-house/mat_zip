package com.matzip.matzipback.test.command.repositories;

import com.matzip.matzipback.test.command.entities.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {
}

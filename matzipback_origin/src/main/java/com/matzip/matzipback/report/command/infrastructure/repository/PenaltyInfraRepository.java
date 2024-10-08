package com.matzip.matzipback.report.command.infrastructure.repository;

import com.matzip.matzipback.report.command.domain.aggregate.Penalty;
import com.matzip.matzipback.report.command.domain.repository.PenaltyRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PenaltyInfraRepository extends PenaltyRepository, JpaRepository<Penalty, Long> {
}

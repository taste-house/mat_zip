package com.matzip.matzipuser.report.command.infrastructure.repository;

import com.matzip.matzipuser.report.command.domain.aggregate.Penalty;
import com.matzip.matzipuser.report.command.domain.repository.PenaltyRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PenaltyInfraRepository extends PenaltyRepository, JpaRepository<Penalty, Long> {
}

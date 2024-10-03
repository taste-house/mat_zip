package com.matzip.matzipback.report.command.domain.repository;

import com.matzip.matzipback.report.command.domain.aggregate.Penalty;

import java.util.Optional;

public interface PenaltyRepository {
    Penalty save(Penalty penalty);

    Optional<Penalty> findById(Long penaltySeq);

    void deleteById(Long penaltySeq);
}

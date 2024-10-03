package com.matzip.matzipback.report.command.domain.repository;

import com.matzip.matzipback.report.command.domain.aggregate.ReportView;

import java.util.List;


public interface ReportViewRepository {
    List<ReportView> findByCategoryAndSeq(String category, Long seq);
}

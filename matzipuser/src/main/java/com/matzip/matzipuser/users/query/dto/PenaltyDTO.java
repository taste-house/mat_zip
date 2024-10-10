package com.matzip.matzipuser.users.query.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PenaltyDTO {

    private long penaltySeq;
    private long userSeq;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String penaltyType;
    private String penaltyReasonContent;

}

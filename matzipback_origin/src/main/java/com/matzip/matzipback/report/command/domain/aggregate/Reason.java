package com.matzip.matzipback.report.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reasons")
@NoArgsConstructor/*(access = AccessLevel.PROTECTED)*/
public class Reason {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  reasonSeq;

    private String reasonName;

}

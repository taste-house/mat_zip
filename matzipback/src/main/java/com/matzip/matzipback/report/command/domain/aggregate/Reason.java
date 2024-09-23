package com.matzip.matzipback.report.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reason")
@NoArgsConstructor/*(access = AccessLevel.PROTECTED)*/
public class Reason {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reasonCode;

    private String reasonName;

}

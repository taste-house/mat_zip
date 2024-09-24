package com.matzip.matzipback.users.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "active_level")
@NoArgsConstructor/*(access = AccessLevel.PROTECTED)*/
public class ActiveLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long activeLevelSeq;
    private String activeLevelName;
    private int activeLevelStandard;

}

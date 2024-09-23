package com.matzip.matzipback.users.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "message")
@NoArgsConstructor/*(access = AccessLevel.PROTECTED)*/
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long messageSeq;

}

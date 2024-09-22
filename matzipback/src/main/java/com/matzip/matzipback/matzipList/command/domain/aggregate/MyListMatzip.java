package com.matzip.matzipback.matzipList.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "list_matzip")
@NoArgsConstructor
public class MyListMatzip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int listMatzipSeq;
    private int listSeq;
    private int restaurantSeq;
    private String listMatzipComment;

}

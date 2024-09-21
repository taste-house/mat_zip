package com.matzip.matzipback.board.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "board_category")
@NoArgsConstructor
public class BoardCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boardCategorySeq;
    private String boardCategoryName;

}

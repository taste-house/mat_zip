package com.matzip.matzipback.test.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "favorite_board")
@NoArgsConstructor
public class FavoriteBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int favoriteBoardSeq;
    private int userSeq;
    private int boardCategorySeq;

}

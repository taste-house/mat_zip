package com.matzip.matzipuser.board.query.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ListDTO {
    private Long listSeq;
    private String listTitle;
    private String listContent;
}

package com.matzip.matzipback.board.query.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostTagDTO {
    private Long postTagSeq;
    private String tagName;
}

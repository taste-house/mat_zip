package com.matzip.matzipback.board.query.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class PopularTagResponse {

    private List<String> tags;

}

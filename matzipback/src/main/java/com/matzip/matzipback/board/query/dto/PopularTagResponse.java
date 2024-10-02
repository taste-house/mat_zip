package com.matzip.matzipback.board.query.dto;

import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@Builder
public class PopularTagResponse {

    private List<String> tags;

}

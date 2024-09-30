package com.matzip.matzipback.matzipList.command.application.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UpdateListRequest {
    private Long listSeq;
    private Long listUserSeq;
    private String listTitle;
    private String listContent;
    private Integer listLevel;

}

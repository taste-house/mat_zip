package com.matzip.matzipback.matzipList.query.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ListContentDTO {

    private String listTitle;
    private String listContent;
    private LocalDateTime listCreatedTime;
    private LocalDateTime listUpdatedTime;
    private List<MatzipDTO> restaurants;


}

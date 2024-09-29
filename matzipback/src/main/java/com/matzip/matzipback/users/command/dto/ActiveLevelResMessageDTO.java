package com.matzip.matzipback.users.command.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ActiveLevelResMessageDTO {

    private int code;
    private String message;
    private List<ActiveLevelResDTO> activeLevels;
}

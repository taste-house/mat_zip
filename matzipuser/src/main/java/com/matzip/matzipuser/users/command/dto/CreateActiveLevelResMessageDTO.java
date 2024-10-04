package com.matzip.matzipuser.users.command.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CreateActiveLevelResMessageDTO {

    private int code;
    private String message;
    private List<ActiveLevelResDTO> activeLevels;
}

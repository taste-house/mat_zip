package com.matzip.matzipback.users.command.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateActiveLevelReqDTO {
    
    private String activeLevelName;
    private int activeLevelStandard;
}

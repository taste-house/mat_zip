package com.matzip.matzipuser.users.command.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateActiveLevelRequestDTO {
    
    private String activeLevelName;
    private int activeLevelStandard;
}

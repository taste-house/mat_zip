package com.matzip.matzipback.matzipList.command.application.controller;

import com.matzip.matzipback.matzipList.command.application.dto.ListBoxUpdateRequest;
import com.matzip.matzipback.matzipList.command.application.service.ListBoxCommandService;
import com.matzip.matzipback.matzipList.command.domain.aggregate.MyList;
import com.matzip.matzipback.responsemessage.SuccessCode;
import com.matzip.matzipback.responsemessage.SuccessResMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ListBoxCommandController {

    private final ListBoxCommandService listBoxCommandService;

    @PutMapping("/listbox")
    public ResponseEntity<SuccessResMessage> updateListBoxLevel(@Valid @RequestBody ListBoxUpdateRequest listBoxUpdateRequest ){

        listBoxCommandService.updateListBoxLevel(listBoxUpdateRequest);

        return ResponseEntity.ok(new SuccessResMessage(SuccessCode.BASIC_UPDATE_SUCCESS));
    }
}

package com.matzip.matzipback.matzipList.command.application.controller;

import com.matzip.matzipback.matzipList.command.application.dto.ListBoxUpdateRequest;
import com.matzip.matzipback.matzipList.command.application.service.ListBoxCommandService;
import com.matzip.matzipback.matzipList.command.domain.aggregate.MyList;
import com.matzip.matzipback.responsemessage.SuccessCode;
import com.matzip.matzipback.responsemessage.SuccessResMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/back/api/vi")
@Tag(name = "List", description = "리스트")
public class ListBoxCommandController {

    private final ListBoxCommandService listBoxCommandService;

    @PutMapping("/listbox")
    @Operation(summary = "리스트 서랍 수정", description = "리스트 서랍 내 리스트의 우선순위를 수정한다.")
    public ResponseEntity<SuccessResMessage> updateListBoxLevel(@Valid @RequestBody ListBoxUpdateRequest listBoxUpdateRequest ){

        listBoxCommandService.updateListBoxLevel(listBoxUpdateRequest);

        return ResponseEntity.ok(new SuccessResMessage(SuccessCode.BASIC_UPDATE_SUCCESS));
    }
}

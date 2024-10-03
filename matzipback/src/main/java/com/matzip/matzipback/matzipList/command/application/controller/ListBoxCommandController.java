package com.matzip.matzipback.matzipList.command.application.controller;

import com.matzip.matzipback.common.util.CustomUserUtils;
import com.matzip.matzipback.exception.RestApiException;
import com.matzip.matzipback.like.query.dto.LikedPostDTO;
import com.matzip.matzipback.matzipList.command.application.dto.ListBoxUpdateRequest;
import com.matzip.matzipback.matzipList.command.application.service.ListBoxCommandService;
import com.matzip.matzipback.matzipList.command.domain.aggregate.MyList;
import com.matzip.matzipback.responsemessage.SuccessCode;
import com.matzip.matzipback.responsemessage.SuccessResMessage;
import com.matzip.matzipback.responsemessage.SuccessSearchResMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.matzip.matzipback.exception.ErrorCode.FORBIDDEN_ACCESS;
import static com.matzip.matzipback.exception.ErrorCode.UNAUTHORIZED_REQUEST;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Tag(name = "List", description = "리스트")
public class ListBoxCommandController {

    private final ListBoxCommandService listBoxCommandService;

    @PutMapping("/listbox")
    @Operation(summary = "리스트 서랍 수정", description = "리스트 서랍 내 리스트의 우선순위를 수정한다.")
    public ResponseEntity<SuccessResMessage> updateListBoxLevel(@Valid @RequestBody ListBoxUpdateRequest listBoxUpdateRequest ){

        try { if (CustomUserUtils.getCurrentUserAuthorities().iterator().next().getAuthority().equals("user")) {

            listBoxCommandService.updateListBoxLevel(listBoxUpdateRequest);

            return ResponseEntity.ok(new SuccessResMessage(SuccessCode.BASIC_UPDATE_SUCCESS));
        } else { throw new RestApiException(FORBIDDEN_ACCESS); }

        } catch (NullPointerException e) { throw new RestApiException(UNAUTHORIZED_REQUEST); }
    }
}

package com.matzip.matzipback.matzipList.query.controller;

import com.matzip.matzipback.matzipList.query.dto.MatzipSearchDTO;
import com.matzip.matzipback.matzipList.query.service.MatzipQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/back/api/vi")
@Tag(name = "List", description = "리스트")
public class MatzipQueryController {

    private final MatzipQueryService matzipQueryService;

    @GetMapping("list/{listSeq}/matzip")
    @Operation(summary = "리스트 맛집 조회", description = "리스트에 등록된 맛집을 조회한다.")
    public ResponseEntity<List<MatzipSearchDTO>> getMatzip(
            @PathVariable Long listSeq,
            @RequestParam(value = "matzipTitle", required = false) String matzipTitle){

            List<MatzipSearchDTO> matzip = matzipQueryService.searchMatzip(listSeq, matzipTitle);
            return ResponseEntity.ok(matzip);
    }
}

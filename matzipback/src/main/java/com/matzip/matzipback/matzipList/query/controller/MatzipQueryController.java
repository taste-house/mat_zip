package com.matzip.matzipback.matzipList.query.controller;

import com.matzip.matzipback.matzipList.query.dto.MatzipSearchDTO;
import com.matzip.matzipback.matzipList.query.service.MatzipQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MatzipQueryController {

    private final MatzipQueryService matzipQueryService;

    @GetMapping("list/{listSeq}/restaurants")
    public ResponseEntity<List<MatzipSearchDTO>> getMatzip(
            @PathVariable Long listSeq,
            @RequestParam(value = "matzipTitle", required = false) String matzipTitle){

            List<MatzipSearchDTO> matzip = matzipQueryService.searchMatzip(listSeq, matzipTitle);
            return ResponseEntity.ok(matzip);
    }
}

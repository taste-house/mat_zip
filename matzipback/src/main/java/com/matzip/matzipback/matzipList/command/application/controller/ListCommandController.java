package com.matzip.matzipback.matzipList.command.application.controller;


import com.matzip.matzipback.matzipList.command.application.dto.CreateListRequest;
import com.matzip.matzipback.matzipList.command.application.service.ListCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ListCommandController {

    private final ListCommandService listCommandService;

    //리스트 등록
    @PostMapping("/list")
    public ResponseEntity<Void> createList(@RequestBody CreateListRequest listRequest){

        Long listSeq = listCommandService.createList(listRequest);

        return ResponseEntity.created(URI.create("/api/v1/list" + listSeq)).build();
    }

    @DeleteMapping("/list/{listSeq}")
    public ResponseEntity<Void> deleteList(@PathVariable Long listSeq){
        listCommandService.deleteList(listSeq);
        return ResponseEntity.noContent().build();

    }

}

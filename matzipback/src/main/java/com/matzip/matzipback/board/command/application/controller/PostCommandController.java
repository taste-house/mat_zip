package com.matzip.matzipback.board.command.application.controller;

import com.matzip.matzipback.board.command.application.service.PostCommandService;
import com.matzip.matzipback.board.command.dto.PostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PostCommandController {

    private final PostCommandService postCommandService;

//    @PostMapping("/post/report")
//    public ResponseEntity<PostDTO> savePostReport(@RequestBody PostDTO postDTO) {
//        boolean result = postCommandService.savePostReport(postDTO);
//
//        if (result) {
//            return ResponseEntity.ok().body(postDTO);
//        }
//        return ResponseEntity.badRequest().build();
//    }


}

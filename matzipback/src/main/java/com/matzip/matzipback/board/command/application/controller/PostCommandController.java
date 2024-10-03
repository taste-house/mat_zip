package com.matzip.matzipback.board.command.application.controller;

import com.matzip.matzipback.board.command.application.dto.PostAndTagRequestDTO;
import com.matzip.matzipback.board.command.application.service.PostCommandService;
import com.matzip.matzipback.responsemessage.SuccessResMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
//import com.google.gson.JsonObject;
//import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static com.matzip.matzipback.responsemessage.SuccessCode.BASIC_DELETE_SUCCESS;
import static com.matzip.matzipback.responsemessage.SuccessCode.BASIC_UPDATE_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Tag(name = "Post", description = "게시글")
public class PostCommandController {

    private final PostCommandService postCommandService;

    /* 1. 게시글 등록, 이미지 업로드, 이미지 삭제 */
    // 게시글 기본 정보 + 태그 등록
    @PostMapping("/posts")
    @Operation(summary = "게시글 등록", description = "게시글을 등록한다.")
    public ResponseEntity<Void> registPost(
            @Valid @RequestBody PostAndTagRequestDTO newPost    // 게시글 정보 + 태그 정보
    ){

        // 게시글 등록
        Long postSeq = postCommandService.createPost(newPost);

        return ResponseEntity.status(HttpStatus.CREATED)
                .location(URI.create("/api/v1/post/" + postSeq))    // 리소스가 생성된 위치
                .build();

    }

    /*
    // 이미지가 업로드 될 때 마다 작동할 메소드
    // 이미지 파일을 업로드하고, 성공적으로 저장된 경우 이미지의 URL과 응답 코드를 JSON 형식으로 반환
    @PostMapping(value ="/post/uploadImage", produces = "application/json")
    @ResponseBody
    public JsonObject uploadPostImageFile(@RequestParam("file") MultipartFile multipartFile) {

        JsonObject jsonObject = new JsonObject();

        String fileRoot = "C:\\summernote_image\\";	//저장될 외부 파일 경로

        // 디렉토리 존재 여부 체크
        File directory = new File(fileRoot);
        if (!directory.exists()) {
            directory.mkdirs(); // 디렉토리 생성
        }

        String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자

        String savedFileName = UUID.randomUUID() + extension;	//저장될 파일명

        File targetFile = new File(fileRoot + savedFileName);

        try {
            InputStream fileStream = multipartFile.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
            jsonObject.addProperty("url", "/summernoteImage/"+savedFileName);
            jsonObject.addProperty("responseCode", "success");

        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
            jsonObject.addProperty("responseCode", "error");
            e.printStackTrace();
        }

        return jsonObject;
    }
    */

    /* 2. 게시글 수정 */
    @PutMapping("/posts/{postSeq}")
    @Operation(summary = "게시글 수정", description = "게시글을 수정한다.")
    public ResponseEntity<SuccessResMessage> updatePost(
            @PathVariable Long postSeq,
            @Valid @RequestBody PostAndTagRequestDTO updatedPost
    ) {

        // 게시글 수정
        postCommandService.updatePost(postSeq, updatedPost);

        return ResponseEntity.ok(new SuccessResMessage(BASIC_UPDATE_SUCCESS));
    }

    /* 3. 게시글 삭제 */
    @DeleteMapping("/posts/{postSeq}")
    @Operation(summary = "게시글 삭제", description = "게시글을 삭제한다.")
    public ResponseEntity<SuccessResMessage> deletePost(@PathVariable Long postSeq) {

        // 게시글 삭제
        postCommandService.deletePost(postSeq);

        return ResponseEntity.ok(new SuccessResMessage(BASIC_DELETE_SUCCESS));
    }

}

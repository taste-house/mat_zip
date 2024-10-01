package com.matzip.matzipback.board.command.application.controller;

import com.matzip.matzipback.board.command.application.dto.ReqPostCmtCreateDTO;
import com.matzip.matzipback.board.command.application.dto.ReqPostCmtUpdateDTO;
import com.matzip.matzipback.board.command.application.dto.ResPostCmtDTO;
import com.matzip.matzipback.board.command.application.service.PostCommentService;
import com.matzip.matzipback.responsemessage.SuccessResMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.matzip.matzipback.responsemessage.SuccessCode.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PostCmtCommandController {

    private final PostCommentService postCommentService;

    // 댓글 등록
    @PostMapping("/postcomment")
    public ResponseEntity<Map<String, Object>> createPostComment(@Valid @RequestBody ReqPostCmtCreateDTO reqPostCmtCreateDTO) {
        ResPostCmtDTO resultPostCmtDTO = postCommentService.createPostComment(reqPostCmtCreateDTO);

        // 성공 메세지 생성
        SuccessResMessage successResMessage = new SuccessResMessage(BASIC_SAVE_SUCCESS);

        // 등록된 댓글 내용 중 클라이언트가 화면을 구성하기 위해 추가로 필요한 정보
        Map<String, Object> postCmtInfo = new HashMap<>();
        postCmtInfo.put("postCommentSeq", resultPostCmtDTO.getPostCommentSeq());
        postCmtInfo.put("postCommentCreatedTime", resultPostCmtDTO.getPostCommentCreatedTime());

        // 메세지와 정보 같이 담은 응답
        Map<String, Object> Response = new HashMap<>();
        Response.put("message", successResMessage);
        Response.put("response", postCmtInfo);

        return ResponseEntity.ok(Response);
    }

    // 댓글 수정
    @PutMapping("/postcomment")
    public ResponseEntity<SuccessResMessage> updatePostComment(
            @Valid @RequestBody ReqPostCmtUpdateDTO reqPostCmtUpdateDTO
    ) {
        postCommentService.updatePostComment(reqPostCmtUpdateDTO);
        return ResponseEntity.ok(new SuccessResMessage(BASIC_UPDATE_SUCCESS));
    }

    // 댓글 삭제
    @DeleteMapping("/postcomment/{postCommentSeq}")
    public ResponseEntity<SuccessResMessage> deletePostComment(@PathVariable Long postCommentSeq) {
        postCommentService.deletePostComment(postCommentSeq);
        return ResponseEntity.ok(new SuccessResMessage(BASIC_DELETE_SUCCESS));
    }

}

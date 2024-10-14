package com.matzip.matzipuser.users.query.controller;

import com.matzip.matzipuser.common.util.CustomUserUtils;
import com.matzip.matzipuser.responsemessage.SuccessCode;
import com.matzip.matzipuser.responsemessage.SuccessSearchResMessage;
import com.matzip.matzipuser.users.query.dto.userInfo.AllUserInfoResponseDTO;
import com.matzip.matzipuser.users.query.dto.userInfo.OtherUserInfoDto;
import com.matzip.matzipuser.users.query.dto.userInfo.UserDetailInfoDTO;
import com.matzip.matzipuser.users.query.dto.userInfo.UserTokenDTO;
import com.matzip.matzipuser.users.query.service.UsersInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/api/v1")
@Slf4j
@Tag(name = "Users", description = "회원관리")
public class UsersQueryController {

    private final UsersInfoService usersInfoService;

    // 로그인 검증 구현 시 되살릴 예정(프론트에서 직접 회원가입 창으로 이동가능하기때문)
//    @GetMapping("/auth/register")
//    public ResponseEntity<Map<String, String>> getSignUp() {
//        log.info("회원가입 화면으로 이동");
//        Map<String, String> response = new HashMap<>();
//        response.put("message", "회원가입 화면으로 이동");
//        response.put("url", "/register"); // 프론트엔드 회원가입 화면의 경로
//        return ResponseEntity.ok(response); // JSON 응답
//    }

//    @GetMapping("/auth/login")
//    public String getLogin(){
//
//        return "";
//    }

    // 1차 수정 완료 - 가람
    @GetMapping("/users/list")
    @Operation(summary = "회원 전체조회", description = "관리자가 회원을 전체 조회한다.")
    public ResponseEntity<SuccessSearchResMessage<?>> getAllUserList(@RequestParam(value = "socialYn", required = false) String socialYn,
//    public ResponseEntity<AllUserInfoResponseDTO> getAllUserList(@RequestParam(value = "socialYn", required = false) String socialYn,
                                                                  @RequestParam(value = "socialSite", required = false) String socialSite,
                                                                  @RequestParam(value = "businessVerifiedYn", required = false) String businessVerifiedYn,
                                                                  @RequestParam(value = "influencerYn", required = false) String influencerYn,
                                                                  @RequestParam(value = "userStatus", required = false) String userStatus,
                                                                  @RequestParam(value = "orderBy", defaultValue = "regDateDesc") String orderBy,
                                                                  @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                                  @RequestParam(value = "size", defaultValue = "10") Integer size) {
                                                                //defaultValue : 기본값 설정, required = false : 파라미터 선택적(필수아님)
        log.info("GET /back/api/v1/users/list - 전체 회원 정보 조회 요청");
        AllUserInfoResponseDTO users = usersInfoService.getAllUserList(socialYn, socialSite, businessVerifiedYn, influencerYn, userStatus, orderBy, page, size);
        log.info("전체 회원 정보 조회 완료. 현재 페이지: {}, 전체 페이지 수: {}, 총 유저 수: {}",
                users.getCurrentPage(), users.getTotalPages(), users.getTotalUsers());

//        return ResponseEntity.ok(users);    // 결과 DTO를 ResponseEntity에 반환
        return ResponseEntity.ok(new SuccessSearchResMessage<>(SuccessCode.BASIC_GET_SUCCESS, users));    // 결과 DTO를 ResponseEntity에 반환
    }

    // 1차 수정 완료 - 가람
    @GetMapping("/users/search")
    @Operation(summary = "회원 검색", description = "관리자 또는 회원이 회원을 검색한다.")
//    public ResponseEntity<AllUserInfoResponseDTO> getSearchUserList(
    public ResponseEntity<SuccessSearchResMessage<?>> getSearchUserList(
            @RequestParam(value = "searchType", required = false) String searchType,
            @RequestParam(value = "searchWord", required = false) String searchWord,
            @RequestParam(value = "socialYn", required = false) String socialYn,
            @RequestParam(value = "socialSite", required = false) String socialSite,
            @RequestParam(value = "businessVerifiedYn", required = false) String businessVerifiedYn,
            @RequestParam(value = "influencerYn", required = false) String influencerYn,
            @RequestParam(value = "userStatus", required = false) String userStatus,
            @RequestParam(value = "orderBy", defaultValue = "regDateDesc") String orderBy,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size
    ) {
        //defaultValue : 기본값 설정, required = false : 파라미터 선택적(필수아님)
        log.info("GET /back/api/v1/users/search - 회원 검색 조회 요청");
        
        String userAuth = CustomUserUtils.getCurrentUserAuthorities().iterator().next().getAuthority();
        AllUserInfoResponseDTO users;
        if(userAuth.equals("user")) {   // 일반회원
            users =  usersInfoService.getSearchUserList(searchType, searchWord, socialYn, socialSite, businessVerifiedYn, influencerYn, "actice", userAuth, orderBy, page, size);
        } else {    // 관리자
            users =  usersInfoService.getSearchUserList(searchType, searchWord, socialYn, socialSite, businessVerifiedYn, influencerYn, null, userAuth, orderBy, page, size);
        }

        log.info("회원 검색 조회 완료. 현재 페이지: {}, 전체 페이지 수: {}, 검색결과 유저 수: {}, 검색타입 : {}, 검색어 : {}",
                users.getCurrentPage(), users.getTotalPages(), users.getTotalUsers(), searchType, searchWord);

        return ResponseEntity.ok(new SuccessSearchResMessage<>(SuccessCode.BASIC_GET_SUCCESS, users));    // 결과 DTO를 ResponseEntity에 반환
//        return ResponseEntity.ok(users);    // 결과 DTO를 ResponseEntity에 반환
    }

    // 1차 수정 완료 - 가람
    @GetMapping("/users/list/{userSeq}")
    @Operation(summary = "회원 상세조회", description = "관리자 또는 회원이 회원정보를 상세조회한다.")
    public ResponseEntity<?> DetailUserInfo(@PathVariable Long userSeq
    ) {
        log.info("GET /back/api/v1/list/{userSeq} - 회원 상세 조회 요청 : {}", userSeq);
        String userAuth = CustomUserUtils.getCurrentUserAuthorities().iterator().next().getAuthority();
        Long currentUserSeq = CustomUserUtils.getCurrentUserSeq();
//        String userAuth = "admin";
//        Long currentUserSeq = 1L;

        if(userAuth.equals("admin")) {
            // 관리자
            UserDetailInfoDTO userInfo = usersInfoService.getDetailUserInfo(userSeq, userAuth);
            return ResponseEntity.ok(new SuccessSearchResMessage<>(SuccessCode.BASIC_GET_SUCCESS, userInfo));
        } else if(currentUserSeq.equals(userSeq)) {
            // 일반 회원이 자신의 정보를 조회하는 경우
            UserDetailInfoDTO userInfo = usersInfoService.getDetailUserInfo(userSeq, userAuth);
            return ResponseEntity.ok(new SuccessSearchResMessage<>(SuccessCode.BASIC_GET_SUCCESS, userInfo));
        } else {
            // 다른 회원 정보를 조회하는 경우
            OtherUserInfoDto otherUserInfo = usersInfoService.getOthersInfo(userSeq);
            return ResponseEntity.ok(new SuccessSearchResMessage<>(SuccessCode.BASIC_GET_SUCCESS, otherUserInfo));
        }

        // 권한이 없는 경우
//        return ResponseEntity.status(ErrorCode.FORBIDDEN_ACCESS.getHttpStatus())
//                .body(ErrorCode.FORBIDDEN_ACCESS.getMessage());
    }


    @GetMapping("/users/email")
    public ResponseEntity<SuccessSearchResMessage<?>> getUserByEmail(@RequestParam("email") String email) {

        UserTokenDTO userTokenDTO = usersInfoService.getUserByEmail(email);

        return ResponseEntity.ok(
                new SuccessSearchResMessage<>(SuccessCode.BASIC_GET_SUCCESS, userTokenDTO));
    }

    @GetMapping("/users/userseq")
    public ResponseEntity<SuccessSearchResMessage<?>> getUserByUserSeq(@RequestParam("userSeq") Long userSeq) {

        UserTokenDTO userTokenDTO = usersInfoService.getUserByUserSeq(userSeq);

        return ResponseEntity.ok(
                new SuccessSearchResMessage<>(SuccessCode.BASIC_GET_SUCCESS, userTokenDTO));
    }


}

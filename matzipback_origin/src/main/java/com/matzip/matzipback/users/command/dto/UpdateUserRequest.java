package com.matzip.matzipback.users.command.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateUserRequest {

    private long userSeq;
    private String userPassword;
//    @Pattern(regexp = "^[0-9]{10,11}$", message = "휴대폰 번호는 10자리 또는 11자리 숫자여야 합니다.")
    private String userPhone;
    @NotBlank(message = "닉네임은 필수 입력 항목입니다.")
    @Size(min = 2, max = 16, message = "닉네임은 2자 이상, 16자 이하로 입력해주세요.")
    private String userNickname;

}

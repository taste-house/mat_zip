package com.matzip.matzipback.users.command.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserRequest {

    @NotBlank(message = "이메일은 필수 입력사항입니다.")
    @Email(message = "유효한 이메일 주소를 입력해주세요.")
    private String userEmail;
    @NotBlank(message = "비밀번호는 필수 입력 사항입니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*.,?])[A-Za-z\\d!@#$%^&*.,?]{8,}$",
            message = "비밀번호는 영문, 숫자, 특수문자를 포함한 8자 이상이어야 합니다.")
    // 특수문자는 !@#$%^&*.,? 만 허용
    private String userPassword;
    @NotBlank(message = "이름은 필수 입력 사항입니다.")
    private String userName;
    @NotBlank(message = "휴대폰 번호는 필수 입력 사항입니다.")
    private String userPhone;

    private String userSocialYn = "N";  // 기본 회원가입
}

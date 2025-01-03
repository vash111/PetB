package com.petprice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    @NotBlank(message = "이메일은 필수 입력 항목입니다.")
    @Email(message = "유효하지 않은 이메일 형식입니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    @Size(min = 6, message = "비밀번호는 최소 6자 이상이어야 합니다.")
    private String password;

    @NotBlank(message = "이름은 필수 입력 항목입니다.")
    private String name;

    @NotBlank(message = "전화번호는 필수 입력 항목입니다.")
    @Pattern(regexp = "^\\d{10,15}$", message = "전화번호는 10자리에서 15자리의 숫자로 입력해야 합니다.")
    private String phoneNumber;

    @NotBlank(message = "원활한 서비스 이용을 위해 필수 입력 항목입니다.")
    private String sido; // 시도 정보

    @NotBlank(message = "원활한 서비스 이용을 위해 필수 입력 항목입니다.")
    private String sigungu; // 시군구 정보
}

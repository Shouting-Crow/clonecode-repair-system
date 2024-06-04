package com.clonecode.repairweb.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AdminRegisterForm {

    @NotEmpty(message = "사용자의 이름은 필수 입니다.")
    private String name;

    @NotEmpty(message = "아이디는 필수 입니다.")
    private String loginId;

    @NotEmpty(message = "패스워드를 입력해 주세요.")
    private String password;

    @NotEmpty(message = "전화번호를 입력해 주세요.")
    private String phoneNumber;

    @NotEmpty(message = "현재 살고있는 도시를 입력해주세요.")
    private String city;

    @NotEmpty(message = "도로명 주소를 입력해 주세요.")
    private String streetAddress;
}

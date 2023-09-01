package com.edu.iuh.fit.springboot.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Khoong luu thong ti bi' mat vao DTO vi Client thao tac voi DTP

@Schema(
        description = "USERDTO MODEL INFOMATION"
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

    @Schema(description = "user id")
    private Long id;
    @Schema(description = "user firstname")
    @NotEmpty
    private String firstname;
    @Schema(description = "user lastname")
    @NotEmpty
    private String lastname;
    @Schema(description = "user email")
    @Email
    @NotEmpty
    private String email;
}

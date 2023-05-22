package com.ead.userauth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PasswordUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1061003265774112379L;

    @NotBlank
    @Size(min = 6, max = 20)
    private String currentPassword;

    @NotBlank
    @Size(min = 6, max = 20)
    private String newPassword;

}

package com.ead.userauth.dto.request;

import com.ead.userauth.validation.DoNotContainWhitespaces;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserInsertDTO implements Serializable {

    private static final long serialVersionUID = -779048429057258253L;

    @NotBlank
    @DoNotContainWhitespaces
    @Size(min = 4, max = 50)
    private String username;

    @NotBlank
    @Email
    @Size(min = 4, max = 50)
    private String email;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;

    @Size(min = 3, max = 150)
    private String fullName;

    @NotBlank
    private String phoneNumber;

    @CPF
    private String document;

}

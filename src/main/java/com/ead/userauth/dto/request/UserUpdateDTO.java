package com.ead.userauth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserUpdateDTO implements Serializable {

    private static final long serialVersionUID = -283683533014534786L;

    private String fullName;
    private String phoneNumber;

    @CPF
    private String document;

}

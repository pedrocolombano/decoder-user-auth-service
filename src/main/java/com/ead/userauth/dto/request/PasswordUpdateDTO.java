package com.ead.userauth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PasswordUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1061003265774112379L;

    private String currentPassword;
    private String newPassword;

}

package com.ead.userauth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserInsertDTO implements Serializable {

    private static final long serialVersionUID = -779048429057258253L;

    private String username;
    private String email;
    private String password;
    private String fullName;
    private String phoneNumber;
    private String document;

}

package com.ead.userauth.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInsertDTO implements Serializable {

    private static final long serialVersionUID = -779048429057258253L;

    private String username;
    private String email;
    private String password;
    private String fullName;
    private String phoneNumber;
    private String document;
    private String imageUrl;

}

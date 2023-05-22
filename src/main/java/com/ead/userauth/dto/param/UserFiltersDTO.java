package com.ead.userauth.dto.param;

import com.ead.userauth.entity.enumerated.UserStatus;
import com.ead.userauth.entity.enumerated.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserFiltersDTO implements Serializable {

    private static final long serialVersionUID = -6544599173798467511L;

    private UserType userType;
    private UserStatus userStatus;
    private String email;

}

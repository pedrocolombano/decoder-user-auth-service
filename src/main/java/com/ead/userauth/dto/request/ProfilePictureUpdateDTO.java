package com.ead.userauth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProfilePictureUpdateDTO implements Serializable {

    private static final long serialVersionUID = -6508833065564461029L;

    @NotBlank
    private String imageUrl;

}

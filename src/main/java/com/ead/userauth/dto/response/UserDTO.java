package com.ead.userauth.dto.response;

import com.ead.userauth.entity.enumerated.UserStatus;
import com.ead.userauth.entity.enumerated.UserType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO extends RepresentationModel<UserDTO> implements Serializable {

    private static final long serialVersionUID = 8562210515052418685L;

    private UUID userId;
    private String username;
    private String email;
    private String fullName;
    private UserStatus userStatus;
    private UserType userType;
    private String phoneNumber;
    private String document;
    private String imageUrl;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime updatedAt;

}

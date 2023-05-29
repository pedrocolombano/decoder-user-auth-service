package com.ead.userauth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserCourseDTO implements Serializable {

    private static final long serialVersionUID = -6410978659843569878L;

    @NotNull
    private UUID courseId;

}

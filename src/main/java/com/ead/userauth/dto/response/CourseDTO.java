package com.ead.userauth.dto.response;

import com.ead.userauth.enumerated.CourseLevel;
import com.ead.userauth.enumerated.CourseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseDTO implements Serializable {

    private static final long serialVersionUID = -547900907463217519L;

    private UUID courseId;
    private String name;
    private String description;
    private String imageUrl;
    private CourseStatus courseStatus;
    private CourseLevel courseLevel;
    private UUID courseInstructor;

}

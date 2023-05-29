package com.ead.userauth.mapper;

import com.ead.userauth.dto.response.SubscribedUserDTO;
import com.ead.userauth.entity.UserCourse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserCourseMapper {

    private final ModelMapper mapper;

    public SubscribedUserDTO fromEntity(final UserCourse entity) {
        mapper.typeMap(UserCourse.class, SubscribedUserDTO.class)
                .addMapping(user -> user.getUser().getUserId(), SubscribedUserDTO::setUserId);

        return mapper.map(entity, SubscribedUserDTO.class);
    }

}

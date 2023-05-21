package com.ead.userauth.mapper;

import com.ead.userauth.dto.response.UserDTO;
import com.ead.userauth.entity.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMapper {

    private final ModelMapper mapper;

    public UserDTO fromEntityToUserDto(final User user) {
        return mapper.map(user, UserDTO.class);
    }

}

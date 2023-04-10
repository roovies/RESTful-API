package com.roovies.restapi.mapper;

import com.roovies.restapi.domain.user.dto.UserDTO;
import com.roovies.restapi.domain.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO userToUserDTO(User user);
    User userDTOToUser(UserDTO userDTO);

    List<UserDTO> usersTouserDTOs(List<User> users);
}

package com.sol.bookchat.mappers;

import com.sol.bookchat.dto.SignUpDto;
import com.sol.bookchat.dto.UserDto;
import com.sol.bookchat.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto signUpDto);

}
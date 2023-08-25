package com.zirima.mappers;


import com.zirima.dto.SignUpDto;
import com.zirima.dto.UserDto;
import com.zirima.enties.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {
    UserDto toUserDto (User user);

    @Mapping(target = "password" , ignore = true)
    User signUpToUser(SignUpDto signUpDto);
}

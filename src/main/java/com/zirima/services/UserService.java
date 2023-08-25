package com.zirima.services;

import com.zirima.dto.CredentialsDto;
import com.zirima.dto.SignUpDto;
import com.zirima.dto.UserDto;
import com.zirima.enties.User;
import com.zirima.exceptions.AppException;
import com.zirima.mappers.UserMapper;
import com.zirima.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private  final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;
    private  final UserMapper userMapper;

    public UserDto login(CredentialsDto credentialsDto){
      User user=  userRepository.findByLogin(credentialsDto.login())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        if(passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()) ,
                user.getPassword())){
            return  userMapper.toUserDto(user);
        }
        throw new AppException("Invalid  password " , HttpStatus.BAD_REQUEST);
    }

    public UserDto register(SignUpDto signUpDto) {
        Optional<User> oUser = userRepository.findByLogin(signUpDto.login());
        if(oUser.isPresent()){
            throw  new AppException("Login Already Exists" , HttpStatus.BAD_REQUEST);
        }

        // map the dto into entity
        User user  = userMapper.signUpToUser(signUpDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDto.password())));
        User savedUser = userRepository.save(user);
        return userMapper.toUserDto(savedUser);

    }
}

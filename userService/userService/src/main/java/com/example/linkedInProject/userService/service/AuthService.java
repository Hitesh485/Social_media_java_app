package com.example.linkedInProject.userService.service;

import com.example.linkedInProject.userService.dto.LoginRequestDto;
import com.example.linkedInProject.userService.dto.SignupRequestDto;
import com.example.linkedInProject.userService.dto.UserDto;
import com.example.linkedInProject.userService.entity.User;
import com.example.linkedInProject.userService.exception.BadRequestException;
import com.example.linkedInProject.userService.respository.UserRepository;
import com.example.linkedInProject.userService.utils.BCrypt;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final JwtService jwtService;

    AuthService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.modelMapper = new ModelMapper();
    }

    public UserDto signup(SignupRequestDto signupRequestDto) {
        log.info("Signup a user with email: {}", signupRequestDto.getEmail());

        boolean userExist = userRepository.existsByEmail(signupRequestDto.getEmail());
        if (userExist) throw new BadRequestException("User already exists");

        User user = modelMapper.map(signupRequestDto, User.class);
        user.setPassword(BCrypt.hash(signupRequestDto.getPassword()));

        user = userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    public String login(LoginRequestDto loginRequestDto) {
        log.info("Login request for user with email: {}", loginRequestDto.getEmail());

        User user = userRepository.findByEmail(loginRequestDto.getEmail());
        if (user.getEmail() == null){
            log.error("user not found in database: {}", user.getEmail());
            throw new BadRequestException("Incorrect email or password");
        }
        boolean isPasswordMatch = BCrypt.match(loginRequestDto.getPassword(), user.getPassword());

        if (!isPasswordMatch) {
            throw new BadRequestException("Incorrect Email or Password");
        }

        return jwtService.generateAccessToken(user);

    }
}

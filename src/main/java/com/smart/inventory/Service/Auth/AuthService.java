package com.smart.inventory.Service.Auth;

import com.smart.inventory.DTO.UserDTO;
import com.smart.inventory.Entity.User;
import com.smart.inventory.Mapper.UserMapper;
import com.smart.inventory.Security.JwtProvider;
import com.smart.inventory.Security.MyUserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final JwtProvider jwtProvider;

    private final UserMapper userMapper;

    public AuthService(JwtProvider jwtProvider, UserMapper userMapper) {
        this.jwtProvider = jwtProvider;
        this.userMapper = userMapper;
    }

    // Create a map to store user info and token.
    public Map<String, Object> login(Authentication authentication) {
        // Create user info.
        MyUserPrincipal principal = (MyUserPrincipal)authentication.getPrincipal();
        User hogwartsUser = principal.getUser();
        UserDTO userDto = this.userMapper.convertToDto(hogwartsUser);

        // Create a JWT.
        String token = this.jwtProvider.createToken(authentication);

        // Create a map to store user info and token.
        Map<String, Object> loginResultMap = new HashMap<>();

        // Put user info and token into the map.
        loginResultMap.put("userInfo", userDto);
        loginResultMap.put("token", token);

        return loginResultMap;
    }



}

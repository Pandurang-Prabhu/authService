package com.authentication.auth.services;


import com.authentication.auth.entity.RoleEntity;
import com.authentication.auth.entity.UserEntity;
import com.authentication.auth.model.CreateUserModel;
import com.authentication.auth.model.LoginModel;
import com.authentication.auth.model.LoginResponseModel;
import com.authentication.auth.repository.RoleRepository;
import com.authentication.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository  roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenService tokenService;


    public String registerUser(CreateUserModel createUserModel){
        String encodedPassword = passwordEncoder.encode(createUserModel.getPassword());
          Optional<UserEntity> userEntityValidateUserName=  userRepository.findByUserName(createUserModel.getUserName());
        Optional<UserEntity> userEntityValidateEmailId   =  userRepository.findByEmail(createUserModel.getEmail());
          if(!userEntityValidateUserName.isEmpty()){
              return "userName already Present.";
          }
        if(!userEntityValidateEmailId.isEmpty()){
            return "email already Present.";
        }
        RoleEntity role = roleRepository.findByAuthority("User").get();
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(role);

        UserEntity userEntity= new UserEntity();
        userEntity.setUserName(createUserModel.getUserName());
        userEntity.setEmail(createUserModel.getEmail());
        userEntity.setAuthorities(roles);
        userEntity.setPassword(encodedPassword);
        userRepository.save(userEntity);
        return "created";
    }

    public LoginResponseModel loginUser(LoginModel loginModel){

        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginModel.getUserName(), loginModel.getPassword())
            );

            String token = tokenService.generateJwt(auth);

            return new LoginResponseModel( token);

        } catch(AuthenticationException e){
            return new LoginResponseModel( "");
        }
    }
}

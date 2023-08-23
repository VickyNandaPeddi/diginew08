package com.aashdit.digiverifier.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import com.aashdit.digiverifier.common.model.ServiceOutcome;
import com.aashdit.digiverifier.config.admin.model.User;
import com.aashdit.digiverifier.config.admin.service.UserService;
import com.aashdit.digiverifier.login.dto.AuthenticationRequest;
import com.aashdit.digiverifier.login.dto.UserLoginDto;
import com.aashdit.digiverifier.security.JwtUtil;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
@RestController
@Slf4j
@RequestMapping(path = "/api/login")
public class LoginController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @ApiOperation("To authenticate user and generate and return JWT")
    @PostMapping(path = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceOutcome<UserLoginDto>> userLogin(@RequestBody AuthenticationRequest authRequest) throws Exception {

        ServiceOutcome<UserLoginDto> response = new ServiceOutcome<>();
        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));

            final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUserName());
            if (userDetails != null) {
                User user = userService.findByUsername(userDetails.getUsername()).getData();
                if (user != null) {
                    if (!user.getIsUserBlocked()) {
                        Boolean isOK = true;
                        if (user.getIsLocked()) {
                            response.setData(null);
                            response.setOutcome(false);
                            response.setMessage("Sorry. Your account has been locked. Please contact the System Administrator");
                            log.error(response.getMessage());
                            isOK = false;
                        }

                        if (user.getIsLoggedIn()) {
                            UserLoginDto userLoginDto = new UserLoginDto();
                            userLoginDto.setJwtToken(jwtUtil.generateToken(user.getUserName()));
                            response.setData(userLoginDto);
                            response.setOutcome(false);
                            response.setMessage("Sorry. You are already logged in.");
                            log.error(response.getMessage());
                            isOK = false;
                        }

                        if (isOK) {
                            UserLoginDto userLoginDto = new UserLoginDto();
                            userLoginDto.setJwtToken(jwtUtil.generateToken(user.getUserName()));
                            userLoginDto.setUserFirstName(user.getUserFirstName());
                            userLoginDto.setOrganizationId(user.getOrganization() != null ? user.getOrganization().getOrganizationId() : null);
                            userLoginDto.setRoleCode(user.getRole().getRoleCode());
                            userLoginDto.setRoleName(user.getRole().getRoleName());
                            userLoginDto.setUserId(user.getUserId());
                            response.setData(userLoginDto);
                            user.setIsLoggedIn(true);
                            userService.saveUserLoginData(user);
                            response.setOutcome(true);
                            response.setMessage("User authenticated successfully.");
                        }

                    } else {
                        response.setData(null);
                        response.setOutcome(false);
                        response.setMessage("Sorry, this account has been deactivated.");
                        log.error(response.getMessage());
                    }
                } else {
                    response.setData(null);
                    response.setOutcome(false);
                    response.setMessage("Invalid username or password.");
                    log.error(response.getMessage());
                }
            } else {
                response.setData(null);
                response.setOutcome(false);
                response.setMessage("Request Failed Due to System Issue.");
                log.error(response.getMessage());
            }
        } catch (Exception ex) {
            response.setData(null);
            response.setOutcome(false);
            response.setMessage("Invalid username or password.");
            log.error("Exception occured in userLogin method in LoginController-->" + ex);
        }

        return new ResponseEntity<ServiceOutcome<UserLoginDto>>(response, HttpStatus.OK);
    }

    @ApiOperation("To sign off a particular user using token from header")
    @PostMapping(path = "/sign-off", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceOutcome<String>> userSignOff(@RequestHeader("Authorization") String authorization) throws Exception {

        ServiceOutcome<String> response = new ServiceOutcome<>();
        try {
            String token = authorization.substring(7);
            String username = jwtUtil.extractUsername(token);
            User user = userService.findByUsername(username).getData();
            user.setIsLoggedIn(false);
            userService.saveUserLoginData(user);

            response.setOutcome(true);
            response.setMessage("Signed off successfuly.");

        } catch (Exception ex) {
            response.setData(null);
            response.setOutcome(false);
            response.setMessage("Unable to sign off.");
            log.error("Exception occured in userSignOff method in LoginController-->" + ex);
        }

        return new ResponseEntity<ServiceOutcome<String>>(response, HttpStatus.OK);
    }

}


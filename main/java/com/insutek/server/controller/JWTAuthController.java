package com.insutek.server.controller;

import com.insutek.server.security.InsutekUserDetailService;
import com.insutek.server.security.jwt.AuthenticationRequest;
import com.insutek.server.security.jwt.AuthenticationResponse;
import com.insutek.server.security.jwt.JWTUtilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class JWTAuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private InsutekUserDetailService insutekUserDetailService;

    private static final Logger logger = LoggerFactory.getLogger(JWTAuthController.class);
    @Autowired
    JWTUtilService jwtUtilService;
    @RequestMapping(value="/authenticate-user", method=RequestMethod.POST)
        public ResponseEntity<?> createAuthenticationToken(
                @RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try{
            Authentication authentication =  authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword())
            );
//            UserDetails insutekUserDetails =  (UserDetails) authentication.getPrincipal();
        final UserDetails insutekUserDetails =  insutekUserDetailService.
                loadUserByUsername(authenticationRequest.getUserName());
            logger.info(String.format("Found User with userName {%s} and password {%s}", insutekUserDetails.getUsername(), insutekUserDetails.getPassword()));
            String jwtToken = jwtUtilService.generateToken(insutekUserDetails);
            logger.info(String.format("Password is same: {%s}",authenticationRequest.getPassword().equals(insutekUserDetails.getPassword())));
            logger.info(String.format("Token for userName {%s} is {%s}", insutekUserDetails.getUsername(), jwtToken));
            return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
        }catch( BadCredentialsException e){
            logger.error(String.format("Invalid username or password for username: {%s} ", authenticationRequest.getUserName()));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }


    }
}

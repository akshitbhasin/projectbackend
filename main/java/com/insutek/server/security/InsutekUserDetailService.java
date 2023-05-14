package com.insutek.server.security;

import com.insutek.server.domain.User;
import com.insutek.server.repository.InsutekUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InsutekUserDetailService implements UserDetailsService {

    @Autowired
    InsutekUserRepository insutekUserRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> currentUser =  insutekUserRepository.findByUserName(userName);
        currentUser.orElseThrow(() -> new UsernameNotFoundException(String.format("Username: %s not found", userName)));
        return currentUser.map(InsutekUserDetails::new).get();
    }


}

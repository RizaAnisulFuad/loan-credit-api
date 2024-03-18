package com.enigma.load_credit_api.service.impl;

import com.enigma.load_credit_api.entity.UserAccount;
import com.enigma.load_credit_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Override
    public UserAccount getByUserId(String id) {
        return null;
    }

    @Override
    public UserAccount getByContext() {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}

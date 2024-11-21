package com.inn.bakery.JWT;

import com.inn.bakery.dao.UserDao;
import com.inn.bakery.POJO.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;
@Slf4j
@Service
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao; // Use the injected instance of UserDao

    private User userDetail; // Keep user details for additional access if needed

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       log.info("Inside loadUserByUsername{}", username);
        userDetail = userDao.findByEmail(username); // Call the non-static method using the instance
        if (!Objects.isNull(userDetail)) {
            return new org.springframework.security.core.userdetails.User(
                    userDetail.getEmail(),
                    userDetail.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")) // Assign a role
            );
        }
        throw new UsernameNotFoundException("User not found.");
    }

    public User getUserDetails() {
        return userDetail;
    }
}

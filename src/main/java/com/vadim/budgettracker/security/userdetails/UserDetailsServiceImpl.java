package com.vadim.budgettracker.security.userdetails;


import com.vadim.budgettracker.dao.UserDAO;
import com.vadim.budgettracker.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//@Service("userDetailServiceImpl")
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    private final UserDAO userDAO;
//
//    @Autowired
//    public UserDetailsServiceImpl(UserDAO repository) {
//        this.userDAO = repository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userDAO.findByEmail(email).orElseThrow(() ->
//            new UsernameNotFoundException("The username is not found")
//        );
//        return SecurityUser.fromUser(user);
//    }
//}

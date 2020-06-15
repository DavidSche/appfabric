package com.davidche.appfabric.uaa.service;

import com.davidche.appfabric.uaa.log.MyLoggable;
import com.davidche.appfabric.uaa.model.CustomUserDetails;
import com.davidche.appfabric.uaa.model.User;
import com.davidche.appfabric.uaa.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * UserDetailsService
 * 是Spring Security进行身份验证的时候会使用，
 * 我们这里就一个加载用户信息的简单方法，
 * 就是得到当前登录用户的一些用户名、密码、用户所拥有的角色等等一些信息
 */
@MyLoggable
@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 根据邮件获取用户
     *
     * @param email 邮件地址
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> dbUser = userRepository.findByEmail(email);
        log.info("Fetched user : " + dbUser + " by " + email);
        return dbUser.map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Couldn't find a matching user email in the database for " + email));
    }

    /**
     * 根据用户ID获取用户信息
     *
     * @param id ID标识
     * @return
     */
    public UserDetails loadUserById(Long id) {
        Optional<User> dbUser = userRepository.findById(id);
        log.info("Fetched user : " + dbUser + " by " + id);
        return dbUser.map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Couldn't find a matching user id in the database for " + id));
    }

    /**
     * 根据用户phone 获取用户信息
     *
     * @param phone 手机号码
     * @return
     */
    public UserDetails loadUserByPhone(String phone) {
        Optional<User> dbUser = userRepository.findByPhone(phone);
        log.info("Fetched user : " + dbUser + " by " + phone);
        return dbUser.map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Couldn't find a matching user phone in the database for " + phone));
    }
}

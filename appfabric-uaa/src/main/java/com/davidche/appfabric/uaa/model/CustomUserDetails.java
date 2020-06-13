package com.davidche.appfabric.uaa.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 实现SS获取用户凭证的接口信息
 * 用户账号信息
 *
 * @Author: David.che
 *
 */
public class CustomUserDetails extends User implements UserDetails {

    public CustomUserDetails(final User user) {
        super(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole().name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getEmail();
    }
    //账户是否未过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    //账户是否未被锁
    @Override
    public boolean isAccountNonLocked() {
        return super.getActive();
    }
    //账户凭证（密码）是否过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    //是否启用
    @Override
    public boolean isEnabled() {
        return super.getEmailVerified();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CustomUserDetails that = (CustomUserDetails) obj;
        return Objects.equals(getId(), that.getId());
    }
}

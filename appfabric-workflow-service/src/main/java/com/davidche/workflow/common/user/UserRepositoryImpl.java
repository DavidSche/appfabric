package com.davidche.workflow.common.user;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnBean(value = UserRepository.class)
public class UserRepositoryImpl implements UserRepository {
    @Override
    public AbstractUser findByUsername(String authenticatedUser) {
        return null;
    }
}

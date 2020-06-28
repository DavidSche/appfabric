package com.davidche.workflow.common.user;

public interface UserRepository {

    AbstractUser findByUsername(String authenticatedUser);

}
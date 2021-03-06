package com.davidche.appfabric.uaa.service;

import com.davidche.appfabric.uaa.log.MyLoggable;
import com.davidche.appfabric.uaa.model.Role;
import com.davidche.appfabric.uaa.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@MyLoggable
@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Find all roles from the database
     */
    public Collection<Role> findAll() {
        return roleRepository.findAll();
    }

}

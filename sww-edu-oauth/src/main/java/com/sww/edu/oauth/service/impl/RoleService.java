package com.sww.edu.oauth.service.impl;

import com.sww.edu.oauth.entity.Role;
import com.sww.edu.oauth.provider.OrganizationProvider;
import com.sww.edu.oauth.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private OrganizationProvider organizationProvider;

    @Override
    public Set<Role> queryUserRolesByUserId(String userId) {
        return organizationProvider.queryRolesByUserId(userId).getData();
    }

}

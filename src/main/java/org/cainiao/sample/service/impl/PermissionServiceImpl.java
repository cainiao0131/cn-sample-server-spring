package org.cainiao.sample.service.impl;

import lombok.RequiredArgsConstructor;
import org.cainiao.sample.dao.service.PermissionMapperService;
import org.cainiao.sample.entity.acl.Permission;
import org.cainiao.sample.service.PermissionService;
import org.springframework.stereotype.Service;

/**
 * <br />
 * <p>
 * Author: Cai Niao(wdhlzd@163.com)<br />
 */
@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionMapperService permissionMapperService;

    @Override
    public Permission addOrEditPermission(Permission permission) {
        return permissionMapperService.saveOrUpdatePermission(permission);
    }
}

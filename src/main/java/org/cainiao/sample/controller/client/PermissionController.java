package org.cainiao.sample.controller.client;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.cainiao.sample.entity.acl.Permission;
import org.cainiao.sample.service.PermissionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色管理
 * <p>
 * Author: Cai Niao(wdhlzd@163.com)<br />
 */
@RestController
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;

    @PostMapping("permission")
    @Operation(summary = "添加或编辑权限")
    public Permission addOrEditPermission(@Parameter(description = "权限") @RequestBody Permission permission) {
        return permissionService.addOrEditPermission(permission);
    }
}

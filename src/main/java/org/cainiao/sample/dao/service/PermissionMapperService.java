package org.cainiao.sample.dao.service;

import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cainiao.common.exception.BusinessException;
import org.cainiao.sample.dao.mapper.PermissionMapper;
import org.cainiao.sample.entity.acl.Permission;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * <br />
 * <p>
 * Author: Cai Niao(wdhlzd@163.com)<br />
 */
@Service
public class PermissionMapperService extends ServiceImpl<PermissionMapper, Permission> implements IService<Permission> {

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Permission saveOrUpdatePermission(Permission permission) {
        Long permissionId = permission.getId();
        String newName = permission.getName();
        if (permissionId == null) {
            // 插入权限
            if (!StringUtils.hasText(newName)) {
                throw new BusinessException("权限名不能为空！");
            }
            if (lambdaQuery().eq(Permission::getName, newName).exists()) {
                throw new BusinessException("权限名已存在！");
            }
            if (!save(permission)) {
                throw new BusinessException("添加权限失败！");
            }
            return permission;
        }

        // 更新权限
        Permission oldPermission = getById(permissionId);
        if (oldPermission == null) {
            throw new BusinessException("未找到权限！");
        }
        boolean isSameName = Objects.equals(newName, oldPermission.getName());
        String newDescription = permission.getDescription();
        boolean isSameDescription = Objects.equals(newDescription, oldPermission.getDescription());
        if (isSameName && isSameDescription) {
            return oldPermission;
        }
        LambdaUpdateChainWrapper<Permission> updateWrapper = lambdaUpdate();
        updateWrapper.eq(Permission::getId, permissionId);
        if (!isSameName) {
            if (!StringUtils.hasText(newName)) {
                throw new BusinessException("权限名不能为空！");
            }
            if (lambdaQuery().eq(Permission::getName, newName).ne(Permission::getId, permissionId).exists()) {
                throw new BusinessException("权限名已存在！");
            }
            updateWrapper.set(Permission::getName, newName);
        }
        if (!isSameDescription) {
            updateWrapper.set(Permission::getDescription, newDescription);
        }
        updateWrapper.set(Permission::getUpdatedAt, LocalDateTime.now());
        if (!update(permission, updateWrapper)) {
            throw new BusinessException("编辑权限失败！");
        }
        return permission;
    }
}

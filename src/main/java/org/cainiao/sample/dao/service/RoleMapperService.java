package org.cainiao.sample.dao.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cainiao.common.exception.BusinessException;
import org.cainiao.sample.dao.mapper.RoleMapper;
import org.cainiao.sample.entity.acl.Role;
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
public class RoleMapperService extends ServiceImpl<RoleMapper, Role> implements IService<Role> {

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Role saveOrUpdateRole(Role role) {
        Long roleId = role.getId();
        String newName = role.getName();
        if (roleId == null) {
            // 插入角色
            if (!StringUtils.hasText(newName)) {
                throw new BusinessException("角色名不能为空！");
            }
            if (lambdaQuery().eq(Role::getName, newName).exists()) {
                throw new BusinessException("角色名已存在！");
            }
            if (!save(role)) {
                throw new BusinessException("添加角色失败！");
            }
            return role;
        }

        // 更新用户
        Role oldRole = getById(roleId);
        if (oldRole == null) {
            throw new BusinessException("未找到角色！");
        }
        boolean isSameName = Objects.equals(newName, oldRole.getName());
        String newDescription = role.getDescription();
        boolean isSameDescription = Objects.equals(newDescription, oldRole.getDescription());
        if (isSameName && isSameDescription) {
            return oldRole;
        }
        LambdaUpdateChainWrapper<Role> updateWrapper = lambdaUpdate();
        updateWrapper.eq(Role::getId, roleId);
        if (!isSameName) {
            if (!StringUtils.hasText(newName)) {
                throw new BusinessException("角色名不能为空！");
            }
            if (lambdaQuery().eq(Role::getName, newName).ne(Role::getId, roleId).exists()) {
                throw new BusinessException("角色名已存在！");
            }
            updateWrapper.set(Role::getName, newName);
        }
        if (!isSameDescription) {
            updateWrapper.set(Role::getDescription, newDescription);
        }
        updateWrapper.set(Role::getUpdatedAt, LocalDateTime.now());
        if (!update(role, updateWrapper)) {
            throw new BusinessException("编辑角色失败！");
        }
        return role;
    }

    public IPage<Role> searchPage(int current, int size, String key) {
        IPage<Role> page = new Page<>(current, size);
        return StringUtils.hasText(key)
            ? page(page, lambdaQuery().like(Role::getName, key).or().like(Role::getDescription, key))
            : page(page);
    }
}

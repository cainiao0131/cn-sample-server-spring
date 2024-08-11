package org.cainiao.sample.dao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cainiao.sample.dao.mapper.UserRoleMapper;
import org.cainiao.sample.entity.acl.UserRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

import static org.cainiao.sample.util.Util.subtraction;

/**
 * <br />
 * <p>
 * Author: Cai Niao(wdhlzd@163.com)<br />
 */
@Service
public class UserRoleMapperService extends ServiceImpl<UserRoleMapper, UserRole> implements IService<UserRole> {

    public Set<Long> selectRoleIdsOfUser(Long userId) {
        return lambdaQuery().select(UserRole::getRoleId).eq(UserRole::getUserId, userId).list()
            .stream().map(UserRole::getRoleId).collect(Collectors.toSet());
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void setRolesOfUser(long userId, Set<Long> roleIds) {
        Set<Long> oldRoleIds = selectRoleIdsOfUser(userId);
        subtraction(roleIds, oldRoleIds);
        UserRole a = UserRole.builder().userId(userId).roleId(1L).build();
        if (!roleIds.isEmpty()) {
            saveBatch(roleIds.stream().map(roleId -> (UserRole) UserRole
                .builder().userId(userId).roleId(roleId).build()).toList());
        }
        if (!oldRoleIds.isEmpty()) {
            removeBatchByIds(oldRoleIds);
        }
    }
}

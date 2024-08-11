package org.cainiao.sample.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.cainiao.sample.dao.service.RoleMapperService;
import org.cainiao.sample.dao.service.UserMapperService;
import org.cainiao.sample.dao.service.UserRoleMapperService;
import org.cainiao.sample.dto.response.GrantedRoles;
import org.cainiao.sample.entity.acl.User;
import org.cainiao.sample.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * <br />
 * <p>
 * Author: Cai Niao(wdhlzd@163.com)<br />
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapperService userMapperService;
    private final RoleMapperService roleMapperService;
    private final UserRoleMapperService userRoleMapperService;

    @Override
    public User addOrEditUser(User user) {
        return userMapperService.saveOrUpdateNewName(user);
    }

    @Override
    public IPage<User> userPage(int current, int size, String key) {
        return userMapperService.searchPage(current, size, key);
    }

    @Override
    public User userDetail(long userId) {
        return userMapperService.getById(userId);
    }

    @Override
    public GrantedRoles grantedRoles(long userId) {
        return GrantedRoles.builder()
            .grantedRoleIds(userRoleMapperService.selectRoleIdsOfUser(userId))
            .allRoles(roleMapperService.list())
            .build();
    }

    @Override
    public void grantRoles(long userId, Set<Long> roleIds) {
        userRoleMapperService.setRolesOfUser(userId, roleIds);
    }
}

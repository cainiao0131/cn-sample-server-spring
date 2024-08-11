package org.cainiao.sample.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.cainiao.sample.dao.service.RoleMapperService;
import org.cainiao.sample.entity.acl.Role;
import org.cainiao.sample.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <br />
 * <p>
 * Author: Cai Niao(wdhlzd@163.com)<br />
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleMapperService roleMapperService;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Role addOrEditRole(Role role) {
        return roleMapperService.saveOrUpdateRole(role);
    }

    @Override
    public IPage<Role> rolePage(int current, int size, String key) {
        return roleMapperService.searchPage(current, size, key);
    }
}

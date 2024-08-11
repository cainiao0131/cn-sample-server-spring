package org.cainiao.sample.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.cainiao.sample.entity.acl.Role;

public interface RoleService {

    Role addOrEditRole(Role role);

    IPage<Role> rolePage(int current, int size, String key);
}

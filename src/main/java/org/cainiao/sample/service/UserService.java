package org.cainiao.sample.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.cainiao.sample.dto.response.GrantedRoles;
import org.cainiao.sample.entity.acl.User;

import java.util.Set;

public interface UserService {

    User addOrEditUser(User user);

    IPage<User> userPage(int current, int size, String key);

    User userDetail(long userId);

    GrantedRoles grantedRoles(long userId);

    void grantRoles(long userId, Set<Long> roleIds);

    Set<String> permissions(long userId);
}

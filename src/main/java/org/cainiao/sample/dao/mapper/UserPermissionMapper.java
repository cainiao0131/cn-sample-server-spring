package org.cainiao.sample.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.cainiao.sample.entity.acl.UserPermission;
import org.cainiao.sample.entity.acl.UserRole;

import java.util.Set;

/**
 * <br />
 * <p>
 * Author: Cai Niao(wdhlzd@163.com)<br />
 */
public interface UserPermissionMapper extends BaseMapper<UserPermission> {

    Set<String> findPermissionsByUserId(@Param("userId") Long userId);
}

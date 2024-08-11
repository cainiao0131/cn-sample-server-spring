package org.cainiao.sample.dao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.cainiao.sample.dao.mapper.UserPermissionMapper;
import org.cainiao.sample.dao.mapper.UserRoleMapper;
import org.cainiao.sample.entity.acl.UserPermission;
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
public class UserPermissionMapperService extends ServiceImpl<UserPermissionMapper, UserPermission> implements IService<UserPermission> {

}

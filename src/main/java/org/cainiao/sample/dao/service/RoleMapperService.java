package org.cainiao.sample.dao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cainiao.sample.dao.mapper.RoleMapper;
import org.cainiao.sample.entity.acl.Role;
import org.springframework.stereotype.Service;

/**
 * <br />
 * <p>
 * Author: Cai Niao(wdhlzd@163.com)<br />
 */
@Service
public class RoleMapperService extends ServiceImpl<RoleMapper, Role> implements IService<Role> {

}

package org.cainiao.sample.dao.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cainiao.sample.dto.response.UserInfo;
import org.cainiao.sample.entity.acl.User;

import java.util.List;

/**
 * <br />
 * <p>
 * Author: Cai Niao(wdhlzd@163.com)<br />
 */
public interface UserMapper extends BaseMapper<User> {

    List<UserInfo> userInfos(int offset, int size, QueryWrapper<User> ew);
}

package org.cainiao.sample.dao.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cainiao.common.exception.BusinessException;
import org.cainiao.sample.dao.mapper.UserMapper;
import org.cainiao.sample.entity.acl.User;
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
public class UserMapperService extends ServiceImpl<UserMapper, User> implements IService<User> {

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public User saveOrUpdateNewName(User user) {
        Long userId = user.getId();
        String newName = user.getName();
        String newNickName = user.getNickName();
        if (userId == null) {
            // 插入用户
            if (!StringUtils.hasText(newName)) {
                throw new BusinessException("用户名不能为空！");
            }
            if (!StringUtils.hasText(newNickName)) {
                throw new BusinessException("昵称不能为空！");
            }
            if (count(lambdaQuery().eq(User::getName, newName)) > 0) {
                throw new BusinessException("用户名已存在！");
            }
            if (!save(user)) {
                throw new BusinessException("添加用户失败！");
            }
            return user;
        }

        // 更新用户
        User oldUser = getById(userId);
        if (oldUser == null) {
            throw new BusinessException("未找到用户！");
        }
        boolean isSameName = Objects.equals(newName, oldUser.getName());
        String newDescription = user.getDescription();
        boolean isSameDescription = Objects.equals(newDescription, oldUser.getDescription());
        boolean isSameNickName = Objects.equals(newNickName, oldUser.getNickName());
        if (isSameName && isSameNickName && isSameDescription) {
            return oldUser;
        }
        LambdaUpdateChainWrapper<User> updateWrapper = lambdaUpdate();
        updateWrapper.eq(User::getId, userId);
        if (!isSameName) {
            if (!StringUtils.hasText(newName)) {
                throw new BusinessException("用户名不能为空！");
            }
            if (count(lambdaQuery().eq(User::getName, newName).ne(User::getId, userId)) > 0) {
                throw new BusinessException("用户名已存在！");
            }
            updateWrapper.set(User::getName, newName);
        }
        if (!isSameNickName) {
            if (!StringUtils.hasText(newNickName)) {
                throw new BusinessException("昵称不能为空！");
            }
            updateWrapper.set(User::getNickName, newNickName);
        }
        if (!isSameDescription) {
            updateWrapper.set(User::getDescription, newDescription);
        }
        updateWrapper.set(User::getUpdatedAt, LocalDateTime.now());
        if (!update(user, updateWrapper)) {
            throw new BusinessException("编辑用户失败！");
        }
        return user;
    }

    public IPage<User> searchPage(int current, int size, String key) {
        IPage<User> page = new Page<>(current, size);
        return StringUtils.hasText(key)
            ? page(page, lambdaQuery()
            .like(User::getName, key).or().like(User::getNickName, key).or().like(User::getDescription, key))
            : page(page);
    }
}

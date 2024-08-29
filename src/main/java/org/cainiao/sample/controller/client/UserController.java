package org.cainiao.sample.controller.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.cainiao.sample.dto.response.GrantedRoles;
import org.cainiao.sample.entity.acl.User;
import org.cainiao.sample.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static org.cainiao.sample.dao.DaoUtil.DEFAULT_PAGE;
import static org.cainiao.sample.dao.DaoUtil.DEFAULT_PAGE_SIZE;

/**
 * 用户管理
 * <p>
 * Author: Cai Niao(wdhlzd@163.com)<br />
 */
@RestController
@RequiredArgsConstructor
@Tag(name = "User", description = "用户管理")
public class UserController {

    private final UserService userService;

    @PostMapping("user")
    @Operation(summary = "添加或编辑用户")
    public User addOrEditUser(@Parameter(description = "用户") @RequestBody User user) {
        return userService.addOrEditUser(user);
    }

    @GetMapping("users")
    @Operation(summary = "分页模糊查询用户列表")
    public IPage<User> userPage(
        @Parameter(description = "当前页") @RequestParam(required = false, defaultValue = DEFAULT_PAGE) int current,
        @Parameter(description = "每页显示记录数") @RequestParam(required = false, defaultValue = DEFAULT_PAGE_SIZE) int size,
        @Parameter(description = "搜索关键词") @RequestParam(required = false) String key) {

        return userService.userPage(current, size, key);
    }

    @GetMapping("user/{userId}")
    @Operation(summary = "用户详情")
    public User userDetail(@Parameter(description = "用户 ID", required = true) @PathVariable long userId) {
        return userService.userDetail(userId);
    }

    @GetMapping("user/{userId}/granted-roles")
    @Operation(summary = "用户的角色ID列表以及全量角色列表")
    public GrantedRoles grantedRoles(@Parameter(description = "用户 ID", required = true) @PathVariable long userId) {
        return userService.grantedRoles(userId);
    }

    @GetMapping("user/{userId}/grant-roles")
    @Operation(summary = "授予用户角色")
    public void grantRoles(@Parameter(description = "用户 ID", required = true) @PathVariable long userId,
                           @Parameter(description = "用户角色 ID") @RequestBody Set<Long> roleIds) {
        userService.grantRoles(userId, roleIds);
    }

    @GetMapping("user/{userId}/permissions")
    @Operation(summary = "用户权限")
    public Set<String> permissions(@Parameter(description = "用户 ID", required = true) @PathVariable long userId) {
        return userService.permissions(userId);
    }
}

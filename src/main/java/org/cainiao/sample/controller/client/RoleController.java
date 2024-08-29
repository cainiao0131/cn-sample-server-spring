package org.cainiao.sample.controller.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.cainiao.sample.entity.acl.Role;
import org.cainiao.sample.service.RoleService;
import org.springframework.web.bind.annotation.*;

import static org.cainiao.sample.dao.DaoUtil.DEFAULT_PAGE;
import static org.cainiao.sample.dao.DaoUtil.DEFAULT_PAGE_SIZE;

/**
 * 角色管理
 * <p>
 * Author: Cai Niao(wdhlzd@163.com)<br />
 */
@RestController
@RequiredArgsConstructor
@Tag(name = "Role", description = "角色管理")
public class RoleController {

    private final RoleService roleService;

    @PostMapping("role")
    @Operation(summary = "添加或编辑角色")
    public Role addOrEditRole(@Parameter(description = "角色") @RequestBody Role role) {
        return roleService.addOrEditRole(role);
    }

    @GetMapping("page/search/roles")
    @Operation(summary = "分页模糊查询角色列表")
    public IPage<Role> rolePage(
        @Parameter(description = "当前页") @RequestParam(required = false, defaultValue = DEFAULT_PAGE) int current,
        @Parameter(description = "每页显示记录数") @RequestParam(required = false, defaultValue = DEFAULT_PAGE_SIZE) int size,
        @Parameter(description = "搜索关键词") @RequestParam(required = false) String key) {

        return roleService.rolePage(current, size, key);
    }
}

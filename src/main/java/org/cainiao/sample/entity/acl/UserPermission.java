package org.cainiao.sample.entity.acl;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.cainiao.common.dao.IdBaseEntity;

/**
 * <br />
 * <p>
 * Author: Cai Niao(wdhlzd@163.com)<br />
 */
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_user_permission")
@Schema(name = "UserPermission", description = "用户权限")
public class UserPermission extends IdBaseEntity {

    @TableField(value = "up_user_id", insertStrategy = FieldStrategy.NOT_NULL)
    @Schema(description = "用户 ID")
    private Long userId;

    @TableField(value = "up_permission_id", insertStrategy = FieldStrategy.NOT_NULL)
    @Schema(description = "权限 ID")
    private Long permissionId;
}

package org.cainiao.sample.entity.acl;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.cainiao.sample.entity.BaseEntity;

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
public class UserPermission extends BaseEntity {

    @TableField("up_user_id")
    @Schema(description = "用户 ID")
    private Long userId;

    @TableField("up_permission_id")
    @Schema(description = "权限 ID")
    private Long permissionId;
}

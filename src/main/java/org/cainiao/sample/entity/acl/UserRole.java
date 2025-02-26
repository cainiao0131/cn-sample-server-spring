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
@TableName("t_user_role")
@Schema(name = "UserRole", description = "用户角色")
public class UserRole extends IdBaseEntity {

    @TableField(value = "ur_user_id", insertStrategy = FieldStrategy.NOT_NULL)
    @Schema(description = "用户 ID")
    private Long userId;

    @TableField(value = "ur_role_id", insertStrategy = FieldStrategy.NOT_NULL)
    @Schema(description = "角色 ID")
    private Long roleId;
}

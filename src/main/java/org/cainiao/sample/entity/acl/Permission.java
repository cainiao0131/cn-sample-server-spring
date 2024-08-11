package org.cainiao.sample.entity.acl;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.cainiao.common.entity.IdBaseEntity;

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
@TableName("t_permission")
@Schema(name = "Permission", description = "权限")
public class Permission extends IdBaseEntity {

    @TableField("p_parent_id")
    @Schema(description = "父权限 ID")
    private Long parentId;

    @TableField("p_name")
    @Schema(description = "权限名")
    private String name;

    @TableField("p_description")
    @Schema(description = "权限描述")
    private String description;
}

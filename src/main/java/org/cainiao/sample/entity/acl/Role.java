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
import org.cainiao.common.dao.ColumnDefine;
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
@TableName("t_role")
@Schema(name = "Role", description = "角色")
public class Role extends IdBaseEntity {

    @TableField(value = "r_name", insertStrategy = FieldStrategy.NOT_EMPTY)
    @ColumnDefine(unique = true)
    @Schema(description = "角色名")
    private String name;

    @TableField("r_description")
    @Schema(description = "角色描述")
    private String description;
}

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
@TableName("t_user")
@Schema(name = "User", description = "用户")
public class User extends IdBaseEntity {

    @TableField("u_name")
    @Schema(description = "用户名")
    private String name;

    @TableField("u_nick_name")
    @Schema(description = "昵称")
    private String nickName;

    @TableField("u_description")
    @Schema(description = "用户描述")
    private String description;
}

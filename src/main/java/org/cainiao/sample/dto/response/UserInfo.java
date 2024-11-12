package org.cainiao.sample.dto.response;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.cainiao.sample.entity.acl.User;

import java.io.Serial;

/**
 * <br />
 * <p>
 * Author: Cai Niao(wdhlzd@163.com)<br />
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserInfo extends User {

    @Serial
    private static final long serialVersionUID = -7256516792983256304L;

    @TableField(value = "last_granted_role_time")
    @Schema(description = "用户最后被授予角色的时间")
    private String lastGrantedRoleTime;
}

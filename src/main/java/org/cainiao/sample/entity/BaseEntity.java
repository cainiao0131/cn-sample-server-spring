package org.cainiao.sample.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import static lombok.Builder.Default;

/**
 * <br />
 * <p>
 * Author: Cai Niao(wdhlzd@163.com)<br />
 */
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false, of = "id")
public class BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -8288575876362981857L;

    @TableId(type = IdType.AUTO)
    @Schema(description = "主键 ID")
    private Long id;

    @TableField("created_by")
    @Schema(description = "创建人")
    private String createdBy;

    @TableField("created_at")
    @Schema(description = "创建时间")
    @Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @TableField("updated_by")
    @Schema(description = "最后更新人")
    private String updatedBy;

    @TableField("updated_at")
    @Schema(description = "最后更新时间")
    @Default
    private LocalDateTime updatedAt = LocalDateTime.now();
}

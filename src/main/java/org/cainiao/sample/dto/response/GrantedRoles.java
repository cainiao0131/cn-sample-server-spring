package org.cainiao.sample.dto.response;

import lombok.Builder;
import lombok.Data;
import org.cainiao.sample.entity.acl.Role;

import java.util.List;
import java.util.Set;

/**
 * <br />
 * <p>
 * Author: Cai Niao(wdhlzd@163.com)<br />
 */
@Builder
@Data
public class GrantedRoles {

    private Set<Long> grantedRoleIds;

    private List<Role> allRoles;
}

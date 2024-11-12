package org.cainiao.sample.dao.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.cainiao.sample.dto.response.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * <br />
 * <p>
 * Author: Cai Niao(wdhlzd@163.com)<br />
 */
@SpringBootTest
public class UserMapperServiceTest {

    private final UserMapperService userMapperService;

    @Test
    public void testGeneratedSQL() {
        IPage<UserInfo> userInfos = userMapperService.searchPageInfo(1, 2, "test");
        assertNotNull(userInfos, "userInfos should not be null");
    }

    @Autowired
    public UserMapperServiceTest(UserMapperService userMapperService) {
        this.userMapperService = userMapperService;
    }
}

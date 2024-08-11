package org.cainiao.sample;

import org.cainiao.sample.dao.mapper.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackageClasses = {UserMapper.class})
public class SampleApplication {

    public static void main(String[] args) {
        new SpringApplication(SampleApplication.class).run(args);
    }
}

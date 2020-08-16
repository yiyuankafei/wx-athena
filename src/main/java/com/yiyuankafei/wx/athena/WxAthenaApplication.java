package com.yiyuankafei.wx.athena;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableDiscoveryClient
//@EnableDubbo(scanBasePackages = "com.yiyuankafei.wx.athena.dubbo.impl")
@SpringBootApplication
@EnableScheduling
@MapperScan({"com.yiyuankafei.wx.athena.mapper","com.yiyuankafei.wx.athena.mapper.custom"})
public class WxAthenaApplication extends SpringBootServletInitializer implements CommandLineRunner {
	
	public static void main(String[] args) {
        SpringApplication.run(WxAthenaApplication.class, args);
    }

	public void run(String... args) throws Exception {
		System.out.println("系统启动完成！");
	}

}

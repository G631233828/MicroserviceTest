package zhongchiedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient // 客戶端
@EnableHystrix //开启Hystrix支持
@EnableFeignClients(basePackages = { "zhongchiedu" })
@ComponentScan("zhongchiedu")
public class ApplicationAppAdmin {
 
	
	public static void main(String[] args) {
		SpringApplication.run(ApplicationAppAdmin.class, args);
	}
}

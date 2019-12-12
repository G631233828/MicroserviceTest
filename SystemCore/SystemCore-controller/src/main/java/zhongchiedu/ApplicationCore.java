package zhongchiedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableEurekaClient//本服务启动之后会自动注册金eureka服务中
@EnableDiscoveryClient//服务发现
@EnableCircuitBreaker  //对Hystrix熔断器的支持,允许断路器
public class ApplicationCore {

	public static void main(String[] args) {
		
		SpringApplication.run(ApplicationCore.class, args);
	}
}

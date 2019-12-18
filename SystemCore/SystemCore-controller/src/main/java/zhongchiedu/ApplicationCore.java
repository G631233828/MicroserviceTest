package zhongchiedu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
@EnableEurekaClient//本服务启动之后会自动注册金eureka服务中
@EnableDiscoveryClient//服务发现
@EnableCircuitBreaker  //对Hystrix熔断器的支持,允许断路器
public class ApplicationCore {

	public static void main(String[] args) {
		
		SpringApplication.run(ApplicationCore.class, args);
	}
	
	
//	@Bean
//	public MappingJackson2HttpMessageConverter getMappingJackson2HttpMessageConverter() {
//		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
//		//设置日期格式
//		ObjectMapper objectMapper = new ObjectMapper();
//		SimpleDateFormat smt = new SimpleDateFormat("yyyy-MM-dd HH:ss");
//		objectMapper.setDateFormat(smt);
//		mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
//		//设置中文编码格式
//		List<MediaType> list = new ArrayList<MediaType>();
//		list.add(MediaType.APPLICATION_JSON_UTF8);
//		mappingJackson2HttpMessageConverter.setSupportedMediaTypes(list);
//		return mappingJackson2HttpMessageConverter;
//	}
	
}

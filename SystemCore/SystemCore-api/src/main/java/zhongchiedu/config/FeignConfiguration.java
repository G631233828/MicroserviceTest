package zhongchiedu.config;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;

/**
 * @Author Gu Yuxing
 * @Create 2019-02-12 18:45
 **/
@Configuration
public class FeignConfiguration {

// 启用Fegin自定义注解 如@RequestLine @Param
//   @Bean
//   public Contract feignContract(){
//       return new Contract.Default();
//   }

	// feign 实现多pojo传输与MultipartFile上传 编码器，需配合开启feign自带注解使用
//   @Bean
//   public Encoder feignSpringFormEncoder(){
//       //return new FeignSpringFormEncoder();
//	   return new SpringFormEncoder();
//   }
//   
//   

	@Autowired
	private ObjectFactory<HttpMessageConverters> messageConverters;

	@Bean
	public Encoder feignFormEncoder() {
		return new SpringFormEncoder(new SpringEncoder(messageConverters));
	}

}

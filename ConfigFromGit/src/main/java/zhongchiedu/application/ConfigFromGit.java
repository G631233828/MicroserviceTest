package zhongchiedu.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer // configServer 配置中心
public class ConfigFromGit {

	public static void main(String[] args) {
		SpringApplication.run(ConfigFromGit.class, args); 
	}

}

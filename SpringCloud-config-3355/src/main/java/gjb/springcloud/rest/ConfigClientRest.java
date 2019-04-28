package gjb.springcloud.rest;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigClientRest {

	@Value("${spring.application.name}")
	private String applicationName;

	@Value("${eureka.client.service-url.defaultZone}")
	private String eurekaServers;

	@Value("${server.port}")
	private String port;

	@RequestMapping(value = "/config")
	public String getConfig() {
		String Str = "applicationName=" + applicationName + ",eurekaServer=" + eurekaServers + ",port=" + port;
		return Str;
	}

}

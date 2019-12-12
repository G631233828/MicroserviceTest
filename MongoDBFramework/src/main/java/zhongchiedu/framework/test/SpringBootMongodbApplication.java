package zhongchiedu.framework.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages={"zhongchiedu.framework.*"})
@EnableMongoRepositories(basePackages = {"zhongchiedu.framework.*"})
public class SpringBootMongodbApplication implements CommandLineRunner {

	@Autowired
	private CustomerDao customerDao;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMongodbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}

//	public void run(String... args) throws Exception {
//		
//		List<Customer> list = customerDao.find(new Query(), Customer.class);
//		System.out.println("list:"+list);
//	}

}

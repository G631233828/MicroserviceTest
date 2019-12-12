package zhongchiedu.com.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import zhongchiedu.com.pojo.User;
import zhongchiedu.com.utils.BasicDataResult;
import zhongchiedu.framework.pagination.Pagination;

/**
 * 
 * @author fliay 修改api 根据已经有的DeptClientService接口新建实现
 *         实现FallbackFactory接口的类DeptClientServiceFallbackFactory
 *         将所有的需要进行熔断的交给了DeptClientServiceFallbackFactory来进行处理
 *
 */

//@FeignClient(value = "SYSTEMOCRE-PROVIDER")// 未使用熔断hystrix使用这个
@Component
@FeignClient(value = "SYSTEMCORE-PROVIDER"/* ,fallbackFactory = UserServiceFallbackFactory.class */)
public interface UserService{
	
	@RequestMapping(value = "/serviceUser/users", method = RequestMethod.GET)
	public Pagination<User>  list(@RequestParam(value = "pageNo", defaultValue = "1") final Integer pageNo,@RequestParam(value = "pageSize", defaultValue = "1") final Integer pageSize);
	
	@RequestMapping(value = "/serviceUser/saveOrUpdateUser", method = RequestMethod.POST)
	public void saveOrUpdateUser(@RequestBody final User user,@RequestParam("roleId") final String roleId , @RequestParam("file") final MultipartFile[] file,@RequestParam("imgPath") final String imgPath, @RequestParam("dir") final String dir, @RequestParam("oldheadImg")final String  oldheadImg);
	
	@RequestMapping(value = "/serviceUser/findUserByAccountName", method = RequestMethod.GET)
	public User findUserByAccountName(@RequestParam("accountName") final String accountName);
	
	@RequestMapping(value = "/serviceUser/findUserById", method = RequestMethod.GET)
	public User findUserById(@RequestParam("id") final String id);
	
	@RequestMapping(value = "/serviceUser/findAllUser", method = RequestMethod.GET)
	public List<User> findAllUser();
	
	@RequestMapping(value = "/serviceUser/remove", method = RequestMethod.GET)
	public BasicDataResult remove(@RequestParam("id") final String id);
	
	@RequestMapping(value = "/serviceUser/ajaxgetRepletes", method = RequestMethod.GET)
	public BasicDataResult ajaxgetRepletes(@RequestParam("accountName") final String accountName);
	
	@RequestMapping(value = "/serviceUser/userDisable", method = RequestMethod.GET)
	public BasicDataResult userDisable(@RequestParam("id") final String id);
	
	@RequestMapping(value = "/serviceUser/checkPassword", method = RequestMethod.GET)
	public BasicDataResult checkPassword(@RequestParam("id") final String id, @RequestParam("password") final String password);
	
	@RequestMapping(value = "/serviceUser/editPassword", method = RequestMethod.GET)
	public BasicDataResult editPassword(  @RequestParam("id") final String id, @RequestParam("password") final String password);
}

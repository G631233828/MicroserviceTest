package zhongchiedu.com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import zhongchiedu.com.pojo.User;
import zhongchiedu.com.serviceImpl.UserServiceImpl;
import zhongchiedu.com.utils.BasicDataResult;
import zhongchiedu.framework.pagination.Pagination;

@RestController
@RequestMapping("/serviceUser")
@Slf4j
public class UserController {


	@Autowired
	private UserServiceImpl userService;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public Pagination<User> list(@RequestParam(value = "pageNo", defaultValue = "1") final Integer pageNo, Model model,
			@RequestParam(value = "pageSize", defaultValue = "20") final Integer pageSize, HttpSession session) {
		return  this.userService.list(pageNo, pageSize);
	}

	@RequestMapping(value = "/saveOrUpdateUser", method = RequestMethod.GET)
	public void saveOrUpdateUser(@RequestBody User user,@RequestParam("roleId") String roleId , @RequestParam("file") MultipartFile[] file,@RequestParam("imgPath")String imgPath, @RequestParam("dir") String dir, @RequestParam("oldheadImg")String oldheadImg) {
		this.userService.saveOrUpdateUser(user, roleId, file, imgPath, dir, oldheadImg);
	}
	
	
	@RequestMapping(value = "/findUserByAccountName", method = RequestMethod.GET)
	public User findUserByAccountName(@RequestParam("accountName")  String accountName) {
		System.out.println("Server Account  "+accountName);
		
		return this.userService.findUserByAccountName(accountName);
	}
	
	
	@RequestMapping(value = "/findUserById", method = RequestMethod.GET)
	public User findUserById(@RequestParam("id")  String id) {
		return this.userService.findUserById(id);
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public BasicDataResult remove(@RequestParam("id") String id){
		return this.userService.remove(id);
	} 
	
	@RequestMapping(value = "/checkPassword", method = RequestMethod.GET)
	public BasicDataResult checkPassword(@RequestParam("id")   String id,@RequestParam("password")   String password) {
		return this.userService.checkPassword(id, password); 
	}
	
	@RequestMapping(value = "/ajaxgetRepletes", method = RequestMethod.GET)
	public BasicDataResult ajaxgetRepletes(@RequestParam("accountName") String accountName) {
		return this.userService.ajaxgetRepletes(accountName);
	}
	
	@RequestMapping(value = "/userDisable", method = RequestMethod.GET)
	public BasicDataResult userDisable(@RequestParam("id")  final String id) {
		return this.userService.userDisable(id);
	}
	
	
	@RequestMapping(value = "/editPassword", method = RequestMethod.GET)
	public BasicDataResult editPassword(@RequestParam("id")   String id,@RequestParam("password")  String password) {
		return this.userService.editPassword(id, password);
	}
	

}

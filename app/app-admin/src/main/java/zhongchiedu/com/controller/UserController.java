package zhongchiedu.com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import zhongchiedu.annotation.SystemControllerLog;
import zhongchiedu.com.pojo.Role;
import zhongchiedu.com.pojo.User;
import zhongchiedu.com.service.RoleService;
import zhongchiedu.com.service.UserService;
import zhongchiedu.com.utils.BasicDataResult;
import zhongchiedu.framework.pagination.Pagination;

@Controller
@RequestMapping("/admin")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@RequiresPermissions(value = "admin:user:list")
	@SystemControllerLog(description = "查询所有用户")
	public String list(@RequestParam(value = "pageNo", defaultValue = "1") final Integer pageNo, Model model,
			@RequestParam(value = "pageSize", defaultValue = "20") final Integer pageSize, HttpSession session) {
		// 分页查询数据
		try {
			Pagination<User> pagination = this.userService.list(pageNo, pageSize);
			model.addAttribute("pageList", pagination);
			
		}catch (Exception e) {
			log.info("查询用户信息失败{}",e.toString());
		}
		return "admin/user/list";
	}
	
	/**
	 * 跳转到添加页面
	 */
	@GetMapping("/user")
	@RequiresPermissions(value = "admin:user:add")
	public String addUserPage(Model model) {
		List<Role> list = this.roleService.findAllRoleByisDisable();
		model.addAttribute("roleList", list);
		return "admin/user/add";
	}
	
	@Value("${upload-imgpath}")
	private String imgPath;
	@Value("${upload-dir}")
	private String dir;
	
	@PostMapping("/user")
	@RequiresPermissions(value = "admin:user:add")
	@SystemControllerLog(description = "添加用户")
	public String addUser(HttpServletRequest request, @ModelAttribute("user") User user,
			@RequestParam(value = "roleId", defaultValue = "") String roleId, @RequestParam("file") MultipartFile[] file,
			@RequestParam(value = "oldheadImg", defaultValue = "") String oldheadImg) {
		this.userService.saveOrUpdateUser(user, roleId,file,imgPath,dir,oldheadImg);
		return "redirect:/admin/users";
	}
	
	/**
	 * 修改用户
	 * @param request
	 * @param user
	 * @param roleId
	 * @param file
	 * @param oldheadImg
	 * @return
	 */
	@PutMapping("/user")
	@RequiresPermissions(value = "admin:user:edit")
	@SystemControllerLog(description = "修改用户")
	public String editUser(HttpServletRequest request, @ModelAttribute("user") User user,
			@RequestParam(value = "roleId", defaultValue = "") String roleId, @RequestParam("file")MultipartFile[] file,
			@RequestParam(value = "oldheadImg", defaultValue = "") String oldheadImg) {
		this.userService.saveOrUpdateUser(user, roleId,file,imgPath,dir,oldheadImg);
		return "redirect:/admin/users";
	}	
	
	
	/**
	 * 跳转到编辑界面
	 * 
	 * @return
	 */
	@GetMapping("/user/{id}")
	@RequiresPermissions(value = "admin:user:edit")
	@SystemControllerLog(description = "编辑用户")
	public String toeditPage(@PathVariable String id, Model model) {
		List<Role> list = this.roleService.findAllRoleByisDisable();
		model.addAttribute("roleList", list);
		User user = this.userService.findUserById(id);
		model.addAttribute("user", user);
		return "admin/user/add";

	}
	
	@DeleteMapping("/user/{id}")
	@RequiresPermissions(value = "admin:user:delete")
	@SystemControllerLog(description = "删除用户")
	public String delete(@PathVariable String id) {
		 this.userService.remove(id);
		return "redirect:/admin/users";
	}
	
	
	@RequestMapping(value = "/user/checkPassword", method = RequestMethod.POST)
	public BasicDataResult checkPassword(@RequestParam(value = "id", defaultValue = "") String id,
			@RequestParam(value = "password", defaultValue = "") String password) {
		return this.userService.checkPassword(id, password);
	}
	
	/**
	 * 通过ajax获取是否存在重复账号的信息
	 * 
	 * @param printWriter
	 * @param session
	 * @param response
	 */
	@RequestMapping(value = "/user/ajaxgetRepletes", method = RequestMethod.POST)
	public BasicDataResult ajaxgetRepletes(@RequestParam(value = "accountName", defaultValue = "") String accountName) {
		return this.userService.ajaxgetRepletes(accountName);
	}
	
	
	@RequestMapping(value = "/user/disable", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public BasicDataResult userDisable(@RequestParam(value = "id", defaultValue = "") String id) {
		return this.userService.userDisable(id);

	}
	
	@RequestMapping(value = "/user/editPassword", method = RequestMethod.POST)
	public BasicDataResult editPassword(@RequestParam(value = "id", defaultValue = "") String id,
			@RequestParam(value = "password2", defaultValue = "") String password2) {
		
		return this.userService.editPassword(id, password2);
	}
	
	

}

package zhongchiedu.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import zhongchiedu.com.pojo.Role;
import zhongchiedu.com.service.RoleService;
import zhongchiedu.com.utils.BasicDataResult;
import zhongchiedu.framework.pagination.Pagination;

@RestController
@RequestMapping("/serviceRole")
@Slf4j
public class RoleController {


	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value = "/findAllRoleByisDisable", method = RequestMethod.GET)
	public List<Role> findAllRoleByisDisable(){
		return this.roleService.findAllRoleByisDisable();
	}
	
	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	public Pagination<Role>  list(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize){
		return this.roleService.list(pageNo, pageSize);
	}
	
	
	@RequestMapping(value = "/saveOrUpdateRole", method = RequestMethod.POST)
	public void saveOrUpdateRole(@RequestBody Role role) {
		this.roleService.saveOrUpdateRole(role);
	}
	
	@RequestMapping(value = "/author", method = RequestMethod.GET)
	public BasicDataResult author(@RequestParam("id")  String id,@RequestParam("checkallPermission")  String checkallPermission) {
		return this.roleService.author(id, checkallPermission);
	}
	
	@RequestMapping(value = "/getAuthor", method = RequestMethod.GET)
	public BasicDataResult getAuthor(@RequestParam("id")  String id) {
		return this.roleService.getAuthor(id);
	}
	
	@RequestMapping(value = "/ajaxgetRepletes", method = RequestMethod.GET)
	public BasicDataResult ajaxgetRepletes(@RequestParam("roleName")  String roleName) {
		return this.roleService.ajaxgetRepletes(roleName);
	}
	
	@RequestMapping(value = "/roleDisable", method = RequestMethod.GET)
	public BasicDataResult roleDisable(@RequestParam("id")  String id) {
		return this.roleService.roleDisable(id);
	}
	
	@RequestMapping(value = "/findRoleById", method = RequestMethod.GET)
	public Role findRoleById(@RequestParam("id") final  String id) {
		return this.roleService.findRoleById(id);
	}
	
	
	
}

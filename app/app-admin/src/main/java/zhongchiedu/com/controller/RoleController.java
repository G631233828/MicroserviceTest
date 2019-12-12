package zhongchiedu.com.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
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

import lombok.extern.slf4j.Slf4j;
import zhongchiedu.annotation.SystemControllerLog;
import zhongchiedu.com.pojo.Resource;
import zhongchiedu.com.pojo.Role;
import zhongchiedu.com.service.ResourceService;
import zhongchiedu.com.service.RoleService;
import zhongchiedu.com.utils.BasicDataResult;
import zhongchiedu.framework.pagination.Pagination;
@Controller
@Slf4j
@RequestMapping("/admin")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private ResourceService resourceService;
 
	@GetMapping("/roles")
    @RequiresPermissions(value = "admin:role:list")
	@SystemControllerLog(description = "查询所有角色")
	public String list(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo, Model model,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
		System.out.println("-----------------------");
		// 分页查询数据
		try {
			Pagination<Role> pagination = this.roleService.list(pageNo, pageSize);
			model.addAttribute("pageList", pagination);

		} catch (Exception e) {
			log.info("查询用户信息失败{}", e.toString());
		}
		List<Resource> listResource = this.resourceService.findAllResourceByUsed();

		model.addAttribute("listResource", listResource);
		
		return "admin/role/list";
	}
	
	/**
	 * 跳转到添加页面
	 */
	@GetMapping("/role")
	public String addRolePage() {
		return "admin/role/add";
	}
	
	@PostMapping("/role")
	@RequiresPermissions(value = "admin:role:add")
	@SystemControllerLog(description = "添加角色")
	public String addRole(@ModelAttribute("role") Role role) {
		this.roleService.saveOrUpdateRole(role);
		return "redirect:/admin/roles";
	}
	
	@PutMapping("/role")
	@RequiresPermissions(value = "admin:role:edit")
	@SystemControllerLog(description = "修改角色")
	public String editRole(@ModelAttribute("role") Role role) {
		this.roleService.saveOrUpdateRole(role);
		return "redirect:/admin/roles";
	}
	
	

	/**
	 * 跳转到编辑界面
	 * 
	 * @return
	 */
	@GetMapping("/role/{id}")
	public String toeditPage(@PathVariable String id, Model model) {
		Role role = this.roleService.findRoleById(id);
		model.addAttribute("role", role);
		return "admin/role/add";

	}
	
	
	@DeleteMapping("/role/{id}")
	@RequiresPermissions(value = "admin:role:delete")
	@SystemControllerLog(description = "删除角色")
	public String delete(@PathVariable String id) {
		 this.roleService.remove(id);
		return "redirect:/admin/roles";
	}	
	
	@RequestMapping("/author")
	@RequiresPermissions(value = "admin:role:author")
	public BasicDataResult author(@RequestParam(defaultValue = "", value = "id") String id,
			@RequestParam(value = "checkallPermission", defaultValue = "") String checkallPermission) {
		BasicDataResult result = this.roleService.author(id,checkallPermission);
		return result;
	}
	
	
	@RequestMapping("/getAuthor")
	@RequiresPermissions(value = "admin:role:author")
	public BasicDataResult getAuthor(@RequestParam(defaultValue = "", value = "id") String id){
		BasicDataResult result = this.roleService.getAuthor(id);
		return result;
	}

	@RequestMapping(value = "/role/ajaxgetRepletes", method = RequestMethod.POST)
	public BasicDataResult ajaxgetRepletes(@RequestParam(value = "roleName", defaultValue = "") String roleName) {
		return	this.roleService.ajaxgetRepletes(roleName);
		
	}
	
	
	@RequestMapping(value = "/role/disable", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public BasicDataResult resourceDisable(@RequestParam(value = "id", defaultValue = "") String id) {
	return this.roleService.roleDisable(id);
		
	}
	
	
}

package zhongchiedu.com.service;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import zhongchiedu.com.pojo.Role;
import zhongchiedu.com.utils.BasicDataResult;
import zhongchiedu.framework.pagination.Pagination;

@FeignClient(value = "SYSTEMCORE-PROVIDER")
public interface RoleService{
	
	@RequestMapping(value = "/serviceRole/roles", method = RequestMethod.GET)
	public Pagination<Role>  list(@RequestParam(value = "pageNo", defaultValue = "1") final Integer pageNo,@RequestParam(value = "pageSize", defaultValue = "1") final Integer pageSize);

	@RequestMapping(value = "/serviceRole/findRoleById", method = RequestMethod.GET)
	public Role findRoleById(@RequestParam("id") final  String id);
	
	@RequestMapping(value = "/serviceRole/findRoleByRoleName", method = RequestMethod.GET)
	public Role findRoleByRoleName(@RequestParam("roleName") final  String roleName);
	
	@RequestMapping(value = "/serviceRole/findAllRoleByisDisable", method = RequestMethod.GET)
	public List<Role> findAllRoleByisDisable();
	
	@RequestMapping(value = "/serviceRole/findAllRole", method = RequestMethod.GET)
	public List<Role> findAllRole();
	
	@RequestMapping(value = "/serviceRole/saveOrUpdateRole", method = RequestMethod.POST)
	public void saveOrUpdateRole(@RequestBody final  Role role);
	
	@RequestMapping(value = "/serviceRole/author", method = RequestMethod.GET)
	public BasicDataResult author(@RequestParam("roleName") final String id,@RequestParam("roleName") final String checkallPermission);
	
	@RequestMapping(value = "/serviceRole/getAuthor", method = RequestMethod.GET)
	public BasicDataResult getAuthor(@RequestParam("roleName") final String id);
	
	@RequestMapping(value = "/serviceRole/ajaxgetRepletes", method = RequestMethod.GET)
	public BasicDataResult ajaxgetRepletes(@RequestParam("roleName") final  String roleName);
	
	@RequestMapping(value = "/serviceRole/roleDisable", method = RequestMethod.GET)
	public BasicDataResult roleDisable(@RequestParam("roleName") final String id);
	
	@RequestMapping(value = "/serviceRole/remove", method = RequestMethod.GET)
	public BasicDataResult remove(@RequestParam("roleName") final String id);
	
}

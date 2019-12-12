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
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import zhongchiedu.annotation.SystemControllerLog;
import zhongchiedu.com.pojo.Resource;
import zhongchiedu.com.service.ResourceService;
import zhongchiedu.com.utils.BasicDataResult;
import zhongchiedu.com.utils.Common;
import zhongchiedu.framework.pagination.Pagination;

@Controller
@RequestMapping("/admin")
@Slf4j
public class ResourceController {
	
	@Autowired
	private ResourceService resourceService;
	
	
	@GetMapping("/resources")
	@RequiresPermissions(value = "admin:resource:list")
	@SystemControllerLog(description = "查询所有资源")
	public String list(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo, Model model,
			@RequestParam(value = "pageSize", defaultValue = "9999") Integer pageSize) {
		// 分页查询数据
		try {
			Pagination<Resource> pagination = this.resourceService.list(pageNo, pageSize);
			model.addAttribute("pageList", pagination);

		} catch (Exception e) {
			log.info("查询资源信息失败{}", e.toString());
		}
			
		return "admin/resource/list";
	}
	@ResponseBody
	@GetMapping("/resourcestest")
	public List<Resource>  test(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo, Model model,
			@RequestParam(value = "pageSize", defaultValue = "9999") Integer pageSize) {
		List<Resource> lists = this.resourceService.findAllResource();
		
		return lists;
	}

	
	/**
	 * 跳转到添加页面
	 */
	@GetMapping("/resource")
	@RequiresPermissions(value = "admin:resource:add")
	public String addUserPage(Model model) {
		//获取所有的启用的资源目录
		List<Resource> list = this.resourceService.findResourcesByType(0);
		model.addAttribute("resList", list);
		return "admin/resource/add";
	}
	

	@PostMapping("/resource")
	@RequiresPermissions(value = "admin:resource:add")
	@SystemControllerLog(description = "添加资源")
	public String addResource(
			@ModelAttribute("resource") Resource resource,
			@RequestParam(value = "parentSubId", defaultValue = "") String parentSubId) {
		if(Common.isNotEmpty(parentSubId)){
			resource.setParentId(parentSubId);
		}
		if(resource.getType() == 0){
			resource.setParentId("0");
		}
		this.resourceService.saveOrUpdateUser(resource);
		return "redirect:/admin/resources";
	}
	
	@PutMapping("/resource")
	@RequiresPermissions(value = "admin:resource:edit")
	@SystemControllerLog(description = "修改资源")
	public String editResource(
			@ModelAttribute("resource") Resource resource,
			@RequestParam(value = "parentSubId", defaultValue = "") String parentSubId) {
		if(Common.isNotEmpty(parentSubId)){
			resource.setParentId(parentSubId);
		}
		if(resource.getType() == 0){
			resource.setParentId("0");
		}
		this.resourceService.saveOrUpdateUser(resource);
		return "redirect:/admin/resources";
	}
	
	
	/**
	 * 根据选择的目录获取菜单
	 * @param parentId
	 * @return
	 */
	@RequestMapping(value="/getparentmenu",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public BasicDataResult getparentmenu(@RequestParam(value = "parentId", defaultValue = "") String parentId){
		List<Resource> list = this.resourceService.findResourceMenu(parentId);
		return list!=null?BasicDataResult.build(200, "success",list):BasicDataResult.build(400, "error",null);
	}
	
	
	
	/**
	 * 跳转到编辑界面
	 * 
	 * @return
	 */
	@GetMapping("/resource/{id}")
	@RequiresPermissions(value = "admin:resource:edit")
	public String toeditPage(@PathVariable String id, Model model) {
		
		Resource resource = this.resourceService.findResourceById(id);
		//resource获取到之后需要查看资源
		if(resource!=null){
			int type = resource.getType();
			//如果type是1需要获取所有目录
			List resList = this.resourceService.findResourcesByType(0);
			List resmenu = null;
			//如果type是2需要获取所有目录与菜单
			if(type == 2){
				resmenu = this.resourceService.findResourceMenuByid(resource.getParentId());
				model.addAttribute("resourssubmenuId", resource.getParentId());
				Resource ressup = this.resourceService.findResourceById(resource.getParentId());
				model.addAttribute("resourssupmenuId", ressup.getParentId());
			}
			if(type == 1){
				model.addAttribute("resourssupmenuId", resource.getParentId());
			}
			model.addAttribute("resList", resList);
			model.addAttribute("resmenu", resmenu);
		}
		model.addAttribute("resource", resource);
		

		return "admin/resource/add";

	}
	
	@DeleteMapping("/resource/{id}")
	@RequiresPermissions(value = "admin:resource:delete")
	@SystemControllerLog(description = "删除资源")
	public String delete(@PathVariable String id){
		this.resourceService.remove(id);
		return "redirect:/admin/resources";
	}
	
	
	@RequestMapping(value = "/resource/disable", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public BasicDataResult resourceDisable(@RequestParam(value = "id", defaultValue = "") String id) {
	return this.resourceService.resourceDisable(id);
		
	}
	
	
	
	
	
	
	
	

}

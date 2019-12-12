package zhongchiedu.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import zhongchiedu.com.pojo.Resource;
import zhongchiedu.com.service.ResourceService;
import zhongchiedu.com.utils.BasicDataResult;
import zhongchiedu.framework.pagination.Pagination;



@RestController
@RequestMapping("/serviceResouce")
@Slf4j
public class ResourceController {

	@Autowired
	private ResourceService resourceService;
	
	
	@RequestMapping(value = "/resources", method = RequestMethod.GET)
	public Pagination<Resource>  list(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize){
		return this.resourceService.list(pageNo, pageSize);
	}
	
	@RequestMapping(value = "/findlistResource", method = RequestMethod.GET)
	public List<Resource> findlistResource(@RequestParam("userId") final String userId){
		return this.resourceService.findlistResource(userId);
	}
	
	@RequestMapping(value = "/findResourceById", method = RequestMethod.GET)
	public Resource findResourceById(@RequestParam("id") final String id) {
		return this.resourceService.findResourceById(id);
	}
	
	@RequestMapping(value = "/findResourceByResUrl", method = RequestMethod.GET)
	public Resource findResourceByResUrl(@RequestParam("url") final String url) {
		return this.resourceService.findResourceByResUrl(url);
	}
	
	@RequestMapping(value = "/findParentResource", method = RequestMethod.GET)
	public List<Resource> findParentResource(){
		return this.resourceService.findParentResource();
	}
	
	@RequestMapping(value = "/findResourceMenu", method = RequestMethod.GET)
	public List<Resource> findResourceMenu(@RequestParam("parentId") final String parentId){
		return this.resourceService.findResourceMenu(parentId);
	}
	
	
	@RequestMapping(value = "/findResourceMenuByid", method = RequestMethod.GET)
	public List<Resource> findResourceMenuByid(@RequestParam("parentId") final String parentId){
		return this.resourceService.findResourceMenuByid(parentId);
	}
	
	@RequestMapping(value = "/findAllResource", method = RequestMethod.GET)
	public List<Resource> findAllResource(){
		return this.resourceService.findAllResource();
	}
	
	@RequestMapping(value = "/findAllResourceByUsed", method = RequestMethod.GET)
	public List<Resource> findAllResourceByUsed(){
		return this.resourceService.findAllResourceByUsed();
	}
	
	@RequestMapping(value = "/findResourcesByType", method = RequestMethod.GET)
	public List<Resource> findResourcesByType(@RequestParam("type") final int type){
		return this.resourceService.findResourcesByType(type);
	}
	
	@RequestMapping(value = "/saveOrUpdateUser", method = RequestMethod.POST)
	public void saveOrUpdateUser(@RequestBody final Resource rs) {
		 this.resourceService.saveOrUpdateUser(rs);
	}
	
	
	@RequestMapping(value = "/resourceDisable", method = RequestMethod.GET)
	public BasicDataResult resourceDisable(@RequestParam("id") final String id) {
		return this.resourceService.resourceDisable(id);
	}
	
	
	
	
	
	
	
	
	
}

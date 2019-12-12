package zhongchiedu.com.service;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import zhongchiedu.com.pojo.Resource;
import zhongchiedu.com.utils.BasicDataResult;
import zhongchiedu.framework.pagination.Pagination;


@FeignClient(value = "SYSTEMCORE-PROVIDER")
public interface ResourceService{

	
	@RequestMapping(value = "/serviceResouce/resources", method = RequestMethod.GET)
	public Pagination<Resource>  list(@RequestParam(value = "pageNo", defaultValue = "1") final Integer pageNo,@RequestParam(value = "pageSize", defaultValue = "1") final Integer pageSize);
	
	@RequestMapping(value = "/serviceResouce/findlistResource", method = RequestMethod.GET)
	public List<Resource> findlistResource(@RequestParam("userId") final String userId);
	
	@RequestMapping(value = "/serviceResouce/findResourceById", method = RequestMethod.GET)
	public Resource findResourceById(@RequestParam("id") final String id);
	
	@RequestMapping(value = "/serviceResouce/findResourceByResUrl", method = RequestMethod.GET)
	public Resource findResourceByResUrl(@RequestParam("url") final String url);
	
	@RequestMapping(value = "/serviceResouce/findParentResource", method = RequestMethod.GET)
	public List<Resource> findParentResource();
	
	@RequestMapping(value = "/serviceResouce/findResourceMenu", method = RequestMethod.GET)
	public List<Resource> findResourceMenu(@RequestParam("parentId") final String parentId);
	
	@RequestMapping(value = "/serviceResouce/findResourceMenuByid", method = RequestMethod.GET)
	public List<Resource> findResourceMenuByid(@RequestParam("parentId") final String parentId);
	
	@RequestMapping(value = "/serviceResouce/findAllResource", method = RequestMethod.GET)
	public List<Resource> findAllResource();
	
	@RequestMapping(value = "/serviceResouce/findAllResourceByUsed", method = RequestMethod.GET)
	public List<Resource> findAllResourceByUsed();
	
	@RequestMapping(value = "/serviceResouce/findResourcesByType", method = RequestMethod.GET)
	public List<Resource> findResourcesByType(@RequestParam("type") final int type);
	
	@RequestMapping(value = "/serviceResouce/saveOrUpdateUser", method = RequestMethod.POST)
	public void saveOrUpdateUser(@RequestBody final Resource rs);
	
	@RequestMapping(value = "/serviceResouce/resourceDisable", method = RequestMethod.GET)
	public BasicDataResult resourceDisable(@RequestParam("id") final String id);
	
	@RequestMapping(value = "/serviceResouce/remove", method = RequestMethod.GET)
	public BasicDataResult remove(@RequestParam("id") final String id);
	
}
package zhongchiedu.com.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zhongchiedu.com.pojo.Log;
import zhongchiedu.framework.pagination.Pagination;

@FeignClient(value = "SYSTEMCORE-PROVIDER")
public interface LogService {
	
	@RequestMapping(value = "/serviceResouce/findAllLog", method = RequestMethod.GET)
	public Pagination<Log> findAllLog(int pageNo,int pageSize,String type);
	
	public Query findLogByQuery(String type);
}

package zhongchiedu.com.compent;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import zhongchiedu.com.pojo.Role;
import zhongchiedu.com.pojo.User;
import zhongchiedu.com.service.RoleService;
import zhongchiedu.com.utils.Common;
import zhongchiedu.com.utils.Contents;

@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {

	private static final Logger log = LoggerFactory.getLogger(LoginHandlerInterceptor.class);
	@Autowired
	private RoleService roleService;
	
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String urlid = request.getParameter("urlid");
		if(Common.isNotEmpty(urlid)){
			session.setAttribute(Contents.MENU_ID, urlid);
		}
		
		//修改权限之后需要去刷新用户的权限
		User user = (User) session.getAttribute(Contents.USER_SESSION);
		if(Common.isNotEmpty(user)){
			//获取session中所属角色的id
			Role sessionRole = user.getRole();
			System.out.println(sessionRole.getId());
			System.out.println(sessionRole.getId());
			System.out.println(sessionRole.getId());
			//通过roleID 查找数据库中的role
			Role role = this.roleService.findRoleById(sessionRole.getId());
			System.out.println("role"+role);
			if(sessionRole.getVersion() == role.getVersion()){
				return true;
			}else{
				SecurityUtils.getSubject().logout();
			}
		}
		
		return true;


	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 通过urlid查询父目录以及urlid所在的资源
		
	}

}

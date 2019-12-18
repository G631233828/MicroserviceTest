package zhongchiedu.com.serviceImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import zhongchiedu.com.pojo.User;
import zhongchiedu.com.service.UserService;
import zhongchiedu.com.utils.BasicDataResult;
import zhongchiedu.com.utils.Common;
import zhongchiedu.framework.pagination.Pagination;
import zhongchiedu.framework.service.GeneralServiceImpl;

@Service
@Slf4j
public class UserServiceImpl extends GeneralServiceImpl<User> implements UserService {


	@Autowired
	private RoleServiceImpl roleService;
	
	@Autowired
	private MultiMediaServiceImpl multiMediaSerice;

	/**
	 * 用户添加或修改
	 * 
	 * @param user
	 * @param roleId
	 * @return
	 */
	public boolean saveOrUpdateUser( User user,  String roleId ,String imgPath,String dir,String oldheadImg) {
	//public boolean saveOrUpdateUser(User user ) {
		System.out.println("user:"+user);
		System.out.println("user:"+user);
		System.out.println("user:"+user);
		System.out.println("user:"+user);
		System.out.println("user:"+user);
	
		return true;
		
		
		//		User getUser = null;
//		List<MultiMedia> userHead = null; 
//			userHead = this.multiMediaSerice.uploadPictures(file, dir, imgPath, "USER",105,105);
//	
//		if(Common.isNotEmpty(user.getId())){
//			getUser = this.findOneById(user.getId(), User.class);
//			if(Common.isNotEmpty(getUser)){
//				user.setPhotograph(Common.isNotEmpty(oldheadImg)?getUser.getPhotograph():null);
//				user.setAccountName(getUser.getAccountName());
//			}
//		}
//		if(userHead.size()>0){
//			user.setPhotograph(userHead.get(0));
//		}
//		Role role = this.roleService.findRoleById(roleId);
//		user.setRole(role != null ? role : null);
//		if(Common.isNotEmpty(getUser)){
//			BeanUtils.copyProperties(user, getUser);
//			this.save(user);
//		}else{
//			this.insert(user);
//		}
		
	}

	/**
	 * 根据用户帐号查询用户信息
	 * 
	 * @param accountName
	 * @return
	 * @throws Exception
	 */
	public User findUserByAccountName(String accountName) {
		Query query = new Query();
		query.addCriteria(Criteria.where("accountName").is(accountName));
		User user = this.findOneByQuery(query, User.class);
		
		return user!=null?user:null;
	}

	/**
	 * 根据用户id 使用状态查询用户
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public User findUserById(String id) {
		System.out.println("server id"+id);
		
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		//query.addCriteria(Criteria.where("isDisable").is(isDisable));
		query.addCriteria(Criteria.where("isDelete").is(false));
		// User user= this.userDao.findOneById(id);
		User user = this.findOneByQuery(query, User.class);
		return user!=null?user:null;
		
	}

	public List<User> findAllUser() {
		List<User> listuser = this.find(new Query(), User.class);
		if (listuser != null)
			return listuser;
		else
			return null;
	}

	//查询重复帐号
	public BasicDataResult ajaxgetRepletes(String accountName) {
		if(Common.isNotEmpty(accountName)){
			User user = this.findUserByAccountName(accountName);
			
			if(user!=null){
				return BasicDataResult.build(206,"当前账号已经被注册，请重新输入", null);
			}
			
			return BasicDataResult.ok();
		}
		
		return BasicDataResult.build(400,"未能获取到请求的信息", null);
		
	}

	public BasicDataResult userDisable(String id) {
		if(Common.isEmpty(id)){
			return BasicDataResult.build(400, "无法禁用，请求出现问题，请刷新界面!", null);
		}
		User user = this.findUserById(id);
		if(user == null){
			return BasicDataResult.build(400, "无法获取到用户信息，该用户可能已经被删除", null);
		}
		
		user.setIsDisable(user.getIsDisable().equals(true)?false:true);
		this.save(user);
		
		return BasicDataResult.build(200, user.getIsDisable().equals(true)?"用户禁用成功":"用户启用成功",user.getIsDisable());
		
	}
	
	
	public BasicDataResult checkPassword(String id,String password){
		
		if(Common.isEmpty(id)){
			return BasicDataResult.build(400, "发生未知异常，请联系管理员！", null);
		}
		if(Common.isEmpty(password)){
			return BasicDataResult.build(400, "请输入旧密码", null);
		}
		User user = this.findOneById(id, User.class);
		if(Common.isEmpty(user)){
			return BasicDataResult.build(400, "未能获取到数据，请刷新后再试", null);
		}
		if(user.getPassWord().equals(password)){
			return BasicDataResult.build(200, "密码正确", null);
		}
		return BasicDataResult.build(400, "发生未知异常，请联系管理员！", null);
		
	}
	
	public BasicDataResult editPassword(String id,String password){
		if(Common.isEmpty(id)){
			return BasicDataResult.build(400, "发生未知异常，请联系管理员！", null);
		}
		if(Common.isEmpty(password)){
			return BasicDataResult.build(400, "新密码不能为空", null);
		}
		User user = this.findOneById(id, User.class);
		if(Common.isEmpty(user)){
			return BasicDataResult.build(400, "未能获取到数据，请刷新后再试", null);
		}
		user.setPassWord(password);
		this.save(user);
		return BasicDataResult.build(200, "密码修改成功", null);
		
	}
	@Override
	public Pagination<User> list(Integer pageNo, Integer pageSize) {
		// 分页查询数据
		Pagination<User> pagination = null;
		try {
			pagination = this.findPaginationByQuery(new Query(), pageNo, pageSize, User.class);
			if (pagination == null)
				pagination = new Pagination<User>();

		} catch (Exception e) {
			e.printStackTrace();
		}

		
		return pagination;
	}

	@Override
	public BasicDataResult remove(String id) {
		
		try {
			String[] strids = id.split(",");
			for (String delids : strids) {
				log.info("删除用户---》" + delids);
				User rm = this.findOneById(delids, User.class);
				this.remove(rm);// 删除某个id
			}
		}catch (Exception e) {
			log.info("删除数据失败{}"+e.toString());
			return BasicDataResult.error("删除数据失败");
		}
		return BasicDataResult.ok();
	}


	@Override
	public String upload(MultipartFile file) {
		System.out.println("service api  file " + file.getOriginalFilename());
		
		// TODO Auto-generated method stub
		return file.getOriginalFilename();
	}

 
}

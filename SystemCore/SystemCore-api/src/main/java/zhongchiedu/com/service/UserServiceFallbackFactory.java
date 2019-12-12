//package zhongchiedu.com.service;
//
//import java.util.List;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//import feign.hystrix.FallbackFactory;
//import zhongchiedu.com.pojo.User;
//import zhongchiedu.framework.pagination.Pagination;
//import zhongchiedu.utils.BasicDataResult;
//
//@Component
//public class UserServiceFallbackFactory implements FallbackFactory<UserService> {
//
//	@Override
//	public UserService create(Throwable cause) {
//		
//		return new UserService() {
//
//			@Override
//			public void saveOrUpdateUser(User user, String roleId,  String imgPath, String dir,
//					String oldheadImg) {
//				
//			}
//
//			@Override
//			public User findUserByAccountName(String accountName) {
//				// TODO Auto-generated method stub
//				return null;
//			}
//
//			@Override
//			public User findUserById(String id) {
//				// TODO Auto-generated method stub
//				return null;
//			}
//
//			@Override
//			public List<User> findAllUser() {
//				// TODO Auto-generated method stub
//				return null;
//			}
//
//			@Override
//			public BasicDataResult ajaxgetRepletes(String accountName) {
//				// TODO Auto-generated method stub
//				return null;
//			}
//
//			@Override
//			public BasicDataResult userDisable(String id) {
//				// TODO Auto-generated method stub
//				return null;
//			}
//
//			@Override
//			public BasicDataResult checkPassword(String id, String password) {
//				// TODO Auto-generated method stub
//				return null;
//			}
//
//			@Override
//			public BasicDataResult editPassword(String id, String password) {
//				// TODO Auto-generated method stub
//				return null;
//			}
//
//			@Override
//			public Pagination<User> list(Integer pageNo, Integer pageSize) {
//				// TODO Auto-generated method stub
//				return null;
//			}
//			
//			
//			
//		};
//		
//	}
//
//}

package zhongchiedu.com.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.multipart.MultipartFile;

import zhongchiedu.com.pojo.MultiMedia;

@FeignClient(value = "SYSTEMCORE-PROVIDER")
public interface MultiMediaService {
	
	public List<MultiMedia> uploadPictures(MultipartFile[] file,String dir,String path,String belong,int width,int height);
	
	
	public MultiMedia uploadVideo(MultipartFile m,String dir,String path,String belong);
	
}

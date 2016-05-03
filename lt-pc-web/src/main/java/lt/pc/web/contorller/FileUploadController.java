package lt.pc.web.contorller;

import gzlazypack.common.util.DateUtil;
import gzlazypack.common.util.PropertiesUtil;
import gzlazypack.common.util.UUIDGenerator;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;




import com.alibaba.fastjson.JSON;


@Controller
@Component
@RequestMapping(value = "/file")
public class FileUploadController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);

//    @Value("${imagePath}")
    @SuppressWarnings("unused")
	private String imagePath;


    @RequestMapping(value = {"upload"})
	@ResponseBody
	public String upload(MultipartHttpServletRequest request,HttpServletResponse response) {
		try {
			String separator = "/";
			//文件上传路径
			String uploadDir = PropertiesUtil.getProperiesValue("imagePath", "/system.properties");
			
			String imageUrl=PropertiesUtil.getProperiesValue("imageContextPath", "/system.properties");
			//String uploadDir = /*PropertiesUtil.getProperiesValue("imagePath", "/system.properties")*/request.getSession().getServletContext().getRealPath("/")+ "resources/images" + separator;
            String fileFolder = DateUtil.formatDateTime(new Date(), "yyyyMMdd");
            uploadDir = uploadDir + fileFolder + separator;
			File dirPath = new File(uploadDir);
			if (!dirPath.exists()) {
				dirPath.mkdirs();
			}
			
			@SuppressWarnings("rawtypes")
			Iterator item = request.getFileNames();
			
			if (item.hasNext()) {
				String fileName = (String) item.next();
				MultipartFile file = request.getFile(fileName);
				byte[] bytes = file.getBytes();
				//文件后缀
				String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
				//文件名
				String prefix = UUIDGenerator.getUUID();
				String newFileName = prefix + suffix;
				
				File uploadedFile = new File(uploadDir + newFileName);
				FileCopyUtils.copy(bytes, uploadedFile);
				
				for (int i = 1; i <= 3; i++){
					int w = 100*i;
					
					String name = prefix + "_" + w + suffix;
					
					String outPath = uploadDir + name;
					System.out.println(outPath);
//					ImageUtils.comparess(uploadedFile, outPath, w, w);
				}
				
				//图片url
				String url = imageUrl+ fileFolder + separator + newFileName;
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("url", url);
				map.put("msg", "上传成功");
				map.put("success", true);
				
				return JSON.toJSONString(map);
			}
		} catch (Exception e) {
			LOGGER.error("图片上传错误");
			e.printStackTrace();
		}
		return "";
	}


	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public String uploadFile(HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file) {
		try {
			String uploadDir = PropertiesUtil.getProperiesValue("imagePath", "/system.properties");
			byte[] bytes = file.getBytes();
			File dirPath = new File(uploadDir);
			if (!dirPath.exists()) {
				dirPath.mkdirs();
			}
			String newFileName = UUIDGenerator.getUUID() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			File uploadedFile = new File(uploadDir + "/" + newFileName);
			FileCopyUtils.copy(bytes, uploadedFile);
			String msg = uploadDir + "/" + newFileName;
			return msg;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

}

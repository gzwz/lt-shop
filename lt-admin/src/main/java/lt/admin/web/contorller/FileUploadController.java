package lt.admin.web.contorller;

import gzlazypack.common.util.DateUtil;
import gzlazypack.common.util.PropertiesUtil;
import gzlazypack.common.util.UUIDGenerator;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;






import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;











import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;

/**
 *
 */

@Controller
@Component
@RequestMapping(value = "/file")
public class FileUploadController extends BaseController {



    /*@RequestMapping(value = {"/upload"})
	@ResponseBody
	public String upload(MultipartHttpServletRequest request,HttpServletResponse response) {
		try {
			String separator = "/";
			//文件上传路径
			String uploadDir = PropertiesUtil.getProperiesValue("imagePath", "/system.properties");
			
			String imageUrl=PropertiesUtil.getProperiesValue("imageContextPath", "/system.properties");
			String uploadDir = request.getSession().getServletContext().getRealPath("/")+ "resources/images" + separator;
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
				String url =  fileFolder + separator + newFileName;
				
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
    */
    
    
    @RequestMapping(value = {"/upload"})
  	@ResponseBody
    public String imageUpload(HttpServletRequest request) throws FileUploadException, IOException{
		List<String> fileUrlList = new ArrayList<>();
		Map<Object, Object> prop = PropertiesUtil.getAllProperiesEntryMap("/system.properties");
		String uploadDir = (String) prop.get("imagePath");
		 String fileFolder = DateUtil.formatDateTime(new Date(), "yyyyMMdd")+"/";
		String imageUrl = "";
		String[] fileType = { ".gif", ".png", ".jpg", ".jpeg", ".bmp" };

		// 保存目录路径
		File dirPath = new File(uploadDir+fileFolder);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		Preconditions.checkArgument(isMultipart, "【图片上传异常】");

		DiskFileItemFactory dff = new DiskFileItemFactory();
		dff.setRepository(new File(uploadDir+fileFolder));
		ServletFileUpload sfu = new ServletFileUpload(dff);
		sfu.setSizeMax(10000 * 1024);
		sfu.setHeaderEncoding("utf-8");
		FileItemIterator fii = sfu.getItemIterator(request);
		while (fii.hasNext()) {
			FileItemStream fis = fii.next();
			if (!fis.isFormField()) {
				// 源文件名称
				String originalName = fis.getName()
						.substring(fis.getName().lastIndexOf(System.getProperty("file.separator")) + 1);
				// 新文件名称
				String newFileName = UUID.randomUUID() + originalName.substring(originalName.lastIndexOf("."));
				// 判断格式是否正确
				boolean isRealType = false;
				for (String str : fileType) {
					if (originalName.toLowerCase().endsWith(str.toLowerCase())) {
						isRealType = true;
						break;
					}
				}
				if (!isRealType) {
					continue;
				}
				// 上传url
				String upLoadUrl = uploadDir +fileFolder+ newFileName;
				BufferedInputStream bis = new BufferedInputStream(fis.openStream());
				// 访问url
				imageUrl = /*prop.get("imageContextPath")+*/fileFolder+ newFileName;
				File file = new File(upLoadUrl);
				if (!file.exists()) {
					file.createNewFile();
				}
				FileOutputStream out = new FileOutputStream(file);
				BufferedOutputStream output = new BufferedOutputStream(out);
				Streams.copy(bis, output, true);
				fileUrlList.add(imageUrl);
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("url", imageUrl);
				map.put("msg", "上传成功");
				map.put("success", true);
				return JSON.toJSONString(map);
			}
		}
		return /*JSON.toJSONString(fileUrlList)*/"";
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

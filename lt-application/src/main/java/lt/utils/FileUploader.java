package lt.utils;

import gzlazypack.common.util.DateUtil;
import gzlazypack.common.util.PropertiesUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
/**
 * 文件上传工具类
 * @author hao_chenhh
 *
 */
public class FileUploader {

	public static String imageUpload(HttpServletRequest request) throws FileUploadException, IOException{
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
				imageUrl = prop.get("imageContextPath")+fileFolder+ newFileName;
				File file = new File(upLoadUrl);
				if (!file.exists()) {
					file.createNewFile();
				}
				FileOutputStream out = new FileOutputStream(file);
				BufferedOutputStream output = new BufferedOutputStream(out);
				Streams.copy(bis, output, true);
				fileUrlList.add(imageUrl);
			}
		}
		return JSON.toJSONString(fileUrlList);
	}
	
	
}

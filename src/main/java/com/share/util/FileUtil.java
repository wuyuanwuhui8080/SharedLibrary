package com.share.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;

/**
 * 文件上传
 *
 * @author 博博
 * @Title: FileUtil
 * @ProjectName SharedLibrary
 * @time 2018/12/17 21:54
 */
@Log4j2
public class FileUtil {

	public static ReturnResult fileUpload(MultipartFile file, String img)
			throws FileNotFoundException {
		File path = new File(ResourceUtils.getURL("classpath:").getPath());

		if (file.isEmpty()) {
			return ReturnResult.error("文件不能为空！");
		}
		// 获取文件名
		String fileName = file.getOriginalFilename();
		log.info("上传的文件名为：{}", fileName);

		// 获取文件的后缀名
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		log.info("上传的后缀名为：{}", suffixName);

		String fileUUIDName = StringUtils.randomUUID() + suffixName;
		log.info("通过uuid随机生成的文件名是:{}", fileUUIDName);
		// 文件上传后的路径
		String filePath = path.getAbsolutePath() + "/static/images/";
		// 解决中文问题，liunx下中文路径，图片显示问题
		// fileName = UUID.randomUUID() + suffixName;

		File dest = new File(filePath + fileUUIDName);
		// 检测是否存在目录
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		try {

			file.transferTo(dest);
			// 根据传入的字符串判断是不是系统默认头像 不是的话就是已经换过其他头像，然后就把之前的头像删除
			if (!img.equals("bd978735b33f496792673949e70fb2eb!400x400.jpeg")) {
				delFile(filePath + img);
			}
			return ReturnResult.okAndList(Arrays.asList(fileUUIDName));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ReturnResult.error("上传失败!");
	}

	/**
	 * 通过文件绝对路径 删除单个文件
	 * 
	 * @param filePath
	 */
	public static boolean delFile(String filePath) {
		File delFile = new File(filePath);
		if (delFile.isFile() && delFile.exists()) {
			delFile.delete();
			log.info("删除文件成功");
			return true;
		} else {
			log.info("没有该文件，删除失败");
			return false;
		}
	}
}

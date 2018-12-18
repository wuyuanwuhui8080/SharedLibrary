package com.share.util;

import java.io.*;
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

	public static ReturnResult fileUpload(MultipartFile file)
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
			return ReturnResult.okAndList(Arrays.asList(fileUUIDName));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ReturnResult.error("上传失败!");
	}

	/**
	 * 保存文件，直接以multipartFile形式
	 *
	 * @param multipartFile
	 * @param path
	 *            文件保存绝对路径
	 * @return 返回文件名
	 * @throws IOException
	 */
	public static String saveImg(MultipartFile multipartFile, String path)
			throws IOException {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		FileInputStream fileInputStream = (FileInputStream) multipartFile
				.getInputStream();
		String fileName = StringUtils.randomUUID() + ".png";
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(path + File.separator + fileName));
		byte[] bs = new byte[1024];
		int len;
		while ((len = fileInputStream.read(bs)) != -1) {
			bos.write(bs, 0, len);
		}
		bos.flush();
		bos.close();
		return fileName;
	}

	/*
	 * public static Integer fileUpdaload(MultipartFile file) { String
	 * oldFileName = file.getOriginalFilename();// 原文件名 String prefix =
	 * FilenameUtils.getExtension(oldFileName);// 原文件后缀 int fileSize = 500000;
	 * if (file.getSize() > fileSize) {// 上传大小不能超过500k return -1; } else if
	 * (prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png") ||
	 * prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")) {//
	 * 校验上传图片的格式 String fileName = System.currentTimeMillis() +
	 * StringUtils.randomUUID() + ".jpg";// 上传到服务器图片的名称 File targetFile = new
	 * File(resourceLocation, fileName); if (!targetFile.exists()) {
	 * targetFile.mkdirs(); }
	 * 
	 * try { file.transferTo(targetFile); } catch (Exception e) {
	 * e.printStackTrace(); return 0; }
	 *//*
		 * isPicPath = path + File.separator + fileName; logoPicPath1 =
		 * request.getContextPath() + "/statics/uploadfiles/" + fileName;
		 *//*
			 * } return 1; }
			 */
}

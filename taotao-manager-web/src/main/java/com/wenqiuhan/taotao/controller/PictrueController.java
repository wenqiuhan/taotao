package com.wenqiuhan.taotao.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片上传
 * 
 * @author 闻秋寒
 *
 */
@RestController
public class PictrueController {

	@Value("${WEBSITE_INDEX_URL}")
	private String WEBSITE_INDEX_URL;

	@RequestMapping("/pic/upload")
	public Map<String, Object> picUpload(HttpServletRequest request, MultipartFile uploadFile) {
		ServletContext servletContext = request.getSession().getServletContext();// 获取ServletContext的对象 代表当前WEB应用
		String realPath = servletContext.getRealPath("/uploads");// 得到文件上传目的位置的真实路径
		String originalFilename = uploadFile.getOriginalFilename();
		String ImgName = UUID.randomUUID().toString() + "."
				+ originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
		System.out.println(ImgName);
		File destDir = new File(realPath + "/" + ImgName);
		String urlDir = WEBSITE_INDEX_URL + "/uploads/" + ImgName;
		System.out.println(destDir);
		if (!destDir.getParentFile().exists()) {
			destDir.getParentFile().mkdirs();
		}
		Map<String, Object> result = new HashMap<>();
		try {
			uploadFile.transferTo(destDir);
			result.put("error", 0);
			result.put("url", urlDir);
		} catch (IOException e) {
			result.put("error", 1);
			result.put("message", "图片上传失败");
			e.printStackTrace();
		}
		System.out.println(result);
		return result;
	}

}

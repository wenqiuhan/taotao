package com.wenqiuhan.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenqiuhan.taotao.content.service.ContentCatgeoryService;
import com.wenqiuhan.taotao.pojo.EasyUITreeNode;
import com.wenqiuhan.taotao.pojo.TaotaoResult;

/**
 * 内容分类管理
 * 
 * @author 闻秋寒
 *
 */
@RequestMapping("/content")
@Controller
public class ContentCategoryController {

	@Autowired
	private ContentCatgeoryService contentCatgeoryService;

	@RequestMapping("/category/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentCategoryList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
		List<EasyUITreeNode> list = contentCatgeoryService.getContentCatgoryList(parentId);
		return list;
	}

	@RequestMapping("/category/create")
	@ResponseBody
	public TaotaoResult addContentCategory(Long parentId, String name) {
		TaotaoResult result = contentCatgeoryService.addContentCategory(parentId, name);
		return result;
	}

	@RequestMapping("/category/update")
	@ResponseBody
	public TaotaoResult updateContentCategory(Long id, String name) {
		TaotaoResult result = contentCatgeoryService.updateContentCategory(id, name);
		return result;
	}

	@RequestMapping("/category/delete")
	@ResponseBody
	public TaotaoResult deleteContentCategory(Long id) {
		TaotaoResult result = contentCatgeoryService.deleteContentCategory(id);
		return result;
	}

}

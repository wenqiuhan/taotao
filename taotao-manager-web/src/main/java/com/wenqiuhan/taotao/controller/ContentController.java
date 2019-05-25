package com.wenqiuhan.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenqiuhan.taotao.content.service.ContentService;
import com.wenqiuhan.taotao.pojo.EasyUIDataGridResult;
import com.wenqiuhan.taotao.pojo.TaotaoResult;
import com.wenqiuhan.taotao.pojo.TbContent;

@RequestMapping("content")
@Controller
public class ContentController {

	@Autowired
	private ContentService contentService;

	@RequestMapping("/query/list")
	@ResponseBody
	public EasyUIDataGridResult getContentList(Long categoryId, Integer page, Integer rows) {
		EasyUIDataGridResult result = contentService.getContentList(categoryId, page, rows);
		return result;
	}

	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult addContent(TbContent content) {
		TaotaoResult result = contentService.addContent(content);
		return result;
	}
}

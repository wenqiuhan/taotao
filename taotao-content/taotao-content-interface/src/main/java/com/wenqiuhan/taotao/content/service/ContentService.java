package com.wenqiuhan.taotao.content.service;

import com.wenqiuhan.taotao.pojo.EasyUIDataGridResult;
import com.wenqiuhan.taotao.pojo.TaotaoResult;
import com.wenqiuhan.taotao.pojo.TbContent;

public interface ContentService {

	EasyUIDataGridResult getContentList(Long categoryId, Integer page, Integer rows);

	public TaotaoResult addContent(TbContent content);
}

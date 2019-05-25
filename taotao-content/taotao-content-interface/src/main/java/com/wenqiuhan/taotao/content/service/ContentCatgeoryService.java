package com.wenqiuhan.taotao.content.service;

import java.util.List;

import com.wenqiuhan.taotao.pojo.EasyUITreeNode;
import com.wenqiuhan.taotao.pojo.TaotaoResult;

public interface ContentCatgeoryService {
	List<EasyUITreeNode> getContentCatgoryList(Long parentId);

	TaotaoResult addContentCategory(Long parentId, String name);

	TaotaoResult updateContentCategory(Long id, String name);

	TaotaoResult deleteContentCategory(Long id);
}

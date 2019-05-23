package com.wenqiuhan.taotao.service;

import java.util.List;

import com.wenqiuhan.taotao.pojo.EasyUITreeNode;

public interface ItemCatService {
	List<EasyUITreeNode> getItemCatList(Long parentId);
}

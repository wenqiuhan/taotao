package com.wenqiuhan.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wenqiuhan.taotao.pojo.EasyUITreeNode;
import com.wenqiuhan.taotao.service.ItemCatService;

/**
 * 商品分类管理
 * 
 * @author 闻秋寒
 *
 */
@RestController
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;

	@RequestMapping("/item/cat/list")
	public List<EasyUITreeNode> getItemCatList(@RequestParam(name = "id", defaultValue = "0") Long parentId) {
		List<EasyUITreeNode> list = itemCatService.getItemCatList(parentId);
		return list;
	}
}

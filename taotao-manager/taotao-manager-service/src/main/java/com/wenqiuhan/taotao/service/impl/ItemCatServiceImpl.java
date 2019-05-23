package com.wenqiuhan.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenqiuhan.taotao.mapper.TbItemCatMapper;
import com.wenqiuhan.taotao.pojo.EasyUITreeNode;
import com.wenqiuhan.taotao.pojo.TbItemCat;
import com.wenqiuhan.taotao.pojo.TbItemCatExample;
import com.wenqiuhan.taotao.pojo.TbItemCatExample.Criteria;
import com.wenqiuhan.taotao.service.ItemCatService;

/**
 * 商品分类管理
 * 
 * @author 闻秋寒
 *
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;

	@Override
	public List<EasyUITreeNode> getItemCatList(Long parentId) {
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
//		转换成EasyUITreNode列表
		List<EasyUITreeNode> resultList = new ArrayList<>();
		for (TbItemCat tbItemCat : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbItemCat.getId());
			node.setText(tbItemCat.getName());
//			如果节点下有子节点“closed”，如果没有“open”
			node.setState(tbItemCat.getIsParent() ? "closed" : "open");
//			添加到节点列表
			resultList.add(node);

		}
		return resultList;
	}

}

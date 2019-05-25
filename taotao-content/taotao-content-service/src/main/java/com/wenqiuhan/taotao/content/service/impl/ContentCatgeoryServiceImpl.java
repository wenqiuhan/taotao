package com.wenqiuhan.taotao.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenqiuhan.taotao.content.service.ContentCatgeoryService;
import com.wenqiuhan.taotao.mapper.TbContentCategoryMapper;
import com.wenqiuhan.taotao.pojo.EasyUITreeNode;
import com.wenqiuhan.taotao.pojo.TaotaoResult;
import com.wenqiuhan.taotao.pojo.TbContentCategory;
import com.wenqiuhan.taotao.pojo.TbContentCategoryExample;
import com.wenqiuhan.taotao.pojo.TbContentCategoryExample.Criteria;

@Service
public class ContentCatgeoryServiceImpl implements ContentCatgeoryService {

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;

	@Override
	public List<EasyUITreeNode> getContentCatgoryList(Long parentId) {
		// 根据parentId查询子节点列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		List<EasyUITreeNode> result = new ArrayList<>();
		for (TbContentCategory tbContentCategory : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent() ? "closed" : "open");
			result.add(node);
		}
		return result;
	}

	@Override
	public TaotaoResult addContentCategory(Long parentId, String name) {
//		创建一个pojo对象
		TbContentCategory contentCategory = new TbContentCategory();
//		补全对象属性
		contentCategory.setParentId(parentId);
		contentCategory.setName(name);
//		状态。1-正常，2-删除
		contentCategory.setStatus(1);
//		排序，默认为1
		contentCategory.setSortOrder(1);
		contentCategory.setIsParent(false);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
//		插入到数据库
		contentCategoryMapper.insert(contentCategory);
//		判断父节点状态
		TbContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
		if (!parent.getIsParent()) {
//			如果父节点为叶子结点改为父节点
			parent.setIsParent(true);
			contentCategoryMapper.updateByPrimaryKey(parent);
		}
//		返回结果
		return TaotaoResult.ok(contentCategory);
	}

	@Override
	public TaotaoResult updateContentCategory(Long id, String name) {
//		创建一个pojo对象
		TbContentCategory contentCategory = new TbContentCategory();
//		补全对象属性
		contentCategory.setId(id);
		contentCategory.setName(name);
		contentCategory.setUpdated(new Date());
		contentCategoryMapper.updateByPrimaryKeySelective(contentCategory);
		return TaotaoResult.ok(contentCategory);
	}

	@Override
	public TaotaoResult deleteContentCategory(Long id) {
//		创建一个pojo对象
		TbContentCategory contentCategory = new TbContentCategory();
//		补全对象属性
		contentCategory.setId(id);
		TbContentCategory contentCategoryBySelect = contentCategoryMapper.selectByPrimaryKey(id);
//		调用级联删除的方法
		cascadeDeleteContentCategory(contentCategoryBySelect);
		return null;
	}

//	级联删除节点
	public Boolean cascadeDeleteContentCategory(TbContentCategory contentCategory) {

//		如果不是父节点直接删除该节点
		if (!contentCategory.getIsParent()) {
			contentCategoryMapper.deleteByPrimaryKey(contentCategory.getId());
//		如果是父节点
		} else {
//			查找所有parentId是传入的id的contentCategory
			TbContentCategoryExample example = new TbContentCategoryExample();
			Criteria criteria = example.createCriteria();
			criteria.andParentIdEqualTo(contentCategory.getId());
			List<TbContentCategory> selectByExample = contentCategoryMapper.selectByExample(example);
//			循环删除
			for (TbContentCategory tbContentCategory : selectByExample) {
				cascadeDeleteContentCategory(tbContentCategory);
			}
			contentCategoryMapper.deleteByPrimaryKey(contentCategory.getId());
			contentCategoryMapper.selectByExample(example);
		}

		return null;
	}
}

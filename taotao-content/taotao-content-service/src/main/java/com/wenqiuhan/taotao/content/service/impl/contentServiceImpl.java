package com.wenqiuhan.taotao.content.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wenqiuhan.taotao.content.service.ContentService;
import com.wenqiuhan.taotao.mapper.TbContentMapper;
import com.wenqiuhan.taotao.pojo.EasyUIDataGridResult;
import com.wenqiuhan.taotao.pojo.TaotaoResult;
import com.wenqiuhan.taotao.pojo.TbContent;
import com.wenqiuhan.taotao.pojo.TbContentExample;
import com.wenqiuhan.taotao.pojo.TbContentExample.Criteria;

@Service
public class contentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;

	@Override
	public EasyUIDataGridResult getContentList(Long categoryId, Integer page, Integer rows) {

//		设置分页信息
		PageHelper.startPage(page, rows);
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		List<TbContent> list = contentMapper.selectByExample(example);
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		EasyUIDataGridResult result = new EasyUIDataGridResult();

		result.setRows(list);
		result.setTotal(pageInfo.getTotal());

		return result;
	}

	@Override
	public TaotaoResult addContent(TbContent content) {
//		补全pojo属性
		content.setCreated(new Date());
		content.setUpdated(new Date());
//		插入到内容表
		contentMapper.insert(content);
		return TaotaoResult.ok();
	}

}

package com.wenqiuhan.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wenqiuhan.taotao.mapper.TbItemMapper;
import com.wenqiuhan.taotao.pojo.EasyUIDataGridResult;
import com.wenqiuhan.taotao.pojo.TbItem;
import com.wenqiuhan.taotao.pojo.TbItemExample;
import com.wenqiuhan.taotao.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;

	@Override
	public TbItem getItemById(Long itemId) {
		TbItem item = itemMapper.selectByPrimaryKey(itemId);
		// TODO Auto-generated method stub
		return item;
	}

	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
		// TODO Auto-generated method stub
//		设置分页信息
		PageHelper.startPage(page, rows);
//		执行查询
		TbItemExample example = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
//		取查询结果
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

}

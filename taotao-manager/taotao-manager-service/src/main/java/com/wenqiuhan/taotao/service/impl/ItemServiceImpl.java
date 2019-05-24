package com.wenqiuhan.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wenqiuhan.taotao.mapper.TbItemDescMapper;
import com.wenqiuhan.taotao.mapper.TbItemMapper;
import com.wenqiuhan.taotao.pojo.EasyUIDataGridResult;
import com.wenqiuhan.taotao.pojo.TaotaoResult;
import com.wenqiuhan.taotao.pojo.TbItem;
import com.wenqiuhan.taotao.pojo.TbItemDesc;
import com.wenqiuhan.taotao.pojo.TbItemExample;
import com.wenqiuhan.taotao.service.ItemService;
import com.wenqiuhan.taotao.utils.IDUtils;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;

	@Autowired
	TbItemDescMapper itemDescMapper;

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

	@Override
	public TaotaoResult addItem(TbItem item, String desc) {
//		生成商品id
		long itemId = IDUtils.genItemId();
//		补全item的属性
		item.setId(itemId);
//		商品状态，1-正常，2-下架，3-删除
		item.setStatus((byte) 1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
//		向商品表插入数据
		itemMapper.insert(item);
//		创建一个商品描述表对应的pojo
		TbItemDesc itemDesc = new TbItemDesc();
//		补全pojo属性
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
//		向商品描述表插入数据
		itemDescMapper.insert(itemDesc);
//		返回结果
		return TaotaoResult.ok();
	}
}

package com.wenqiuhan.taotao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenqiuhan.taotao.mapper.TbItemMapper;
import com.wenqiuhan.taotao.pojo.TbItem;
import com.wenqiuhan.taotao.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;

	@Override
	public TbItem getItemById(Long itemId) {
		TbItem item = itemMapper.selectByPrimaryKey(itemId);
		System.out.println("+++++++++++" + item);
		// TODO Auto-generated method stub
		return item;
	}

}

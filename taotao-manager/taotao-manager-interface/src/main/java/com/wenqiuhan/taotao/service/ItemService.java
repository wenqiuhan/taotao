package com.wenqiuhan.taotao.service;

import com.wenqiuhan.taotao.pojo.EasyUIDataGridResult;
import com.wenqiuhan.taotao.pojo.TaotaoResult;
import com.wenqiuhan.taotao.pojo.TbItem;

public interface ItemService {

	TbItem getItemById(Long itemId);

	EasyUIDataGridResult getItemList(int page, int rows);

	TaotaoResult addItem(TbItem item, String desc);
}

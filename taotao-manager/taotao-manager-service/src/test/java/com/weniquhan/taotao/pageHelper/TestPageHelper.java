package com.weniquhan.taotao.pageHelper;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wenqiuhan.taotao.mapper.TbItemMapper;
import com.wenqiuhan.taotao.pojo.TbItem;
import com.wenqiuhan.taotao.pojo.TbItemExample;

public class TestPageHelper {

	@SuppressWarnings("resource")
	@Test
	public void testPageHelper() throws Exception {
//		1.在mybatis配置文件中配置分页插件
//		2.在执行查询钱分配分页条件。使用PageHelper的静态方法
		PageHelper.startPage(1, 30);
//		3.执行查询
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:applicationContext-dao.xml");
		TbItemMapper itemMapper = applicationContext.getBean(TbItemMapper.class);
//		创建Example对象
		TbItemExample example = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		System.out.println("总记录数：" + pageInfo.getTotal());
		System.out.println("总页数：" + pageInfo.getPages());
		System.out.println("返回的总记录数：" + list.size());
//		4，取得分页信息。使用PageInfo对象获取
	}

}

package cn.tedu.service;

import java.util.List;

import cn.tedu.domain.Prod;

public interface ProdService {
	/**
	 * 添加商品信息方法
	 * @param prod 封装了商品信息的商品对象
	 * @return true - 添加成功   false - 添加失败
	 */
	boolean addProd(Prod prod);

	/**
	 * 获取所有商品信息的方法
	 * @return 返回了封装了商品信息的列表
	 */
	List getAllProd();

	/**
	 * 更新商品信息方法
	 * @param prod 封装了商品信息的对象
	 * @return true-更新成功 false-更新失败
	 */
	boolean updateProd(Prod prod);
}

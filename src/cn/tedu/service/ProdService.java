package cn.tedu.service;

import cn.tedu.domain.Prod;

public interface ProdService {
	/**
	 * 添加商品信息方法
	 * @param prod 封装了商品信息的商品对象
	 * @return true - 添加成功   false - 添加失败
	 */
	boolean addProd(Prod prod);
}

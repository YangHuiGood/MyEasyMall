package cn.tedu.dao;

import java.util.List;

import cn.tedu.domain.Prod;
import cn.tedu.domain.ProdCategory;
import cn.tedu.exception.MsgException;

public interface ProdDao {
	/**
	 * 根据商品种类名称查询商品种类id的方法
	 * @param cname 商品种类名称
	 * @return 存在返回 商品id  不存在返回-1
	 * @throws MsgException
	 */
	int getCidByCname(String cname) throws MsgException;
	
	/**
	 * 添加商品种类的方法
	 * @param pc 封装了商品种类信息的对象
	 * @return 添加成功返回true ，添加失败返回false
	 */
	boolean insertPC(ProdCategory pc);
	
	/**
	 * 添加商品信息的方法
	 * @param prod 封装了商品信息的对象
	 * @return 成功返回 true 失败返回 false
	 */
	boolean insertProd(Prod prod);

	/**
	 * 查询所有商品信息并以列表返回
	 * @return 封装了商品信息的列表
	 */
	List getAllProd();

	/**
	 * 更新商品信息
	 * @param prod 封装了商品信息的对象
	 * @return true-更新成功 false-更新失败
	 */
	boolean updateProd(Prod prod);
}

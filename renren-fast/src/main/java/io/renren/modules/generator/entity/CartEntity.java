package io.renren.modules.generator.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 购物车; InnoDB free: 7168 kB
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-12-21 15:04:29
 */
@Data
@TableName("cart")
public class CartEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * SKUID
	 */
	@TableId
	private Integer skuId;
	/**
	 * 选中
	 */
	private Integer selected;
	/**
	 * 价格
	 */
	private Double price;
	/**
	 * 数量
	 */
	@TableField("`count`")
	private Integer count;
	/**
	 * 规格
	 */
	private String attrsText;
	/**
	 * 订单ID
	 */
	private String memberId;

}

package io.renren.modules.generator.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * SKU; InnoDB free: 7168 kB
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-12-21 15:04:29
 */
@Data
@TableName("goods_sku")
public class GoodsSkuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * SKUID
	 */
	@TableId
	private Integer id;
	/**
	 * 名称
	 */
	@TableField("`name`")
	private String name;
	/**
	 * 商品ID
	 */
	private Integer goodId;
	/**
	 * 价格
	 */
	private Double price;
	/**
	 * 上次价格
	 */
	private Double oldPrice;
	/**
	 * 存库
	 */
	private Integer inventory;
	/**
	 * 有效
	 */
	private Integer isEffective;
	/**
	 * 扣折
	 */
	private Double discount;

}

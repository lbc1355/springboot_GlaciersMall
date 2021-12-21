package io.renren.modules.generator.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 商品; InnoDB free: 7168 kB
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-12-21 15:04:29
 */
@Data
@TableName("goods")
public class GoodsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品ID
	 */
	@TableId
	private Integer id;
	/**
	 * 名称
	 */
	@TableField("`name`")
	private String name;
	/**
	 * 描述
	 */
	@TableField("`desc`")
	private String desc;
	/**
	 * 价格
	 */
	private String price;
	/**
	 * 图片
	 */
	private String picture;
	/**
	 * 折扣
	 */
	private Double discount;
	/**
	 * 顺序
	 */
	private Integer orderNum;
	/**
	 * 类分ID
	 */
	private Integer categoryId;
	/**
	 * 品牌ID
	 */
	private Integer brandId;

}

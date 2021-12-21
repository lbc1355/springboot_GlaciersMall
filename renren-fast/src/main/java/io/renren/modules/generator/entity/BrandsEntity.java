package io.renren.modules.generator.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 品牌; InnoDB free: 7168 kB
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-12-21 15:04:29
 */
@Data
@TableName("brands")
public class BrandsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 品牌ID
	 */
	@TableId
	private Integer id;
	/**
	 * 名称
	 */
	@TableField("`name`")
	private String name;
	/**
	 * 英文名称
	 */
	private String nameen;
	/**
	 * 商标
	 */
	private String logo;
	/**
	 * 图片
	 */
	private String picture;
	/**
	 * 型类
	 */
	@TableField("`type`")
	private Integer type;
	/**
	 * 述描
	 */
	@TableField("`desc`")
	private String desc;
	/**
	 * 地点
	 */
	private String place;

}

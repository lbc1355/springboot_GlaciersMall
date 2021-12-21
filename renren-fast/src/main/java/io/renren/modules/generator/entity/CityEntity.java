package io.renren.modules.generator.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 城市; InnoDB free: 7168 kB
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-12-21 15:04:29
 */
@Data
@TableName("city")
public class CityEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 城市代码
	 */
	@TableId
	private Integer code;
	/**
	 * 城市登记
	 */
	@TableField("`level`")
	private Integer level;
	/**
	 * 城市名称
	 */
	@TableField("`name`")
	private String name;
	/**
	 * 父级ID
	 */
	private Integer parentId;

}

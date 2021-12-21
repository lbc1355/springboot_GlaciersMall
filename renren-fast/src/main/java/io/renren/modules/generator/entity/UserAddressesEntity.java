package io.renren.modules.generator.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 地址; InnoDB free: 7168 kB
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-12-21 15:04:29
 */
@Data
@TableName("user_addresses")
public class UserAddressesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 址地ID
	 */
	@TableId
	private Integer id;
	/**
	 * 户用ID
	 */
	private String memberId;
	/**
	 * 货人收
	 */
	private String receiver;
	/**
	 * 联系电话
	 */
	private String contact;
	/**
	 * 地区
	 */
	private String fullLocation;
	/**
	 * 详细地址
	 */
	private String address;
	/**
	 * 政编码邮
	 */
	private String postalCode;
	/**
	 * 地址标签
	 */
	private String addressTags;
	/**
	 * 否是为默认地址
	 */
	private Integer isDefault;
	/**
	 * 省份代码
	 */
	private String provinceCode;
	/**
	 * 城市代码
	 */
	private String cityCode;
	/**
	 * 县城代码
	 */
	private String countyCode;

}

package io.renren.modules.generator.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户; InnoDB free: 7168 kB
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-12-21 15:04:29
 */
@Data
@TableName("user_member")
public class UserMemberEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private String id;
	/**
	 * 账号
	 */
	private String account;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 创建者
	 */
	private Integer creator;
	/**
	 * 删除
	 */
	private Integer isDelete;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 新更时间
	 */
	private Date updateTime;
	/**
	 * 
	 */
	private String mobile;
	/**
	 * 
	 */
	private String unionId;
	/**
	 * 
	 */
	private String source;
	/**
	 * 
	 */
	private String avatar;
	/**
	 * 
	 */
	private String gender;
	/**
	 * 
	 */
	private Date birthday;
	/**
	 * 
	 */
	private Integer citycode;
	/**
	 * 
	 */
	private String profession;
	/**
	 * 
	 */
	private Integer provincecode;

}

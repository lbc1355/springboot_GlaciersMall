package io.renren.modules.generator.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 订单表; InnoDB free: 7168 kB
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-12-21 15:04:29
 */
@Data
@TableName("`order`")
public class OrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 单订ID
	 */
	@TableId
	private String id;
	/**
	 * 用户ID
	 */
	private String memberId;
	/**
	 * 付支方式
	 */
	private Integer payChannel;
	/**
	 * 线支付在或者货到付款
	 */
	private Integer payType;
	/**
	 * 订单状态
	 */
	private Integer orderState;
	/**
	 * 费邮
	 */
	private Double postFee;
	/**
	 * 实付金额
	 */
	private Double payMoney;
	/**
	 * 金额合计
	 */
	private Double totalMoney;
	/**
	 * 数量合计
	 */
	private Integer totalNum;
	/**
	 * 建创订单时间
	 */
	private String createTime;
	/**
	 * 款付时间型类
	 */
	private String payTime;
	/**
	 * 配送时间
	 */
	private Integer deliveryTimeType;
	/**
	 * 买卖留言
	 */
	private String buyerMessage;

}

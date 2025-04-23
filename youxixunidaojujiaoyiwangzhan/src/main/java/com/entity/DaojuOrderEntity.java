package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 道具订单
 *
 * @author 
 * @email
 */
@TableName("daoju_order")
public class DaojuOrderEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public DaojuOrderEntity() {

	}

	public DaojuOrderEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 订单号
     */
    @TableField(value = "daoju_order_uuid_number")

    private String daojuOrderUuidNumber;


    /**
     * 道具
     */
    @TableField(value = "daoju_id")

    private Integer daojuId;


    /**
     * 用户
     */
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 购买数量
     */
    @TableField(value = "buy_number")

    private Integer buyNumber;


    /**
     * 实付价格
     */
    @TableField(value = "daoju_order_true_price")

    private Double daojuOrderTruePrice;


    /**
     * 兑换网址
     */
    @TableField(value = "daoju_order_courier_name")

    private String daojuOrderCourierName;


    /**
     * 兑换码
     */
    @TableField(value = "daoju_order_courier_number")

    private String daojuOrderCourierNumber;


    /**
     * 订单类型
     */
    @TableField(value = "daoju_order_types")

    private Integer daojuOrderTypes;


    /**
     * 支付类型
     */
    @TableField(value = "daoju_order_payment_types")

    private Integer daojuOrderPaymentTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }
    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：订单号
	 */
    public String getDaojuOrderUuidNumber() {
        return daojuOrderUuidNumber;
    }
    /**
	 * 获取：订单号
	 */

    public void setDaojuOrderUuidNumber(String daojuOrderUuidNumber) {
        this.daojuOrderUuidNumber = daojuOrderUuidNumber;
    }
    /**
	 * 设置：道具
	 */
    public Integer getDaojuId() {
        return daojuId;
    }
    /**
	 * 获取：道具
	 */

    public void setDaojuId(Integer daojuId) {
        this.daojuId = daojuId;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }
    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：购买数量
	 */
    public Integer getBuyNumber() {
        return buyNumber;
    }
    /**
	 * 获取：购买数量
	 */

    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
    /**
	 * 设置：实付价格
	 */
    public Double getDaojuOrderTruePrice() {
        return daojuOrderTruePrice;
    }
    /**
	 * 获取：实付价格
	 */

    public void setDaojuOrderTruePrice(Double daojuOrderTruePrice) {
        this.daojuOrderTruePrice = daojuOrderTruePrice;
    }
    /**
	 * 设置：兑换网址
	 */
    public String getDaojuOrderCourierName() {
        return daojuOrderCourierName;
    }
    /**
	 * 获取：兑换网址
	 */

    public void setDaojuOrderCourierName(String daojuOrderCourierName) {
        this.daojuOrderCourierName = daojuOrderCourierName;
    }
    /**
	 * 设置：兑换码
	 */
    public String getDaojuOrderCourierNumber() {
        return daojuOrderCourierNumber;
    }
    /**
	 * 获取：兑换码
	 */

    public void setDaojuOrderCourierNumber(String daojuOrderCourierNumber) {
        this.daojuOrderCourierNumber = daojuOrderCourierNumber;
    }
    /**
	 * 设置：订单类型
	 */
    public Integer getDaojuOrderTypes() {
        return daojuOrderTypes;
    }
    /**
	 * 获取：订单类型
	 */

    public void setDaojuOrderTypes(Integer daojuOrderTypes) {
        this.daojuOrderTypes = daojuOrderTypes;
    }
    /**
	 * 设置：支付类型
	 */
    public Integer getDaojuOrderPaymentTypes() {
        return daojuOrderPaymentTypes;
    }
    /**
	 * 获取：支付类型
	 */

    public void setDaojuOrderPaymentTypes(Integer daojuOrderPaymentTypes) {
        this.daojuOrderPaymentTypes = daojuOrderPaymentTypes;
    }
    /**
	 * 设置：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 获取：订单创建时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "DaojuOrder{" +
            "id=" + id +
            ", daojuOrderUuidNumber=" + daojuOrderUuidNumber +
            ", daojuId=" + daojuId +
            ", yonghuId=" + yonghuId +
            ", buyNumber=" + buyNumber +
            ", daojuOrderTruePrice=" + daojuOrderTruePrice +
            ", daojuOrderCourierName=" + daojuOrderCourierName +
            ", daojuOrderCourierNumber=" + daojuOrderCourierNumber +
            ", daojuOrderTypes=" + daojuOrderTypes +
            ", daojuOrderPaymentTypes=" + daojuOrderPaymentTypes +
            ", insertTime=" + insertTime +
            ", createTime=" + createTime +
        "}";
    }
}

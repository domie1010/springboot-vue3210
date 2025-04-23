package com.entity.model;

import com.entity.DaojuOrderEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 道具订单
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class DaojuOrderModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 订单号
     */
    private String daojuOrderUuidNumber;


    /**
     * 道具
     */
    private Integer daojuId;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 购买数量
     */
    private Integer buyNumber;


    /**
     * 实付价格
     */
    private Double daojuOrderTruePrice;


    /**
     * 兑换网址
     */
    private String daojuOrderCourierName;


    /**
     * 兑换码
     */
    private String daojuOrderCourierNumber;


    /**
     * 订单类型
     */
    private Integer daojuOrderTypes;


    /**
     * 支付类型
     */
    private Integer daojuOrderPaymentTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间 show3
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：订单号
	 */
    public String getDaojuOrderUuidNumber() {
        return daojuOrderUuidNumber;
    }


    /**
	 * 设置：订单号
	 */
    public void setDaojuOrderUuidNumber(String daojuOrderUuidNumber) {
        this.daojuOrderUuidNumber = daojuOrderUuidNumber;
    }
    /**
	 * 获取：道具
	 */
    public Integer getDaojuId() {
        return daojuId;
    }


    /**
	 * 设置：道具
	 */
    public void setDaojuId(Integer daojuId) {
        this.daojuId = daojuId;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：用户
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：购买数量
	 */
    public Integer getBuyNumber() {
        return buyNumber;
    }


    /**
	 * 设置：购买数量
	 */
    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
    /**
	 * 获取：实付价格
	 */
    public Double getDaojuOrderTruePrice() {
        return daojuOrderTruePrice;
    }


    /**
	 * 设置：实付价格
	 */
    public void setDaojuOrderTruePrice(Double daojuOrderTruePrice) {
        this.daojuOrderTruePrice = daojuOrderTruePrice;
    }
    /**
	 * 获取：兑换网址
	 */
    public String getDaojuOrderCourierName() {
        return daojuOrderCourierName;
    }


    /**
	 * 设置：兑换网址
	 */
    public void setDaojuOrderCourierName(String daojuOrderCourierName) {
        this.daojuOrderCourierName = daojuOrderCourierName;
    }
    /**
	 * 获取：兑换码
	 */
    public String getDaojuOrderCourierNumber() {
        return daojuOrderCourierNumber;
    }


    /**
	 * 设置：兑换码
	 */
    public void setDaojuOrderCourierNumber(String daojuOrderCourierNumber) {
        this.daojuOrderCourierNumber = daojuOrderCourierNumber;
    }
    /**
	 * 获取：订单类型
	 */
    public Integer getDaojuOrderTypes() {
        return daojuOrderTypes;
    }


    /**
	 * 设置：订单类型
	 */
    public void setDaojuOrderTypes(Integer daojuOrderTypes) {
        this.daojuOrderTypes = daojuOrderTypes;
    }
    /**
	 * 获取：支付类型
	 */
    public Integer getDaojuOrderPaymentTypes() {
        return daojuOrderPaymentTypes;
    }


    /**
	 * 设置：支付类型
	 */
    public void setDaojuOrderPaymentTypes(Integer daojuOrderPaymentTypes) {
        this.daojuOrderPaymentTypes = daojuOrderPaymentTypes;
    }
    /**
	 * 获取：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：订单创建时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间 show3
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }

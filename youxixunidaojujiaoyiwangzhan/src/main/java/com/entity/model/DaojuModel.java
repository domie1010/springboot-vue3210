package com.entity.model;

import com.entity.DaojuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 道具
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class DaojuModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 道具名称
     */
    private String daojuName;


    /**
     * 道具编号
     */
    private String daojuUuidNumber;


    /**
     * 道具照片
     */
    private String daojuPhoto;


    /**
     * 道具类型
     */
    private Integer daojuTypes;


    /**
     * 道具库存
     */
    private Integer daojuKucunNumber;


    /**
     * 购买获得积分
     */
    private Integer daojuPrice;


    /**
     * 道具原价
     */
    private Double daojuOldMoney;


    /**
     * 现价
     */
    private Double daojuNewMoney;


    /**
     * 点击次数
     */
    private Integer daojuClicknum;


    /**
     * 道具介绍
     */
    private String daojuContent;


    /**
     * 是否上架
     */
    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    private Integer daojuDelete;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 获取：道具名称
	 */
    public String getDaojuName() {
        return daojuName;
    }


    /**
	 * 设置：道具名称
	 */
    public void setDaojuName(String daojuName) {
        this.daojuName = daojuName;
    }
    /**
	 * 获取：道具编号
	 */
    public String getDaojuUuidNumber() {
        return daojuUuidNumber;
    }


    /**
	 * 设置：道具编号
	 */
    public void setDaojuUuidNumber(String daojuUuidNumber) {
        this.daojuUuidNumber = daojuUuidNumber;
    }
    /**
	 * 获取：道具照片
	 */
    public String getDaojuPhoto() {
        return daojuPhoto;
    }


    /**
	 * 设置：道具照片
	 */
    public void setDaojuPhoto(String daojuPhoto) {
        this.daojuPhoto = daojuPhoto;
    }
    /**
	 * 获取：道具类型
	 */
    public Integer getDaojuTypes() {
        return daojuTypes;
    }


    /**
	 * 设置：道具类型
	 */
    public void setDaojuTypes(Integer daojuTypes) {
        this.daojuTypes = daojuTypes;
    }
    /**
	 * 获取：道具库存
	 */
    public Integer getDaojuKucunNumber() {
        return daojuKucunNumber;
    }


    /**
	 * 设置：道具库存
	 */
    public void setDaojuKucunNumber(Integer daojuKucunNumber) {
        this.daojuKucunNumber = daojuKucunNumber;
    }
    /**
	 * 获取：购买获得积分
	 */
    public Integer getDaojuPrice() {
        return daojuPrice;
    }


    /**
	 * 设置：购买获得积分
	 */
    public void setDaojuPrice(Integer daojuPrice) {
        this.daojuPrice = daojuPrice;
    }
    /**
	 * 获取：道具原价
	 */
    public Double getDaojuOldMoney() {
        return daojuOldMoney;
    }


    /**
	 * 设置：道具原价
	 */
    public void setDaojuOldMoney(Double daojuOldMoney) {
        this.daojuOldMoney = daojuOldMoney;
    }
    /**
	 * 获取：现价
	 */
    public Double getDaojuNewMoney() {
        return daojuNewMoney;
    }


    /**
	 * 设置：现价
	 */
    public void setDaojuNewMoney(Double daojuNewMoney) {
        this.daojuNewMoney = daojuNewMoney;
    }
    /**
	 * 获取：点击次数
	 */
    public Integer getDaojuClicknum() {
        return daojuClicknum;
    }


    /**
	 * 设置：点击次数
	 */
    public void setDaojuClicknum(Integer daojuClicknum) {
        this.daojuClicknum = daojuClicknum;
    }
    /**
	 * 获取：道具介绍
	 */
    public String getDaojuContent() {
        return daojuContent;
    }


    /**
	 * 设置：道具介绍
	 */
    public void setDaojuContent(String daojuContent) {
        this.daojuContent = daojuContent;
    }
    /**
	 * 获取：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }


    /**
	 * 设置：是否上架
	 */
    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getDaojuDelete() {
        return daojuDelete;
    }


    /**
	 * 设置：逻辑删除
	 */
    public void setDaojuDelete(Integer daojuDelete) {
        this.daojuDelete = daojuDelete;
    }
    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }

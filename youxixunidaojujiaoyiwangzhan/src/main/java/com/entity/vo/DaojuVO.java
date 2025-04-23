package com.entity.vo;

import com.entity.DaojuEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 道具
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("daoju")
public class DaojuVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 道具名称
     */

    @TableField(value = "daoju_name")
    private String daojuName;


    /**
     * 道具编号
     */

    @TableField(value = "daoju_uuid_number")
    private String daojuUuidNumber;


    /**
     * 道具照片
     */

    @TableField(value = "daoju_photo")
    private String daojuPhoto;


    /**
     * 道具类型
     */

    @TableField(value = "daoju_types")
    private Integer daojuTypes;


    /**
     * 道具库存
     */

    @TableField(value = "daoju_kucun_number")
    private Integer daojuKucunNumber;


    /**
     * 购买获得积分
     */

    @TableField(value = "daoju_price")
    private Integer daojuPrice;


    /**
     * 道具原价
     */

    @TableField(value = "daoju_old_money")
    private Double daojuOldMoney;


    /**
     * 现价
     */

    @TableField(value = "daoju_new_money")
    private Double daojuNewMoney;


    /**
     * 点击次数
     */

    @TableField(value = "daoju_clicknum")
    private Integer daojuClicknum;


    /**
     * 道具介绍
     */

    @TableField(value = "daoju_content")
    private String daojuContent;


    /**
     * 是否上架
     */

    @TableField(value = "shangxia_types")
    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */

    @TableField(value = "daoju_delete")
    private Integer daojuDelete;


    /**
     * 创建时间  show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
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
	 * 设置：道具名称
	 */
    public String getDaojuName() {
        return daojuName;
    }


    /**
	 * 获取：道具名称
	 */

    public void setDaojuName(String daojuName) {
        this.daojuName = daojuName;
    }
    /**
	 * 设置：道具编号
	 */
    public String getDaojuUuidNumber() {
        return daojuUuidNumber;
    }


    /**
	 * 获取：道具编号
	 */

    public void setDaojuUuidNumber(String daojuUuidNumber) {
        this.daojuUuidNumber = daojuUuidNumber;
    }
    /**
	 * 设置：道具照片
	 */
    public String getDaojuPhoto() {
        return daojuPhoto;
    }


    /**
	 * 获取：道具照片
	 */

    public void setDaojuPhoto(String daojuPhoto) {
        this.daojuPhoto = daojuPhoto;
    }
    /**
	 * 设置：道具类型
	 */
    public Integer getDaojuTypes() {
        return daojuTypes;
    }


    /**
	 * 获取：道具类型
	 */

    public void setDaojuTypes(Integer daojuTypes) {
        this.daojuTypes = daojuTypes;
    }
    /**
	 * 设置：道具库存
	 */
    public Integer getDaojuKucunNumber() {
        return daojuKucunNumber;
    }


    /**
	 * 获取：道具库存
	 */

    public void setDaojuKucunNumber(Integer daojuKucunNumber) {
        this.daojuKucunNumber = daojuKucunNumber;
    }
    /**
	 * 设置：购买获得积分
	 */
    public Integer getDaojuPrice() {
        return daojuPrice;
    }


    /**
	 * 获取：购买获得积分
	 */

    public void setDaojuPrice(Integer daojuPrice) {
        this.daojuPrice = daojuPrice;
    }
    /**
	 * 设置：道具原价
	 */
    public Double getDaojuOldMoney() {
        return daojuOldMoney;
    }


    /**
	 * 获取：道具原价
	 */

    public void setDaojuOldMoney(Double daojuOldMoney) {
        this.daojuOldMoney = daojuOldMoney;
    }
    /**
	 * 设置：现价
	 */
    public Double getDaojuNewMoney() {
        return daojuNewMoney;
    }


    /**
	 * 获取：现价
	 */

    public void setDaojuNewMoney(Double daojuNewMoney) {
        this.daojuNewMoney = daojuNewMoney;
    }
    /**
	 * 设置：点击次数
	 */
    public Integer getDaojuClicknum() {
        return daojuClicknum;
    }


    /**
	 * 获取：点击次数
	 */

    public void setDaojuClicknum(Integer daojuClicknum) {
        this.daojuClicknum = daojuClicknum;
    }
    /**
	 * 设置：道具介绍
	 */
    public String getDaojuContent() {
        return daojuContent;
    }


    /**
	 * 获取：道具介绍
	 */

    public void setDaojuContent(String daojuContent) {
        this.daojuContent = daojuContent;
    }
    /**
	 * 设置：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }


    /**
	 * 获取：是否上架
	 */

    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getDaojuDelete() {
        return daojuDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setDaojuDelete(Integer daojuDelete) {
        this.daojuDelete = daojuDelete;
    }
    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}

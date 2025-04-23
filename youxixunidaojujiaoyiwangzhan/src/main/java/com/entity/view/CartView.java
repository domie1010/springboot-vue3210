package com.entity.view;

import com.entity.CartEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 购物车
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("cart")
public class CartView extends CartEntity implements Serializable {
    private static final long serialVersionUID = 1L;




		//级联表 daoju
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
				* 道具类型的值
				*/
				private String daojuValue;
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
				* 是否上架的值
				*/
				private String shangxiaValue;
			/**
			* 逻辑删除
			*/
			private Integer daojuDelete;

		//级联表 yonghu
			/**
			* 用户姓名
			*/
			private String yonghuName;
			/**
			* 用户手机号
			*/
			private String yonghuPhone;
			/**
			* 用户身份证号
			*/
			private String yonghuIdNumber;
			/**
			* 用户头像
			*/
			private String yonghuPhoto;
			/**
			* 电子邮箱
			*/
			private String yonghuEmail;
			/**
			* 余额
			*/
			private Double newMoney;
			/**
			* 总积分
			*/
			private Double yonghuSumJifen;
			/**
			* 现积分
			*/
			private Double yonghuNewJifen;
			/**
			* 会员等级
			*/
			private Integer huiyuandengjiTypes;
				/**
				* 会员等级的值
				*/
				private String huiyuandengjiValue;

	public CartView() {

	}

	public CartView(CartEntity cartEntity) {
		try {
			BeanUtils.copyProperties(this, cartEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}











				//级联表的get和set daoju

					/**
					* 获取： 道具名称
					*/
					public String getDaojuName() {
						return daojuName;
					}
					/**
					* 设置： 道具名称
					*/
					public void setDaojuName(String daojuName) {
						this.daojuName = daojuName;
					}

					/**
					* 获取： 道具编号
					*/
					public String getDaojuUuidNumber() {
						return daojuUuidNumber;
					}
					/**
					* 设置： 道具编号
					*/
					public void setDaojuUuidNumber(String daojuUuidNumber) {
						this.daojuUuidNumber = daojuUuidNumber;
					}

					/**
					* 获取： 道具照片
					*/
					public String getDaojuPhoto() {
						return daojuPhoto;
					}
					/**
					* 设置： 道具照片
					*/
					public void setDaojuPhoto(String daojuPhoto) {
						this.daojuPhoto = daojuPhoto;
					}

					/**
					* 获取： 道具类型
					*/
					public Integer getDaojuTypes() {
						return daojuTypes;
					}
					/**
					* 设置： 道具类型
					*/
					public void setDaojuTypes(Integer daojuTypes) {
						this.daojuTypes = daojuTypes;
					}


						/**
						* 获取： 道具类型的值
						*/
						public String getDaojuValue() {
							return daojuValue;
						}
						/**
						* 设置： 道具类型的值
						*/
						public void setDaojuValue(String daojuValue) {
							this.daojuValue = daojuValue;
						}

					/**
					* 获取： 道具库存
					*/
					public Integer getDaojuKucunNumber() {
						return daojuKucunNumber;
					}
					/**
					* 设置： 道具库存
					*/
					public void setDaojuKucunNumber(Integer daojuKucunNumber) {
						this.daojuKucunNumber = daojuKucunNumber;
					}

					/**
					* 获取： 购买获得积分
					*/
					public Integer getDaojuPrice() {
						return daojuPrice;
					}
					/**
					* 设置： 购买获得积分
					*/
					public void setDaojuPrice(Integer daojuPrice) {
						this.daojuPrice = daojuPrice;
					}

					/**
					* 获取： 道具原价
					*/
					public Double getDaojuOldMoney() {
						return daojuOldMoney;
					}
					/**
					* 设置： 道具原价
					*/
					public void setDaojuOldMoney(Double daojuOldMoney) {
						this.daojuOldMoney = daojuOldMoney;
					}

					/**
					* 获取： 现价
					*/
					public Double getDaojuNewMoney() {
						return daojuNewMoney;
					}
					/**
					* 设置： 现价
					*/
					public void setDaojuNewMoney(Double daojuNewMoney) {
						this.daojuNewMoney = daojuNewMoney;
					}

					/**
					* 获取： 点击次数
					*/
					public Integer getDaojuClicknum() {
						return daojuClicknum;
					}
					/**
					* 设置： 点击次数
					*/
					public void setDaojuClicknum(Integer daojuClicknum) {
						this.daojuClicknum = daojuClicknum;
					}

					/**
					* 获取： 道具介绍
					*/
					public String getDaojuContent() {
						return daojuContent;
					}
					/**
					* 设置： 道具介绍
					*/
					public void setDaojuContent(String daojuContent) {
						this.daojuContent = daojuContent;
					}

					/**
					* 获取： 是否上架
					*/
					public Integer getShangxiaTypes() {
						return shangxiaTypes;
					}
					/**
					* 设置： 是否上架
					*/
					public void setShangxiaTypes(Integer shangxiaTypes) {
						this.shangxiaTypes = shangxiaTypes;
					}


						/**
						* 获取： 是否上架的值
						*/
						public String getShangxiaValue() {
							return shangxiaValue;
						}
						/**
						* 设置： 是否上架的值
						*/
						public void setShangxiaValue(String shangxiaValue) {
							this.shangxiaValue = shangxiaValue;
						}

					/**
					* 获取： 逻辑删除
					*/
					public Integer getDaojuDelete() {
						return daojuDelete;
					}
					/**
					* 设置： 逻辑删除
					*/
					public void setDaojuDelete(Integer daojuDelete) {
						this.daojuDelete = daojuDelete;
					}

















				//级联表的get和set yonghu

					/**
					* 获取： 用户姓名
					*/
					public String getYonghuName() {
						return yonghuName;
					}
					/**
					* 设置： 用户姓名
					*/
					public void setYonghuName(String yonghuName) {
						this.yonghuName = yonghuName;
					}

					/**
					* 获取： 用户手机号
					*/
					public String getYonghuPhone() {
						return yonghuPhone;
					}
					/**
					* 设置： 用户手机号
					*/
					public void setYonghuPhone(String yonghuPhone) {
						this.yonghuPhone = yonghuPhone;
					}

					/**
					* 获取： 用户身份证号
					*/
					public String getYonghuIdNumber() {
						return yonghuIdNumber;
					}
					/**
					* 设置： 用户身份证号
					*/
					public void setYonghuIdNumber(String yonghuIdNumber) {
						this.yonghuIdNumber = yonghuIdNumber;
					}

					/**
					* 获取： 用户头像
					*/
					public String getYonghuPhoto() {
						return yonghuPhoto;
					}
					/**
					* 设置： 用户头像
					*/
					public void setYonghuPhoto(String yonghuPhoto) {
						this.yonghuPhoto = yonghuPhoto;
					}

					/**
					* 获取： 电子邮箱
					*/
					public String getYonghuEmail() {
						return yonghuEmail;
					}
					/**
					* 设置： 电子邮箱
					*/
					public void setYonghuEmail(String yonghuEmail) {
						this.yonghuEmail = yonghuEmail;
					}

					/**
					* 获取： 余额
					*/
					public Double getNewMoney() {
						return newMoney;
					}
					/**
					* 设置： 余额
					*/
					public void setNewMoney(Double newMoney) {
						this.newMoney = newMoney;
					}

					/**
					* 获取： 总积分
					*/
					public Double getYonghuSumJifen() {
						return yonghuSumJifen;
					}
					/**
					* 设置： 总积分
					*/
					public void setYonghuSumJifen(Double yonghuSumJifen) {
						this.yonghuSumJifen = yonghuSumJifen;
					}

					/**
					* 获取： 现积分
					*/
					public Double getYonghuNewJifen() {
						return yonghuNewJifen;
					}
					/**
					* 设置： 现积分
					*/
					public void setYonghuNewJifen(Double yonghuNewJifen) {
						this.yonghuNewJifen = yonghuNewJifen;
					}

					/**
					* 获取： 会员等级
					*/
					public Integer getHuiyuandengjiTypes() {
						return huiyuandengjiTypes;
					}
					/**
					* 设置： 会员等级
					*/
					public void setHuiyuandengjiTypes(Integer huiyuandengjiTypes) {
						this.huiyuandengjiTypes = huiyuandengjiTypes;
					}


						/**
						* 获取： 会员等级的值
						*/
						public String getHuiyuandengjiValue() {
							return huiyuandengjiValue;
						}
						/**
						* 设置： 会员等级的值
						*/
						public void setHuiyuandengjiValue(String huiyuandengjiValue) {
							this.huiyuandengjiValue = huiyuandengjiValue;
						}




}

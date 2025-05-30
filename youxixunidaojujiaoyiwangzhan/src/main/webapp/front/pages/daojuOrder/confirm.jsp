

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="true" %>
<!-- 个人中心 -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>确认下单</title>
    <link rel="stylesheet" href="../../layui/css/layui.css">
    <!-- 主题（主要颜色设置） -->
    <link rel="stylesheet" href="../../css/theme.css" />
    <!-- 通用的css -->
    <link rel="stylesheet" href="../../css/common.css" />
</head>
<style>

    .container {
        margin: 0 auto;
        width: 980px;
    }
    .index-title {
        text-align: center;
        box-sizing: border-box;
        width: 980px;
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
    }
    .index-title span {
        padding: 0 10px;
        line-height: 1.4;
    }

    .common_title{width:1200px;margin:20px auto 0;font-size:14px;}

    .common_list_con{width:1200px;border:1px solid #dddddd;border-top:2px solid var(--publicMainColor,orange);margin:10px auto 0;background-color:#f7f7f7;position:relative;}

    .common_list_con dl{margin:20px;}
    .common_list_con dt{font-size:14px;font-weight:bold;margin-bottom:10px}
    .common_list_con dd input{vertical-align:bottom;margin-right:10px;margin-top: 5px;margin-left: 30px;}


    .goods_list_th{height:40px;border-bottom:1px solid #ccc}
    .goods_list_th li{float:left;line-height:40px;text-align:center;}
    .goods_list_th .col01{width:25%}
    .goods_list_th .col02{width:20%}
    .goods_list_th .col04{width:20%}
    .goods_list_th .col05{width:20%}

    .goods_list_td{height:80px;border-bottom:1px solid #eeeded}
    .goods_list_td li{float:left;line-height:80px;text-align:center;}
    .goods_list_td .col01{width:8%}
    .goods_list_td .col02{width:6%}
    .goods_list_td .col03{width:15%}
    .goods_list_td .col04{width:20%}
    .goods_list_td .col05{width:13%}
    .goods_list_td .col06{width:26%}
    .goods_list_td .col07{width:15%}

    .goods_list_td .col02{text-align:right}
    .goods_list_td .col02 img{width:63px;height:63px;border:1px solid #ddd;display:block;margin:7px 0;float:right;}
    .goods_list_td .col03{text-align:left;text-indent:20px}


    .settle_con{margin:10px}
    .total_goods_count,.transit,.total_pay{line-height:24px;text-align:right}
    .total_goods_count em,.total_goods_count b,.transit b,.total_pay b{font-size:14px;color:#ff4200;padding:0 5px;}

    .order_submit{width:1200px;margin:20px auto;}
    .order_submit a{width:160px;height:40px;line-height:40px;text-align:center;background-color:var(--publicMainColor,orange);color:#fff;font-size:16px;display:block;float:right}


</style>
<body>

<div id="app">
    <!-- 轮播图 -->
    <div class="layui-carousel" id="swiper">
        <div carousel-item id="swiper-item">
            <div v-for="(item,index) in swiperList" v-bind:key="index">
                <img class="swiper-item" :src="item.img">
            </div>
        </div>
    </div>
    <!-- 轮播图 -->

    <!-- 标题 -->
    <!--<h2 style="margin-top: 20px;" class="index-title">CONFIRM / ORDER</h2>
    <div class="line-container">
        <p class="line"> 确认下单 </p>
    </div>-->
    <div class="index-title" style="width: 1200px" :style='{"padding":"10px","boxShadow":"0 0 0px rgba(255,0,0,.8)","margin":"20px auto","borderColor":"rgba(0,0,0,.3)","color":"#333","borderRadius":"0px","borderWidth":"0","fontSize":"25px","borderStyle":"solid","height":"auto","backgroundColor":"var(--publicMainColor, #808080)"}'>
        <span>CONFIRM / ORDER</span><span>确认下单</span>
    </div>
    <!-- 标题 -->

			<%--<h3 class="common_title">确认收货地址</h3>
			<div class="common_list_con clearfix"/>
				<dl>
                    <dt>寄送到：</dt>
                    <dd v-for="(item,index) in addressList" v-bind:key="index" @click="addressOnChecked(index)">
                        <input type="radio" :value="index" name="address" :checked="item.isdefaultTypes == 2?true:false" />
                        {{item.addressDizhi}}     （{{item.addressName}} 收） {{item.addressPhone}}
                        <hr width=1160 size=1 color="#999999">
                    </dd>
                </dl>
			</div>--%>


            <h3 class="common_title">清单</h3>
            <div class="common_list_con clearfix">
                <ul class="goods_list_th clearfix">
                    <li class="col01">名称</li>
                    <li class="col02">现价/积分</li>
                    <li class="col04">数量</li>
                    <li class="col05">小计</li>
                </ul>
                <ul class="goods_list_td clearfix"  v-for="(item,index) in dataList" v-bind:key="index">
                    <li class="col01">{{index+1}}</li>
                    <li class="col02"><img :src="item.daojuPhoto"></li><!-- style="width: 100px;height: 100px;object-fit: cover;"-->
                    <li class="col03">{{item.daojuName}}</li>
                    <li class="col05">{{item.daojuNewMoney}} RMB</li>
                    <li class="col06">{{item.buyNumber}}</li>
					<li class="col07">{{(item.daojuNewMoney*item.buyNumber).toFixed(2)}} RMB</li>
                </ul>
            </div>

			<h3 class="common_title">结算</h3>
			<div class="common_list_con clearfix">
				<div class="settle_con" v-if="this.daojuOrderPaymentTypes == 1">
					<div class="total_goods_count">共<em>{{dataList.length}}</em>件，总金额:<b>{{totalMoney.toFixed(2)}} RMB</b></div>
					<div class="total_pay">实付款:<b>{{(totalMoney * zhekou).toFixed(2)}} RMB</b></div>
				</div>
				<div class="settle_con" v-if="this.daojuOrderPaymentTypes == 2">
					<div class="total_goods_count">共<em>{{dataList.length}}</em>件，总积分:<b>{{totalMoney.toFixed(2)}} 积分</b></div>
					<!--<div class="total_pay">实付款:<b>{{(totalMoney * zhekou).toFixed(2)}} 积分</b></div>-->
				</div>
			</div>


			<div class="order_submit clearfix" @click="payClick()">
				<a id="order_btn"><i class="layui-icon">&#xe657;</i> 提交订单</a>
			</div>
</div>

		<!-- layui -->
		<script src="../../layui/layui.js"></script>
		<!-- vue -->
		<script src="../../js/vue.js"></script>
        <!-- 引入element组件库 -->
        <script src="../../xznstatic/js/element.min.js"></script>
        <!-- 引入element样式 -->
        <link rel="stylesheet" href="../../xznstatic/css/element.min.css">
		<!-- 组件配置信息 -->
		<script src="../../js/config.js"></script>
		<!-- 扩展插件配置信息 -->
		<script src="../../modules/config.js"></script>
		<!-- 工具方法 -->
		<script src="../../js/utils.js"></script>
		<!-- 校验格式工具类 -->
		<script src="../../js/validate.js"></script>

		<script>
			var vue = new Vue({
				el: '#app',
				data: {
					// 轮播图
					swiperList: [{
						img: '../../img/banner.jpg'
					}],
					dataList: [],
                    daojuOrderPaymentTypes: 1,
                    zhekou:1,
					// 当前用户信息
					user: {}
				},
				computed: {
					totalMoney: function() {
						var total = 0;
						for (var item of this.dataList) {
							total += item.daojuNewMoney * item.buyNumber
						}
						return total;
					}
				},
				methods: {
					jump(url) {
						jump(url)
					}
					// 正常下单，生成订单，减少余额，添加积分，减少库存，修改状态已支付
					,payClick() {
						let data={
                            daojus:localStorage.getItem('daojus'),
                            yonghuId: localStorage.getItem('userid'),
                            daojuOrderPaymentTypes: vue.daojuOrderPaymentTypes,
						}
                        // 获取商品详情信息
                        layui.http.request(`daojuOrder/order`, 'POST', data, (res) => {
                            // 订单编号
                            var orderId = genTradeNo();
							if(res.code == 0){
                                alert("下单成功，点击确定后跳转 我的订单页面");
                                window.location.href='../daojuOrder/list.jsp';
							}
                       		 window.location.href='../daojuOrder/list.jsp';
					    });
					}
            	}
			});

			layui.use(['layer', 'element', 'carousel', 'http', 'jquery', 'form', 'upload'], function() {
				var layer = layui.layer;
				var element = layui.element;
				var carousel = layui.carousel;
				var http = layui.http;
				var jquery = layui.jquery;
				var form = layui.form;
				var upload = layui.upload;

				// 充值
				jquery('#btn-recharge').click(function(e) {
					layer.open({
						type: 2,
						title: '用户充值',
						area: ['900px', '600px'],
						content: '../recharge/recharge.jsp'
					});
				});

				// 获取轮播图 数据
				http.request('config/list', 'get', {
					page: 1,
					limit: 5
				}, function(res) {
					if (res.data.list.length > 0) {
						var swiperItemHtml = '';
						for (let item of res.data.list) {
							if (item.value && item.value != "" && item.value != null) {
								swiperItemHtml +=
									'<div>' +
									'<img class="swiper-item" src="' + item.value + '">' +
									'</div>';
							}
						}
						jquery('#swiper-item').html(swiperItemHtml);
						// 轮播图
						carousel.render({
							elem: '#swiper',
							width: swiper.width,height:swiper.height,
							arrow: swiper.arrow,
							anim: swiper.anim,
							interval: swiper.interval,
							indicator: "none"
						});
					}
				});

                // 获取道具购买的清单列表
				var daojus = localStorage.getItem('daojus');
				// 转换成json类型，localstorage保存的是string数据
				vue.dataList = JSON.parse(daojus);

				// 用户当前用户信息
				let table = localStorage.getItem("userTable");
                if (!table) {
                    layer.msg('请先登录', {
                        time: 2000,
                        icon: 5
                    }, function () {
                        window.parent.location.href = '../login/login.jsp';
                    });
                }
				http.request(`yonghu/session`, 'get', {}, function(data) {
					vue.user = data.data;
					// 用户当前用户折扣信息
					http.request('dictionary/page', 'get', {
						dicCode: "huiyuandengji_types",
						dicName: "会员等级类型",
                        codeIndexStart: vue.user.huiyuandengjiTypes,
                        codeIndexEnd: vue.user.huiyuandengjiTypes,
					}, function(res) {
					    if(res.data.list.length >0){
							vue.zhekou = res.data.list[0].beizhu;
                        }
					})
				});
			});
		</script>
	</body>
</html>

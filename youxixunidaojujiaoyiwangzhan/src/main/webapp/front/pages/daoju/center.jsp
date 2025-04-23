<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="true" %>

<!-- 个人中心 -->
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>个人中心</title>
		<link rel="stylesheet" href="../../layui/css/layui.css">
		<!-- 样式 -->
		<link rel="stylesheet" href="../../css/style.css" />
		<!-- 主题（主要颜色设置） -->
		<link rel="stylesheet" href="../../css/theme.css" />
		<!-- 通用的css -->
		<link rel="stylesheet" href="../../css/common.css" />
	</head>
	<style>
	</style>
	<body style="background: #EEEEEE;">

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
.
			<!-- 标题 -->
			<h2 style="margin-top: 20px;" class="index-title">USER / CENTER</h2>
			<div class="line-container">
				<p class="line" style="background: #EEEEEE;"> 个人中心 </p>
			</div>
			<!-- 标题 -->

			<div class="center-container">
				<!-- 个人中心菜单 config.js-->
<div class="left-container">
    <ul class="layui-nav layui-nav-tree">
        <li v-for="(item,index) in centerMenu" v-bind:key="index" class="layui-nav-item" :class="index==0?'layui-this':''">
            <a :href="'javascript:jump(\''+item.url+'\')'">{{item.name}}</a>
        </li>
    </ul>
</div>				<!-- 个人中心菜单 -->
				<!-- 个人中心 -->
				<div class="right-container">
					<form class="layui-form" lay-filter="myForm">
						<!-- 主键 -->
						<input type="hidden" name="id" id="id" />

						<div class="layui-form-item">
							<label class="layui-form-label">道具名称</label>
							<div class="layui-input-block">
								<input type="text" style="width: 500px" name="daojuName" id="daojuName" lay-verify="required" placeholder="道具名称" autocomplete="off" class="layui-input">
							</div>
						</div>

						<div class="layui-form-item">
							<label class="layui-form-label">道具编号</label>
							<div class="layui-input-block">
								<input type="text" style="width: 500px" name="daojuUuidNumber" id="daojuUuidNumber" lay-verify="required" placeholder="道具编号" autocomplete="off" class="layui-input">
							</div>
						</div>

						<div class="layui-form-item">
							<div class="layui-input-block">
								<img id="daojuPhotoImg"
									 style="width: 100px;height: 100px;border-radius: 50%;border: 2px solid #EEEEEE;"
									 src="../../img/avator.png">
								<input type="hidden" id="daojuPhoto" name="daojuPhoto"/>
							</div>
						</div>

						<div class="layui-form-item">
							<div class="layui-input-block">
								<button type="button" class="layui-btn btn-theme" id="daojuPhotoUpload">
									<i class="layui-icon">&#xe67c;</i>上传图片
								</button>
							</div>
						</div>

						<div class="layui-form-item">
							<label class="layui-form-label">道具类型</label>
							<div  class="layui-input-inline" style="width: 500px">
								<select name="daojuTypes" id="daojuTypes">
                                    <option v-for="item in daojuTypesList" :value="item.codeIndex" :key="item.codeIndex">{{ item.indexName }}</option>
								</select>
							</div>
						</div>

						<div class="layui-form-item">
							<label class="layui-form-label">道具库存</label>
							<div class="layui-input-block">
								<input type="text" style="width: 500px" name="daojuKucunNumber" id="daojuKucunNumber"  lay-verify="required|integer"   placeholder="道具库存" autocomplete="off" class="layui-input">
							</div>
						</div>

						<div class="layui-form-item">
							<label class="layui-form-label">购买获得积分</label>
							<div class="layui-input-block">
								<input type="text" style="width: 500px" name="daojuPrice" id="daojuPrice"  lay-verify="required|integer"   placeholder="购买获得积分" autocomplete="off" class="layui-input">
							</div>
						</div>
			<div class="layui-form-item">
                <div class="layui-block">
                    <label class="layui-form-label">道具原价</label>
                    <div class="layui-input-block">
                        <input type="text" value="0" readonly name="daojuOldMoney" id="daojuOldMoney"  placeholder="道具原价" autocomplete="off" class="layui-input" style="width: 500px">
                        <div class="layui-form-mid layui-word-aux">
                            <i class="layui-icon" style="font-size: 20px;color: red;">&#xe65e;</i>
                            <a id="btn-recharge" href="javascript:void(0)">点我充值</a>
                        </div>
                    </div>
                </div>
            </div>
			<div class="layui-form-item">
                <div class="layui-block">
                    <label class="layui-form-label">现价</label>
                    <div class="layui-input-block">
                        <input type="text" value="0" readonly name="daojuNewMoney" id="daojuNewMoney"  placeholder="现价" autocomplete="off" class="layui-input" style="width: 500px">
                        <div class="layui-form-mid layui-word-aux">
                            <i class="layui-icon" style="font-size: 20px;color: red;">&#xe65e;</i>
                            <a id="btn-recharge" href="javascript:void(0)">点我充值</a>
                        </div>
                    </div>
                </div>
            </div>

						<div class="layui-form-item">
							<label class="layui-form-label">点击次数</label>
							<div class="layui-input-block">
								<input type="text" style="width: 500px" name="daojuClicknum" id="daojuClicknum"  lay-verify="required|integer"   placeholder="点击次数" autocomplete="off" class="layui-input">
							</div>
						</div>

						<div class="layui-form-item">
							<div class="layui-input-block">
								<button class="layui-btn btn-submit btn-theme" lay-submit lay-filter="thisSubmit">更新信息</button>
							</div>
						</div>
						<div class="layui-form-item">
							<div class="layui-input-block">
								<button @click="logout" class="layui-btn btn-submit">退出登录</button>
							</div>
						</div>
					</form>
				</div>
				<!-- 个人中心 -->
			</div>

		</div>

		<!-- layui -->
		<script src="../../layui/layui.js"></script>
		<!-- vue -->
		<script src="../../js/vue.js"></script>
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
					daojuTypesList: [],
					shangxiaTypesList: [],
                    centerMenu: centerMenu
                },
                updated: function () {
                    layui.form.render(null, 'myForm');
                },
                methods: {
                    jump(url) {
                        jump(url)
                    },
                    logout() {
                        localStorage.removeItem('Token');
                        localStorage.removeItem('role');
                        localStorage.removeItem('sessionTable');
                        localStorage.removeItem('adminName');
                        localStorage.removeItem('userid');
                        localStorage.removeItem('userTable');
                        window.parent.location.href = '../login/login.jsp';
                    }
                }
            });

            layui.use(['layer', 'element', 'carousel', 'http', 'jquery', 'form', 'upload', 'laydate'], function () {
                var layer = layui.layer;
                var element = layui.element;
                var carousel = layui.carousel;
                var http = layui.http;
                var jquery = layui.jquery;
                var form = layui.form;
                var upload = layui.upload;
                var laydate = layui.laydate;

                // 充值
                jquery('#btn-recharge').click(function (e) {
                    layer.open({
                        type: 2,
                        title: '用户充值',
                        area: ['900px', '600px'],
                        content: '../recharge/recharge.jsp'
                    });
                });


                // 获取轮播图 数据(有问题把下面的打开 把这个注视掉)
                http.request('config/list', 'get', {
                    page: 1,
                    limit: 5
                }, function(res) {
                    if (res.data.list.length > 0) {
                        let swiperList = [];
                        res.data.list.forEach(element => {
                            if (element.value != null) {
                            swiperList.push({
                                img: element.value
                            });
                        }
                    });
                        vue.swiperList = swiperList;
                        // 轮播图
                        vue.$nextTick(() => {
                            carousel.render({
                            elem: '#swiper',
                            width: '100%',
                            height: '450px',
                            arrow: 'hover',
                            anim: 'default',
                            autoplay: 'true',
                            interval: '3000',
                            indicator: 'inside'
                        });

                    })
                    }
                });

                /*// 获取轮播图 数据
                http.request('config/list', 'get', {
                    page: 1,
                    limit: 5
                }, function(res) {
                    if (res.data.list.length > 0) {
                        var swiperItemHtml = '';
                        for (let item of res.data.list) {
                            if (item.value != "" && item.value != null) {
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
                });*/


                // 查询字典表相关
            		// 道具类型的查询和初始化
           		daojuTypesSelect();
            		// 是否上架的查询和初始化
           		shangxiaTypesSelect();
                // 日期效验规则及格式
                dateTimePick();
                // 表单效验规则
                form.verify({
                    // 正整数效验规则
                    integer: [
                        /^[1-9][0-9]*$/
                        ,'必须是正整数'
                    ]
                    // 小数效验规则
                    ,double: [
                        /^[1-9][0-9]{0,5}(\.[0-9]{1,2})?$/
                        ,'最大整数位为6为,小数最大两位'
                    ]
                });

                // 上传文件
            	// 道具照片的文件上传
                upload.render({
                    //绑定元素
                    elem: '#daojuPhotoUpload',
                    //上传接口
                    url: http.baseurl + 'file/upload',
                    // 请求头
                    headers: {
                        Token: localStorage.getItem('Token')
                    },
                    // 允许上传时校验的文件类型
                    accept: 'images',
                    before: function () {
                        //loading层
                        var index = layer.load(1, {
                            shade: [0.1, '#fff'] //0.1透明度的白色背景
                        });
                    },
                    // 上传成功
                    done: function (res) {
                        console.log(res);
                        layer.closeAll();
                        if (res.code == 0) {
                            layer.msg("上传成功", {
                                time: 2000,
                                icon: 6
                            })
                            var url = http.baseurl + 'upload/' + res.file;
                            jquery('#daojuPhoto').val(url);
                            jquery('#daojuPhotoImg').attr('src', url);
                        } else {
                            layer.msg(res.msg, {
                                time: 2000,
                                icon: 5
                            })
                        }
                    },
                    //请求异常回调
                    error: function () {
                        layer.closeAll();
                        layer.msg("请求接口异常", {
                            time: 2000,
                            icon: 5
                        })
                    }
                });

                // 查询用户信息
                let table = localStorage.getItem("userTable");

                if (!table) {
                    layer.msg('请先登录', {
                        time: 2000,
                        icon: 5
                    }, function () {
                        window.parent.location.href = '../login/login.jsp';
                    });
                }


                http.request(`daoju/session`, 'get', {}, function (data) {
                    // 表单赋值
                    form.val("myForm", data.data);
					jquery("#daojuPhotoImg").attr("src", data.data.daojuPhoto);
                });

                // 提交表单
                form.on('submit(thisSubmit)', function (data) {
                    data = data.field;
                    http.requestJson(table + '/update', 'post', data, function (res) {
                        layer.msg('修改成功', {
                            time: 2000,
                            icon: 6
                        }, function () {
                            window.location.reload();
                        });
                    });
                    return false
                });


				// 日期框初始化
                function dateTimePick(){
                }



				//道具类型的查询
			   function daojuTypesSelect() {
				   //填充下拉框选项
				   http.request("dictionary/page?page=1&limit=100&sort=&order=&dicCode=daoju_types", "GET", {}, (res) => {
					   if(res.code == 0){
						   vue.daojuTypesList = res.data.list;
					   }
				   });
			   }

				//是否上架的查询
			   function shangxiaTypesSelect() {
				   //填充下拉框选项
				   http.request("dictionary/page?page=1&limit=100&sort=&order=&dicCode=shangxia_types", "GET", {}, (res) => {
					   if(res.code == 0){
						   vue.shangxiaTypesList = res.data.list;
					   }
				   });
			   }


            });
		</script>
	</body>
</html>

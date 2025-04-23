
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 道具订单
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/daojuOrder")
public class DaojuOrderController {
    private static final Logger logger = LoggerFactory.getLogger(DaojuOrderController.class);

    @Autowired
    private DaojuOrderService daojuOrderService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private DaojuService daojuService;
    @Autowired
    private YonghuService yonghuService;
@Autowired
private CartService cartService;
@Autowired
private DaojuCommentbackService daojuCommentbackService;



    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = daojuOrderService.queryPage(params);

        //字典表数据转换
        List<DaojuOrderView> list =(List<DaojuOrderView>)page.getList();
        for(DaojuOrderView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        DaojuOrderEntity daojuOrder = daojuOrderService.selectById(id);
        if(daojuOrder !=null){
            //entity转view
            DaojuOrderView view = new DaojuOrderView();
            BeanUtils.copyProperties( daojuOrder , view );//把实体数据重构到view中

                //级联表
                DaojuEntity daoju = daojuService.selectById(daojuOrder.getDaojuId());
                if(daoju != null){
                    BeanUtils.copyProperties( daoju , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setDaojuId(daoju.getId());
                }
                //级联表
                YonghuEntity yonghu = yonghuService.selectById(daojuOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody DaojuOrderEntity daojuOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,daojuOrder:{}",this.getClass().getName(),daojuOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            daojuOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        daojuOrder.setInsertTime(new Date());
        daojuOrder.setCreateTime(new Date());
        daojuOrderService.insert(daojuOrder);
        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody DaojuOrderEntity daojuOrder, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,daojuOrder:{}",this.getClass().getName(),daojuOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            daojuOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<DaojuOrderEntity> queryWrapper = new EntityWrapper<DaojuOrderEntity>()
            .eq("id",0)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        DaojuOrderEntity daojuOrderEntity = daojuOrderService.selectOne(queryWrapper);
        if(daojuOrderEntity==null){
            daojuOrderService.updateById(daojuOrder);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        daojuOrderService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<DaojuOrderEntity> daojuOrderList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("../../upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            DaojuOrderEntity daojuOrderEntity = new DaojuOrderEntity();
//                            daojuOrderEntity.setDaojuOrderUuidNumber(data.get(0));                    //订单号 要改的
//                            daojuOrderEntity.setDaojuId(Integer.valueOf(data.get(0)));   //道具 要改的
//                            daojuOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            daojuOrderEntity.setBuyNumber(Integer.valueOf(data.get(0)));   //购买数量 要改的
//                            daojuOrderEntity.setDaojuOrderTruePrice(data.get(0));                    //实付价格 要改的
//                            daojuOrderEntity.setDaojuOrderCourierName(data.get(0));                    //兑换网址 要改的
//                            daojuOrderEntity.setDaojuOrderCourierNumber(data.get(0));                    //兑换码 要改的
//                            daojuOrderEntity.setDaojuOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            daojuOrderEntity.setDaojuOrderPaymentTypes(Integer.valueOf(data.get(0)));   //支付类型 要改的
//                            daojuOrderEntity.setInsertTime(date);//时间
//                            daojuOrderEntity.setCreateTime(date);//时间
                            daojuOrderList.add(daojuOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //订单号
                                if(seachFields.containsKey("daojuOrderUuidNumber")){
                                    List<String> daojuOrderUuidNumber = seachFields.get("daojuOrderUuidNumber");
                                    daojuOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> daojuOrderUuidNumber = new ArrayList<>();
                                    daojuOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("daojuOrderUuidNumber",daojuOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //订单号
                        List<DaojuOrderEntity> daojuOrderEntities_daojuOrderUuidNumber = daojuOrderService.selectList(new EntityWrapper<DaojuOrderEntity>().in("daoju_order_uuid_number", seachFields.get("daojuOrderUuidNumber")));
                        if(daojuOrderEntities_daojuOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(DaojuOrderEntity s:daojuOrderEntities_daojuOrderUuidNumber){
                                repeatFields.add(s.getDaojuOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [订单号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        daojuOrderService.insertBatch(daojuOrderList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }





    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        // 没有指定排序字段就默认id倒序
        if(StringUtil.isEmpty(String.valueOf(params.get("orderBy")))){
            params.put("orderBy","id");
        }
        PageUtils page = daojuOrderService.queryPage(params);

        //字典表数据转换
        List<DaojuOrderView> list =(List<DaojuOrderView>)page.getList();
        for(DaojuOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        DaojuOrderEntity daojuOrder = daojuOrderService.selectById(id);
            if(daojuOrder !=null){


                //entity转view
                DaojuOrderView view = new DaojuOrderView();
                BeanUtils.copyProperties( daojuOrder , view );//把实体数据重构到view中

                //级联表
                    DaojuEntity daoju = daojuService.selectById(daojuOrder.getDaojuId());
                if(daoju != null){
                    BeanUtils.copyProperties( daoju , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setDaojuId(daoju.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(daojuOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody DaojuOrderEntity daojuOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,daojuOrder:{}",this.getClass().getName(),daojuOrder.toString());
            DaojuEntity daojuEntity = daojuService.selectById(daojuOrder.getDaojuId());
            if(daojuEntity == null){
                return R.error(511,"查不到该道具");
            }
            // Double daojuNewMoney = daojuEntity.getDaojuNewMoney();

            if(false){
            }
            else if((daojuEntity.getDaojuKucunNumber() -daojuOrder.getBuyNumber())<0){
                return R.error(511,"购买数量不能大于库存数量");
            }
            else if(daojuEntity.getDaojuNewMoney() == null){
                return R.error(511,"道具价格不能为空");
            }

            //计算所获得积分
            Double buyJifen =0.0;
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");
            double balance = yonghuEntity.getNewMoney() - daojuEntity.getDaojuNewMoney()*daojuOrder.getBuyNumber();//余额
            buyJifen = new BigDecimal(daojuEntity.getDaojuPrice()).multiply(new BigDecimal(daojuOrder.getBuyNumber())).doubleValue();//所获积分
            if(balance<0)
                return R.error(511,"余额不够支付");
            daojuOrder.setDaojuOrderTypes(1); //设置订单状态为已支付
            daojuOrder.setDaojuOrderTruePrice(daojuEntity.getDaojuNewMoney()*daojuOrder.getBuyNumber()); //设置实付价格
            daojuOrder.setYonghuId(userId); //设置订单支付人id
            daojuOrder.setDaojuOrderUuidNumber(String.valueOf(new Date().getTime()));
            daojuOrder.setDaojuOrderPaymentTypes(1);
            daojuOrder.setInsertTime(new Date());
            daojuOrder.setCreateTime(new Date());
                daojuEntity.setDaojuKucunNumber( daojuEntity.getDaojuKucunNumber() -daojuOrder.getBuyNumber());
                daojuService.updateById(daojuEntity);
                daojuOrderService.insert(daojuOrder);//新增订单
            yonghuEntity.setNewMoney(balance);//设置金额
            yonghuEntity.setYonghuSumJifen(yonghuEntity.getYonghuSumJifen() + buyJifen); //设置总积分
            yonghuEntity.setYonghuNewJifen(yonghuEntity.getYonghuNewJifen() + buyJifen); //设置现积分
                if(yonghuEntity.getYonghuSumJifen()  < 10000)
                    yonghuEntity.setHuiyuandengjiTypes(1);
                else if(yonghuEntity.getYonghuSumJifen()  < 100000)
                    yonghuEntity.setHuiyuandengjiTypes(2);
                else if(yonghuEntity.getYonghuSumJifen()  < 1000000)
                    yonghuEntity.setHuiyuandengjiTypes(3);
            yonghuService.updateById(yonghuEntity);
            return R.ok();
    }
    /**
     * 添加订单
     */
    @RequestMapping("/order")
    public R add(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("order方法:,,Controller:{},,params:{}",this.getClass().getName(),params.toString());
        String daojuOrderUuidNumber = String.valueOf(new Date().getTime());

        //获取当前登录用户的id
        Integer userId = (Integer) request.getSession().getAttribute("userId");

        Integer daojuOrderPaymentTypes = Integer.valueOf(String.valueOf(params.get("daojuOrderPaymentTypes")));//支付类型

        String data = String.valueOf(params.get("daojus"));
        JSONArray jsonArray = JSON.parseArray(data);
        List<Map> daojus = JSON.parseObject(jsonArray.toString(), List.class);

        //获取当前登录用户的个人信息
        YonghuEntity yonghuEntity = yonghuService.selectById(userId);

        //当前订单表
        List<DaojuOrderEntity> daojuOrderList = new ArrayList<>();
        //商品表
        List<DaojuEntity> daojuList = new ArrayList<>();
        //购物车ids
        List<Integer> cartIds = new ArrayList<>();

        BigDecimal zhekou = new BigDecimal(1.0);
        // 获取折扣
        Wrapper<DictionaryEntity> dictionary = new EntityWrapper<DictionaryEntity>()
                .eq("dic_code", "huiyuandengji_types")
                .eq("dic_name", "会员等级类型")
                .eq("code_index", yonghuEntity.getHuiyuandengjiTypes())
                ;
        DictionaryEntity dictionaryEntity = dictionaryService.selectOne(dictionary);
        if(dictionaryEntity != null ){
            zhekou = BigDecimal.valueOf(Double.valueOf(dictionaryEntity.getBeizhu()));
        }

        //循环取出需要的数据
        for (Map<String, Object> map : daojus) {
           //取值
            Integer daojuId = Integer.valueOf(String.valueOf(map.get("daojuId")));//商品id
            Integer buyNumber = Integer.valueOf(String.valueOf(map.get("buyNumber")));//购买数量
            DaojuEntity daojuEntity = daojuService.selectById(daojuId);//购买的商品
            String id = String.valueOf(map.get("id"));
            if(StringUtil.isNotEmpty(id))
                cartIds.add(Integer.valueOf(id));

            //判断商品的库存是否足够
            if(daojuEntity.getDaojuKucunNumber() < buyNumber){
                //商品库存不足直接返回
                return R.error(daojuEntity.getDaojuName()+"的库存不足");
            }else{
                //商品库存充足就减库存
                daojuEntity.setDaojuKucunNumber(daojuEntity.getDaojuKucunNumber() - buyNumber);
            }

            //订单信息表增加数据
            DaojuOrderEntity daojuOrderEntity = new DaojuOrderEntity<>();

            //赋值订单信息
            daojuOrderEntity.setDaojuOrderUuidNumber(daojuOrderUuidNumber);//订单号
            daojuOrderEntity.setDaojuId(daojuId);//道具
            daojuOrderEntity.setYonghuId(userId);//用户
            daojuOrderEntity.setBuyNumber(buyNumber);//购买数量 ？？？？？？
            daojuOrderEntity.setDaojuOrderTypes(1);//订单类型
            daojuOrderEntity.setDaojuOrderPaymentTypes(daojuOrderPaymentTypes);//支付类型
            daojuOrderEntity.setInsertTime(new Date());//订单创建时间
            daojuOrderEntity.setCreateTime(new Date());//创建时间

            //判断是什么支付方式 1代表余额 2代表积分
            if(daojuOrderPaymentTypes == 1){//余额支付
                //计算金额
                Double money = new BigDecimal(daojuEntity.getDaojuNewMoney()).multiply(new BigDecimal(buyNumber)).multiply(zhekou).doubleValue();

                if(yonghuEntity.getNewMoney() - money <0 ){
                    return R.error("余额不足,请充值！！！");
                }else{
                    //计算所获得积分
                    Double buyJifen =0.0;
                        buyJifen = new BigDecimal(daojuEntity.getDaojuPrice()).multiply(new BigDecimal(buyNumber)).doubleValue();
                    yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() - money); //设置金额
                    yonghuEntity.setYonghuSumJifen(yonghuEntity.getYonghuSumJifen() + buyJifen); //设置总积分
                    yonghuEntity.setYonghuNewJifen(yonghuEntity.getYonghuNewJifen() + buyJifen); //设置现积分
                        if(yonghuEntity.getYonghuSumJifen()  < 10000)
                            yonghuEntity.setHuiyuandengjiTypes(1);
                        else if(yonghuEntity.getYonghuSumJifen()  < 100000)
                            yonghuEntity.setHuiyuandengjiTypes(2);
                        else if(yonghuEntity.getYonghuSumJifen()  < 1000000)
                            yonghuEntity.setHuiyuandengjiTypes(3);


                    daojuOrderEntity.setDaojuOrderTruePrice(money);

                }
            }
            daojuOrderList.add(daojuOrderEntity);
            daojuList.add(daojuEntity);

        }
        daojuOrderService.insertBatch(daojuOrderList);
        daojuService.updateBatchById(daojuList);
        yonghuService.updateById(yonghuEntity);
        if(cartIds != null && cartIds.size()>0)
            cartService.deleteBatchIds(cartIds);
        return R.ok();
    }

    /**
    * 退款
    */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request){
        logger.debug("refund方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

            DaojuOrderEntity daojuOrder = daojuOrderService.selectById(id);
            Integer buyNumber = daojuOrder.getBuyNumber();
            Integer daojuOrderPaymentTypes = daojuOrder.getDaojuOrderPaymentTypes();
            Integer daojuId = daojuOrder.getDaojuId();
            if(daojuId == null)
                return R.error(511,"查不到该道具");
            DaojuEntity daojuEntity = daojuService.selectById(daojuId);
            if(daojuEntity == null)
                return R.error(511,"查不到该道具");
            Double daojuNewMoney = daojuEntity.getDaojuNewMoney();
            if(daojuNewMoney == null)
                return R.error(511,"道具价格不能为空");

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");

            Double zhekou = 1.0;
            // 获取折扣
            Wrapper<DictionaryEntity> dictionary = new EntityWrapper<DictionaryEntity>()
                    .eq("dic_code", "huiyuandengji_types")
                    .eq("dic_name", "会员等级类型")
                    .eq("code_index", yonghuEntity.getHuiyuandengjiTypes())
                    ;
            DictionaryEntity dictionaryEntity = dictionaryService.selectOne(dictionary);
            if(dictionaryEntity != null ){
                zhekou = Double.valueOf(dictionaryEntity.getBeizhu());
            }


            //判断是什么支付方式 1代表余额 2代表积分
            if(daojuOrderPaymentTypes == 1){//余额支付
                //计算金额
                Double money = daojuEntity.getDaojuNewMoney() * buyNumber  * zhekou;
                //计算所获得积分
                Double buyJifen = 0.0;
                buyJifen = new BigDecimal(daojuEntity.getDaojuPrice()).multiply(new BigDecimal(buyNumber)).doubleValue();
                yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() + money); //设置金额
                yonghuEntity.setYonghuSumJifen(yonghuEntity.getYonghuSumJifen() - buyJifen); //设置总积分
                if(yonghuEntity.getYonghuNewJifen() - buyJifen <0 )
                    return R.error("积分已经消费,无法退款！！！");
                yonghuEntity.setYonghuNewJifen(yonghuEntity.getYonghuNewJifen() - buyJifen); //设置现积分

                if(yonghuEntity.getYonghuSumJifen()  < 10000)
                    yonghuEntity.setHuiyuandengjiTypes(1);
                else if(yonghuEntity.getYonghuSumJifen()  < 100000)
                    yonghuEntity.setHuiyuandengjiTypes(2);
                else if(yonghuEntity.getYonghuSumJifen()  < 1000000)
                    yonghuEntity.setHuiyuandengjiTypes(3);

            }

            daojuEntity.setDaojuKucunNumber(daojuEntity.getDaojuKucunNumber() + buyNumber);



            daojuOrder.setDaojuOrderTypes(2);//设置订单状态为退款
            daojuOrderService.updateById(daojuOrder);//根据id更新
            yonghuService.updateById(yonghuEntity);//更新用户信息
            daojuService.updateById(daojuEntity);//更新订单中道具的信息
            return R.ok();
    }


    /**
     * 发货
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id ,String daojuOrderCourierNumber, String daojuOrderCourierName){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        DaojuOrderEntity  daojuOrderEntity = new  DaojuOrderEntity();;
        daojuOrderEntity.setId(id);
        daojuOrderEntity.setDaojuOrderTypes(3);
        daojuOrderEntity.setDaojuOrderCourierNumber(daojuOrderCourierNumber);
        daojuOrderEntity.setDaojuOrderCourierName(daojuOrderCourierName);
        boolean b =  daojuOrderService.updateById( daojuOrderEntity);
        if(!b){
            return R.error("发货出错");
        }
        return R.ok();
    }














    /**
     * 收货
     */
    @RequestMapping("/receiving")
    public R receiving(Integer id){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        DaojuOrderEntity  daojuOrderEntity = new  DaojuOrderEntity();
        daojuOrderEntity.setId(id);
        daojuOrderEntity.setDaojuOrderTypes(4);
        boolean b =  daojuOrderService.updateById( daojuOrderEntity);
        if(!b){
            return R.error("收货出错");
        }
        return R.ok();
    }



    /**
    * 评价
    */
    @RequestMapping("/commentback")
    public R commentback(Integer id, String commentbackText, Integer daojuCommentbackPingfenNumber, HttpServletRequest request){
        logger.debug("commentback方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
            DaojuOrderEntity daojuOrder = daojuOrderService.selectById(id);
        if(daojuOrder == null)
            return R.error(511,"查不到该订单");
        if(daojuOrder.getDaojuOrderTypes() != 4)
            return R.error(511,"您不能评价");
        Integer daojuId = daojuOrder.getDaojuId();
        if(daojuId == null)
            return R.error(511,"查不到该道具");

        DaojuCommentbackEntity daojuCommentbackEntity = new DaojuCommentbackEntity();
            daojuCommentbackEntity.setId(id);
            daojuCommentbackEntity.setDaojuId(daojuId);
            daojuCommentbackEntity.setYonghuId((Integer) request.getSession().getAttribute("userId"));
            daojuCommentbackEntity.setDaojuCommentbackText(commentbackText);
            daojuCommentbackEntity.setInsertTime(new Date());
            daojuCommentbackEntity.setReplyText(null);
            daojuCommentbackEntity.setUpdateTime(null);
            daojuCommentbackEntity.setCreateTime(new Date());
            daojuCommentbackService.insert(daojuCommentbackEntity);

            daojuOrder.setDaojuOrderTypes(5);//设置订单状态为已评价
            daojuOrderService.updateById(daojuOrder);//根据id更新
            return R.ok();
    }












}

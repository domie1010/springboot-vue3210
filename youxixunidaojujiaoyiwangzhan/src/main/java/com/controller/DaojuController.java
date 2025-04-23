
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
 * 道具
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/daoju")
public class DaojuController {
    private static final Logger logger = LoggerFactory.getLogger(DaojuController.class);

    @Autowired
    private DaojuService daojuService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service

    @Autowired
    private YonghuService yonghuService;


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
        params.put("daojuDeleteStart",1);params.put("daojuDeleteEnd",1);
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = daojuService.queryPage(params);

        //字典表数据转换
        List<DaojuView> list =(List<DaojuView>)page.getList();
        for(DaojuView c:list){
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
        DaojuEntity daoju = daojuService.selectById(id);
        if(daoju !=null){
            //entity转view
            DaojuView view = new DaojuView();
            BeanUtils.copyProperties( daoju , view );//把实体数据重构到view中

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
    public R save(@RequestBody DaojuEntity daoju, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,daoju:{}",this.getClass().getName(),daoju.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<DaojuEntity> queryWrapper = new EntityWrapper<DaojuEntity>()
            .eq("daoju_name", daoju.getDaojuName())
            .eq("daoju_uuid_number", daoju.getDaojuUuidNumber())
            .eq("daoju_types", daoju.getDaojuTypes())
            .eq("daoju_kucun_number", daoju.getDaojuKucunNumber())
            .eq("daoju_price", daoju.getDaojuPrice())
            .eq("daoju_clicknum", daoju.getDaojuClicknum())
            .eq("shangxia_types", daoju.getShangxiaTypes())
            .eq("daoju_delete", daoju.getDaojuDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        DaojuEntity daojuEntity = daojuService.selectOne(queryWrapper);
        if(daojuEntity==null){
            daoju.setDaojuClicknum(1);
            daoju.setShangxiaTypes(1);
            daoju.setDaojuDelete(1);
            daoju.setCreateTime(new Date());
            daojuService.insert(daoju);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody DaojuEntity daoju, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,daoju:{}",this.getClass().getName(),daoju.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<DaojuEntity> queryWrapper = new EntityWrapper<DaojuEntity>()
            .notIn("id",daoju.getId())
            .andNew()
            .eq("daoju_name", daoju.getDaojuName())
            .eq("daoju_uuid_number", daoju.getDaojuUuidNumber())
            .eq("daoju_types", daoju.getDaojuTypes())
            .eq("daoju_kucun_number", daoju.getDaojuKucunNumber())
            .eq("daoju_price", daoju.getDaojuPrice())
            .eq("daoju_clicknum", daoju.getDaojuClicknum())
            .eq("shangxia_types", daoju.getShangxiaTypes())
            .eq("daoju_delete", daoju.getDaojuDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        DaojuEntity daojuEntity = daojuService.selectOne(queryWrapper);
        if("".equals(daoju.getDaojuPhoto()) || "null".equals(daoju.getDaojuPhoto())){
                daoju.setDaojuPhoto(null);
        }
        if(daojuEntity==null){
            daojuService.updateById(daoju);//根据id更新
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
        ArrayList<DaojuEntity> list = new ArrayList<>();
        for(Integer id:ids){
            DaojuEntity daojuEntity = new DaojuEntity();
            daojuEntity.setId(id);
            daojuEntity.setDaojuDelete(2);
            list.add(daojuEntity);
        }
        if(list != null && list.size() >0){
            daojuService.updateBatchById(list);
        }
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
            List<DaojuEntity> daojuList = new ArrayList<>();//上传的东西
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
                            DaojuEntity daojuEntity = new DaojuEntity();
//                            daojuEntity.setDaojuName(data.get(0));                    //道具名称 要改的
//                            daojuEntity.setDaojuUuidNumber(data.get(0));                    //道具编号 要改的
//                            daojuEntity.setDaojuPhoto("");//详情和图片
//                            daojuEntity.setDaojuTypes(Integer.valueOf(data.get(0)));   //道具类型 要改的
//                            daojuEntity.setDaojuKucunNumber(Integer.valueOf(data.get(0)));   //道具库存 要改的
//                            daojuEntity.setDaojuPrice(Integer.valueOf(data.get(0)));   //购买获得积分 要改的
//                            daojuEntity.setDaojuOldMoney(data.get(0));                    //道具原价 要改的
//                            daojuEntity.setDaojuNewMoney(data.get(0));                    //现价 要改的
//                            daojuEntity.setDaojuClicknum(Integer.valueOf(data.get(0)));   //点击次数 要改的
//                            daojuEntity.setDaojuContent("");//详情和图片
//                            daojuEntity.setShangxiaTypes(Integer.valueOf(data.get(0)));   //是否上架 要改的
//                            daojuEntity.setDaojuDelete(1);//逻辑删除字段
//                            daojuEntity.setCreateTime(date);//时间
                            daojuList.add(daojuEntity);


                            //把要查询是否重复的字段放入map中
                                //道具编号
                                if(seachFields.containsKey("daojuUuidNumber")){
                                    List<String> daojuUuidNumber = seachFields.get("daojuUuidNumber");
                                    daojuUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> daojuUuidNumber = new ArrayList<>();
                                    daojuUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("daojuUuidNumber",daojuUuidNumber);
                                }
                        }

                        //查询是否重复
                         //道具编号
                        List<DaojuEntity> daojuEntities_daojuUuidNumber = daojuService.selectList(new EntityWrapper<DaojuEntity>().in("daoju_uuid_number", seachFields.get("daojuUuidNumber")).eq("daoju_delete", 1));
                        if(daojuEntities_daojuUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(DaojuEntity s:daojuEntities_daojuUuidNumber){
                                repeatFields.add(s.getDaojuUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [道具编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        daojuService.insertBatch(daojuList);
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
        PageUtils page = daojuService.queryPage(params);

        //字典表数据转换
        List<DaojuView> list =(List<DaojuView>)page.getList();
        for(DaojuView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        DaojuEntity daoju = daojuService.selectById(id);
            if(daoju !=null){

                //点击数量加1
                daoju.setDaojuClicknum(daoju.getDaojuClicknum()+1);
                daojuService.updateById(daoju);

                //entity转view
                DaojuView view = new DaojuView();
                BeanUtils.copyProperties( daoju , view );//把实体数据重构到view中

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
    public R add(@RequestBody DaojuEntity daoju, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,daoju:{}",this.getClass().getName(),daoju.toString());
        Wrapper<DaojuEntity> queryWrapper = new EntityWrapper<DaojuEntity>()
            .eq("daoju_name", daoju.getDaojuName())
            .eq("daoju_uuid_number", daoju.getDaojuUuidNumber())
            .eq("daoju_types", daoju.getDaojuTypes())
            .eq("daoju_kucun_number", daoju.getDaojuKucunNumber())
            .eq("daoju_price", daoju.getDaojuPrice())
            .eq("daoju_clicknum", daoju.getDaojuClicknum())
            .eq("shangxia_types", daoju.getShangxiaTypes())
            .eq("daoju_delete", daoju.getDaojuDelete())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        DaojuEntity daojuEntity = daojuService.selectOne(queryWrapper);
        if(daojuEntity==null){
            daoju.setDaojuDelete(1);
            daoju.setCreateTime(new Date());
        daojuService.insert(daoju);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


}

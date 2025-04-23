package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DaojuEntity;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * 道具 服务类
 */
public interface DaojuService extends IService<DaojuEntity> {

    /**
    * @param params 查询参数
    * @return 带分页的查询出来的数据
    */
     PageUtils queryPage(Map<String, Object> params);
}
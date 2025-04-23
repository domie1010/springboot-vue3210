package com.dao;

import com.entity.DaojuOrderEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.DaojuOrderView;

/**
 * 道具订单 Dao 接口
 *
 * @author 
 */
public interface DaojuOrderDao extends BaseMapper<DaojuOrderEntity> {

   List<DaojuOrderView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}

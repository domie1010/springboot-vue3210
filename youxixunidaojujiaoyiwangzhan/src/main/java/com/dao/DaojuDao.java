package com.dao;

import com.entity.DaojuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.DaojuView;

/**
 * 道具 Dao 接口
 *
 * @author 
 */
public interface DaojuDao extends BaseMapper<DaojuEntity> {

   List<DaojuView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}

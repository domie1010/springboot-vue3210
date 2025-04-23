package com.dao;

import com.entity.DaojuCollectionEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.DaojuCollectionView;

/**
 * 道具收藏 Dao 接口
 *
 * @author 
 */
public interface DaojuCollectionDao extends BaseMapper<DaojuCollectionEntity> {

   List<DaojuCollectionView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}

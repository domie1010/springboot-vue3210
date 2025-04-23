package com.dao;

import com.entity.DaojuCommentbackEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.DaojuCommentbackView;

/**
 * 道具评价 Dao 接口
 *
 * @author 
 */
public interface DaojuCommentbackDao extends BaseMapper<DaojuCommentbackEntity> {

   List<DaojuCommentbackView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}

package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entity.ShangJiaEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 用户
 * 
 * @author 
 * @email 
 * @date
 */
public interface ShangJiaDao extends BaseMapper<ShangJiaEntity> {
	
	List<ShangJiaEntity> selectListVO(@Param("ew") Wrapper<ShangJiaEntity> wrapper);

	ShangJiaEntity selectVO(@Param("ew") Wrapper<ShangJiaEntity> wrapper);
	
	List<ShangJiaEntity> selectListView(@Param("ew") Wrapper<ShangJiaEntity> wrapper);

	List<ShangJiaEntity> selectListView(Pagination page,@Param("ew") Wrapper<ShangJiaEntity> wrapper);

	ShangJiaEntity selectView(@Param("ew") Wrapper<ShangJiaEntity> wrapper);
	
}

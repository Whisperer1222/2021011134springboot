package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.entity.ShangJiaEntity;
import com.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 用户
 *
 * @author 
 * @email 
 * @date
 */
public interface ShangJiaService extends IService<ShangJiaEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ShangJiaEntity> selectListVO(Wrapper<ShangJiaEntity> wrapper);

	ShangJiaEntity selectVO(@Param("ew") Wrapper<ShangJiaEntity> wrapper);

   	List<ShangJiaEntity> selectListView(Wrapper<ShangJiaEntity> wrapper);

	ShangJiaEntity selectView(@Param("ew") Wrapper<ShangJiaEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ShangJiaEntity> wrapper);
   	
}


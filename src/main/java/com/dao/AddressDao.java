package com.dao;

import com.entity.AddressEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.AddressVO;
import com.entity.view.AddressView;


/**
 * 地址
 */
public interface AddressDao extends BaseMapper<AddressEntity> {
	//查询地址列表并以值对象形式返回
	List<AddressVO> selectListVO(@Param("ew") Wrapper<AddressEntity> wrapper);
	//根据条件查询单个地址
	AddressVO selectVO(@Param("ew") Wrapper<AddressEntity> wrapper);
	//查询地址列表并以视图形式返回
	List<AddressView> selectListView(@Param("ew") Wrapper<AddressEntity> wrapper);
	//分页查询地址列表并以视图形式返回
	List<AddressView> selectListView(Pagination page,@Param("ew") Wrapper<AddressEntity> wrapper);
	//根据条件查询单个地址视图
	AddressView selectView(@Param("ew") Wrapper<AddressEntity> wrapper);

}

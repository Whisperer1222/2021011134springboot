package com.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.ShangJiaDao;
import com.entity.ShangJiaEntity;
import com.service.ShangJiaService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("shangjiaService")
public class ShangJiaServiceImpl extends ServiceImpl<ShangJiaDao, ShangJiaEntity> implements ShangJiaService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ShangJiaEntity> page = this.selectPage(
                new Query<ShangJiaEntity>(params).getPage(),
                new EntityWrapper<ShangJiaEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ShangJiaEntity> wrapper) {
		  Page<ShangJiaEntity> page =new Query<ShangJiaEntity>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<ShangJiaEntity> selectListVO(Wrapper<ShangJiaEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ShangJiaEntity selectVO(Wrapper<ShangJiaEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ShangJiaEntity> selectListView(Wrapper<ShangJiaEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ShangJiaEntity selectView(Wrapper<ShangJiaEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

}

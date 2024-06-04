package com.entity.view;

import com.entity.ChimaEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 尺码
 * 后端返回视图实体辅助类   
 *
 * @author 
 * @email 
 * @date
 */
@TableName("chima")
public class ChimaView  extends ChimaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public ChimaView(){
	}
 
 	public ChimaView(ChimaEntity chimaEntity){
 	try {
			BeanUtils.copyProperties(this, chimaEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}

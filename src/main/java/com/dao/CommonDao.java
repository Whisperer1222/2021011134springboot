
package com.dao;

import java.util.List;
import java.util.Map;

/**
 * 通用接口
 */
public interface CommonDao{
	List<String> getOption(Map<String, Object> params);
	//根据提供的参数，获取一组选项列表
	Map<String, Object> getFollowByOption(Map<String, Object> params);
	//根据提供的参数，获取选项的详细信息
	List<String> getFollowByOption2(Map<String, Object> params);
	
	void sh(Map<String, Object> params);
	//执行更新操作
	int remindCount(Map<String, Object> params);
	//统计数目
	Map<String, Object> selectCal(Map<String, Object> params);
	//执行聚合计算
	List<Map<String, Object>> selectGroup(Map<String, Object> params);
	//按某列对结果进行分组，并计算每组的统计信息
	List<Map<String, Object>> selectValue(Map<String, Object> params);
	//按某列对结果进行分组，并计算统计值
}

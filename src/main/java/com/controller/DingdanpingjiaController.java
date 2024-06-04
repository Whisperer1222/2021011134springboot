package com.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.dao.DingdanpingjiaDao;
import com.dao.OrdersDao;
import com.entity.OrdersEntity;
import com.entity.ShangpinxinxiEntity;
import com.entity.YonghuEntity;
import com.service.ShangpinxinxiService;
import com.service.YonghuService;
import com.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.DingdanpingjiaEntity;
import com.entity.view.DingdanpingjiaView;

import com.service.DingdanpingjiaService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;


/**
 * 订单评价
 * 后端接口
 * @author 
 * @email 
 * @date
 */
@RestController
@RequestMapping("/dingdanpingjia")
public class DingdanpingjiaController {
    @Autowired
    private DingdanpingjiaService dingdanpingjiaService;
    @Autowired
    private YonghuService yonghuService;
    @Autowired
    OrdersDao ordersDao;
    @Autowired
    ShangpinxinxiService shangpinxinxiService;

    /**
     * 后端列表 处理分页请求，返回订单评价列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DingdanpingjiaEntity dingdanpingjia,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
//		Long userId = (Long) request.getSession().getAttribute("userId");
//		YonghuEntity yonghuEntity = yonghuService.selectById(userId);
//		dingdanpingjia.setYonghuming(yonghuEntity.getYonghuming());

        EntityWrapper<DingdanpingjiaEntity> ew = new EntityWrapper<DingdanpingjiaEntity>();
		PageUtils page = dingdanpingjiaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, dingdanpingjia), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表 返回订单评价列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DingdanpingjiaEntity dingdanpingjia, HttpServletRequest request){
        EntityWrapper<DingdanpingjiaEntity> ew = new EntityWrapper<DingdanpingjiaEntity>();
		PageUtils page = dingdanpingjiaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, dingdanpingjia), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表 返回特定视图的订单评价列表
     */
    @RequestMapping("/lists")
    public R list( DingdanpingjiaEntity dingdanpingjia){
       	EntityWrapper<DingdanpingjiaEntity> ew = new EntityWrapper<DingdanpingjiaEntity>();
      	ew.allEq(MPUtil.allEQMapPre( dingdanpingjia, "dingdanpingjia")); 
        return R.ok().put("data", dingdanpingjiaService.selectListView(ew));
    }

	 /**
     * 查询 根据传入的DingdanpingjiaEntity对象查询订单评价信息
     */
    @RequestMapping("/query")
    public R query(DingdanpingjiaEntity dingdanpingjia){
        EntityWrapper< DingdanpingjiaEntity> ew = new EntityWrapper< DingdanpingjiaEntity>();
 		ew.allEq(MPUtil.allEQMapPre( dingdanpingjia, "dingdanpingjia")); 
		DingdanpingjiaView dingdanpingjiaView =  dingdanpingjiaService.selectView(ew);
		return R.ok("查询订单评价成功").put("data", dingdanpingjiaView);
    }
	
    /**
     * 后端详情 根据ID查询并返回单个订单评价的详细信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DingdanpingjiaEntity dingdanpingjia = dingdanpingjiaService.selectById(id);
        return R.ok().put("data", dingdanpingjia);
    }

    /**
     * 前端详情
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DingdanpingjiaEntity dingdanpingjia = dingdanpingjiaService.selectById(id);
        return R.ok().put("data", dingdanpingjia);
    }
    



    /**
     * 后端保存 保存订单评价信息，并在保存前后进行一些业务逻辑处理，如更新商品的好评率
     */
    @RequestMapping("/save")
    public R save(@RequestBody DingdanpingjiaEntity dingdanpingjia, HttpServletRequest request){
    	dingdanpingjia.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(dingdanpingjia);
        EntityWrapper<OrdersEntity> qw = new EntityWrapper<>();
			//根据订单评价的订单编号dingdanpingjia.getDingdanbianhao()查询对应的OrdersEntity
        qw.eq("orderid",dingdanpingjia.getDingdanbianhao());
        List<OrdersEntity> list = ordersDao.selectList(qw);
        if (list!=null&&list.size()>0){
            OrdersEntity ordersEntity = list.get(0);
            dingdanpingjia.setShangpinmingcheng(ordersEntity.getGoodname());
            dingdanpingjia.setTianjiatupian(ordersEntity.getPicture());
          //计算好评率
					////根据商品的销量xiaoliang、购买数量buynumber和订单评价的评分pingfen来计算新的好评率hpl
            ShangpinxinxiEntity shangpinxinxiEntity = shangpinxinxiService.selectById(ordersEntity.getGoodid());
            double xiaoliang = shangpinxinxiEntity.getXiaoliang();
            int buynumber = ordersEntity.getBuynumber();
            double hpl = shangpinxinxiEntity.getHpl();
            int pingfen = Integer.parseInt(dingdanpingjia.getPingfen());
						//如果评分大于3分，认为是好评，计算好评率时会将这次购买的好评加入到总好评中；否则，会从总好评中减去这次购买的差评
            if (pingfen>3){
                if(xiaoliang>0){
                    hpl= ((xiaoliang - buynumber) * hpl/100 + buynumber)/xiaoliang*100;
                }else {
                    hpl=100;
                }
            }else {
                if (xiaoliang>0){
                    hpl = ((xiaoliang - buynumber) * hpl/100)/xiaoliang*100;
                    if (hpl<0){
                        hpl=0;
                    }
                }else {
                    hpl=0;
                }
            }
            DecimalFormat df = new DecimalFormat("#.00");//使用DecimalFormat格式化好评率到两位小数
            shangpinxinxiEntity.setHpl(Double.parseDouble(df.format(hpl)));
						//更新商品的好评率
            shangpinxinxiService.updateById(shangpinxinxiEntity);
        }
				//将订单评价信息保存到数据库
        dingdanpingjiaService.insert(dingdanpingjia);


        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody DingdanpingjiaEntity dingdanpingjia, HttpServletRequest request){
    	dingdanpingjia.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(dingdanpingjia);
        dingdanpingjiaService.insert(dingdanpingjia);
        return R.ok();
    }

    /**
     * 修改 更新订单评价信息
     */
    @RequestMapping("/update")
    public R update(@RequestBody DingdanpingjiaEntity dingdanpingjia, HttpServletRequest request){
        //ValidatorUtils.validateEntity(dingdanpingjia);
        dingdanpingjiaService.updateById(dingdanpingjia);//全部更新
        return R.ok();
    }
    

    /**
     * 删除 根据传入的ID数组删除订单评价信息
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        dingdanpingjiaService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
    /**
     * 提醒接口 根据不同条件统计订单评价数量
     */
	@RequestMapping("/remind/{columnName}/{type}")
	public R remindCount(@PathVariable("columnName") String columnName, HttpServletRequest request, 
						 @PathVariable("type") String type,@RequestParam Map<String, Object> map) {
		map.put("column", columnName);
		map.put("type", type);
		
		if(type.equals("2")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			Date remindStartDate = null;
			Date remindEndDate = null;
			if(map.get("remindstart")!=null) {
				Integer remindStart = Integer.parseInt(map.get("remindstart").toString());
				c.setTime(new Date()); 
				c.add(Calendar.DAY_OF_MONTH,remindStart);
				remindStartDate = c.getTime();
				map.put("remindstart", sdf.format(remindStartDate));
			}
			if(map.get("remindend")!=null) {
				Integer remindEnd = Integer.parseInt(map.get("remindend").toString());
				c.setTime(new Date());
				c.add(Calendar.DAY_OF_MONTH,remindEnd);
				remindEndDate = c.getTime();
				map.put("remindend", sdf.format(remindEndDate));
			}
		}
		
		Wrapper<DingdanpingjiaEntity> wrapper = new EntityWrapper<DingdanpingjiaEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}

		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			wrapper.eq("yonghuming", (String)request.getSession().getAttribute("username"));
		}

		int count = dingdanpingjiaService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	


}

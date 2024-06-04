package com.controller;

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

import com.entity.CartEntity;
import com.entity.view.CartView;

import com.service.CartService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;


/**
 * 购物车表
 * 后端接口
 * @author 
 * @email 
 * @date
 */
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    /**
     * 后端列表 为后端（如管理员界面）提供分页的购物车列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,CartEntity cart,
		HttpServletRequest request){
    	if(!request.getSession().getAttribute("role").toString().equals("管理员")) {
    		cart.setUserid((Long)request.getSession().getAttribute("userId"));
    	}
        EntityWrapper<CartEntity> ew = new EntityWrapper<CartEntity>();
		PageUtils page = cartService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, cart), params), params));

        return R.ok().put("data", page);
    }//接收请求参数和 CartEntity 对象，根据用户角色设置用户ID，并使用 EntityWrapper 构建查询条件。
		//调用 cartService 的 queryPage 方法执行分页查询并返回结果。
    
    /**
     * 前端列表 为前端提供分页的购物车列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,CartEntity cart, HttpServletRequest request){
        EntityWrapper<CartEntity> ew = new EntityWrapper<CartEntity>();
		PageUtils page = cartService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, cart), params), params));
        return R.ok().put("data", page);
    }//与 page 方法类似，但不涉及角色检查，直接执行分页查询

	/**
     * 列表 获取购物车的列表视图，可能用于前端显示
     */
    @RequestMapping("/lists")
    public R list( CartEntity cart){
       	EntityWrapper<CartEntity> ew = new EntityWrapper<CartEntity>();
      	ew.allEq(MPUtil.allEQMapPre( cart, "cart")); 
        return R.ok().put("data", cartService.selectListView(ew));
    }//接收 CartEntity 对象，使用 EntityWrapper 设置查询条件，并调用 cartService 的 selectListView 方法获取列表数据

	 /**
     * 查询 根据提供的 CartEntity 对象的条件查询单个购物车视图
     */
    @RequestMapping("/query")
    public R query(CartEntity cart){
			//根据传入的CartEntity对象查询购物车信息，并返回查询结果
			//创建一个EntityWrapper对象，这是一个查询条件包装器，用于构建查询条件
        EntityWrapper< CartEntity> ew = new EntityWrapper< CartEntity>();
 		ew.allEq(MPUtil.allEQMapPre( cart, "cart")); 
		CartView cartView =  cartService.selectView(ew);
		return R.ok("查询购物车表成功").put("data", cartView);
    }//使用 EntityWrapper 和 MPUtil 工具类设置查询条件，
	/**调用CartService的selectView方法，传入构建好的查询条件ew。selectView方法负责执行实际的数据库查询操作，
	并返回一个CartView对象。CartView可能是CartEntity的一个扩展或DTO（数据传输对象），用于展示查询结果*/
	
    /**
     * 后端详情	根据ID获取购物车的详细信息，用于后端
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        CartEntity cart = cartService.selectById(id);
        return R.ok().put("data", cart);
    }//接收购物车ID作为路径变量，调用 cartService 的 selectById 方法获取详细信息

    /**
     * 前端详情 根据ID获取购物车的详细信息，用于前端
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        CartEntity cart = cartService.selectById(id);
        return R.ok().put("data", cart);
    }//接收购物车ID作为路径变量，调用 cartService 的 selectById 方法获取详细信息
    



    /**
     * 后端保存 保存新的购物车记录，用于后端
     */
    @RequestMapping("/save")
    public R save(@RequestBody CartEntity cart, HttpServletRequest request){
			//购物车记录的ID通过当前时间戳和随机数生成，保证了唯一性
    	cart.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(cart);
			//将当前会话（session）中的用户ID设置到cart对象的userid字段。这意味着每个购物车项都会关联到一个特定的用户
    	cart.setUserid((Long)request.getSession().getAttribute("userId"));
        cartService.insert(cart);
        return R.ok();
    }//接收 CartEntity 对象，生成唯一ID，并调用 cartService 的 insert 方法保存到数据库
    
    /**
     * 前端保存 保存新的购物车记录，用于前端
     */
    @RequestMapping("/add")
    public R add(@RequestBody CartEntity cart, HttpServletRequest request){
			//购物车记录的ID通过当前时间戳和随机数生成，保证了唯一性
    	cart.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(cart);
        cartService.insert(cart);
        return R.ok();
    }//接收 CartEntity 对象，生成唯一ID，并调用 cartService 的 insert 方法保存到数据库

    /**
     * 修改	更新现有购物车记录
     */
    @RequestMapping("/update")
    public R update(@RequestBody CartEntity cart, HttpServletRequest request){
        //ValidatorUtils.validateEntity(cart);
        cartService.updateById(cart);//全部更新
        return R.ok();
    }//接收更新的 CartEntity 对象，调用 cartService 的 updateById 方法更新数据库记录
    

    /**
     * 删除 根据一组ID删除购物车记录
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        cartService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }//接收ID数组，调用 cartService 的 deleteBatchIds 方法删除对应记录
    
    /**
     * 提醒接口
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
		
		Wrapper<CartEntity> wrapper = new EntityWrapper<CartEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}
		if(!request.getSession().getAttribute("role").toString().equals("管理员")) {
    		wrapper.eq("userid", (Long)request.getSession().getAttribute("userId"));
    	}


		int count = cartService.selectCount(wrapper);
		return R.ok().put("count", count);
	}



}

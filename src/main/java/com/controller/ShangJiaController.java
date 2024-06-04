package com.controller;

import com.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.ShangJiaEntity;
import com.service.TokenService;
import com.service.ShangJiaService;
import com.utils.MPUtil;
import com.utils.PageUtils;
import com.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;


/**
 * 用户
 * 后端接口
 * @author 
 * @email 
 * @date
 */
@RestController
@RequestMapping("/shangjia")
public class ShangJiaController {
    @Autowired
    private ShangJiaService shangJiaService;
    
	@Autowired
	private TokenService tokenService;
	
	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		ShangJiaEntity user = shangJiaService.selectOne(new EntityWrapper<ShangJiaEntity>().eq("yonghuming", username));
		// 判断用户是否存在，以及密码是否匹配
		if(user==null || !user.getMima().equals(password)) {
			return R.error("账号或密码不正确");
		}
		// 调用Token服务，为用户生成Token
		String token = tokenService.generateToken(user.getId(), username,"shangJia",  "用户" );
		// 返回操作成功响应，并携带Token
		return R.ok().put("token", token);
	}
	
	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody ShangJiaEntity shangJia){
    	//ValidatorUtils.validateEntity(shangJia);
    	ShangJiaEntity user = shangJiaService.selectOne(new EntityWrapper<ShangJiaEntity>().eq("yonghuming", shangJia.getYonghuming()));
		if(user!=null) {
			return R.error("注册用户已存在");
		}
		// 为新商家用户生成ID
		Long uId = new Date().getTime();
		shangJia.setId(uId);
		// 设置商家用户的审核状态
		shangJia.setSfsh("未审核，请上传有效证件");
		// 调用商家服务，将新商家用户插入数据库
        shangJiaService.insert(shangJia);
        return R.ok();
    }
	
	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public R logout(HttpServletRequest request) {
		// 使当前会话无效，实现用户退出
		request.getSession().invalidate();
		return R.ok("退出成功");
	}
	
	/**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        ShangJiaEntity user = shangJiaService.selectById(id);
        return R.ok().put("data", user);
    }
    
    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	ShangJiaEntity user = shangJiaService.selectOne(new EntityWrapper<ShangJiaEntity>().eq("yonghuming", username));
    	if(user==null) {
    		return R.error("账号不存在");
    	}
			// 重置密码为默认值"123456"
        user.setMima("123456");
			// 调用商家服务，更新商家用户密码
        shangJiaService.updateById(user);
        return R.ok("密码已重置为：123456");
    }


    /**
     * 后端列表 提供分页数据
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ShangJiaEntity shangJia,
		HttpServletRequest request){
        EntityWrapper<ShangJiaEntity> ew = new EntityWrapper<ShangJiaEntity>();
		PageUtils page = shangJiaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shangJia), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ShangJiaEntity shangJia, HttpServletRequest request){
        EntityWrapper<ShangJiaEntity> ew = new EntityWrapper<ShangJiaEntity>();
		PageUtils page = shangJiaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shangJia), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ShangJiaEntity shangJia){
       	EntityWrapper<ShangJiaEntity> ew = new EntityWrapper<ShangJiaEntity>();
      	ew.allEq(MPUtil.allEQMapPre( shangJia, "shangJia")); 
        return R.ok().put("data", shangJiaService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ShangJiaEntity shangJia){
        EntityWrapper< ShangJiaEntity> ew = new EntityWrapper< ShangJiaEntity>();
 		ew.allEq(MPUtil.allEQMapPre( shangJia, "shangJia"));
		ShangJiaEntity shangJiaView =  shangJiaService.selectView(ew);
		return R.ok("查询用户成功").put("data", shangJiaView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ShangJiaEntity shangJia = shangJiaService.selectById(id);
        return R.ok().put("data", shangJia);
    }

    /**
     * 前端详情
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ShangJiaEntity shangJia = shangJiaService.selectById(id);
        return R.ok().put("data", shangJia);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ShangJiaEntity shangJia, HttpServletRequest request){
    	shangJia.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(shangJia);
    	ShangJiaEntity user = shangJiaService.selectOne(new EntityWrapper<ShangJiaEntity>().eq("yonghuming", shangJia.getYonghuming()));
		if(user!=null) {
			return R.error("用户已存在");
		}
		shangJia.setId(new Date().getTime());
        shangJiaService.insert(shangJia);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ShangJiaEntity shangJia, HttpServletRequest request){
    	shangJia.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(shangJia);
    	ShangJiaEntity user = shangJiaService.selectOne(new EntityWrapper<ShangJiaEntity>().eq("yonghuming", shangJia.getYonghuming()));
		if(user!=null) {
			return R.error("用户已存在");
		}
		shangJia.setId(new Date().getTime());
        shangJiaService.insert(shangJia);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ShangJiaEntity shangJia, HttpServletRequest request){
        //ValidatorUtils.validateEntity(shangJia);
        shangJiaService.updateById(shangJia);//全部更新
        return R.ok();
    }
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        shangJiaService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
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
		
		Wrapper<ShangJiaEntity> wrapper = new EntityWrapper<ShangJiaEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}


		int count = shangJiaService.selectCount(wrapper);
		return R.ok().put("count", count);
	}



}

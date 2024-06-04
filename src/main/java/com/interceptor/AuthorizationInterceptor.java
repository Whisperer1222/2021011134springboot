package com.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.HttpStatus;

import com.annotation.IgnoreAuth;
import com.entity.EIException;
import com.entity.TokenEntity;
import com.service.TokenService;
import com.utils.R;

/**
 * 权限(Token)验证
 */
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    public static final String LOGIN_TOKEN_KEY = "Token";

    @Autowired
    private TokenService tokenService;
    
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		//支持跨域请求 设置了多种HTTP头来支持跨域请求(CORS)，允许不同的HTTP方法和自定义头，以及指定了预检请求(OPTIONS)的处理。
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,request-source,Token, Origin,imgType, Content-Type, cache-control,postman-token,Cookie, Accept,authorization");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
	// 跨域时会首先发送一个OPTIONS请求，这里我们给OPTIONS请求直接返回正常状态
		//当收到一个OPTIONS请求时（这是CORS中的标准预检请求），拦截器直接返回HTTP状态码200（OK），而不会继续执行后续的拦截器或控制器逻辑。
	if (request.getMethod().equals(RequestMethod.OPTIONS.name())) {
        	response.setStatus(HttpStatus.OK.value());
            return false;
        }
        
        IgnoreAuth annotation;
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(IgnoreAuth.class);
        } else {
            return true;
        }

        //从header中获取token 使用TokenService检查Token是否存在且有效
        String token = request.getHeader(LOGIN_TOKEN_KEY);
        
        /**
         * 不需要验证权限的方法直接放过
         */
        if(annotation!=null) {
        	return true;
        }
        
        TokenEntity tokenEntity = null;
        if(StringUtils.isNotBlank(token)) {
        	tokenEntity = tokenService.getTokenEntity(token);
        }
        
        if(tokenEntity != null) {
        	request.getSession().setAttribute("userId", tokenEntity.getUserid());
        	request.getSession().setAttribute("role", tokenEntity.getRole());
        	request.getSession().setAttribute("tableName", tokenEntity.getTablename());
        	request.getSession().setAttribute("username", tokenEntity.getUsername());
        	return true;
        }
        //如果Token验证失败（即用户未登录或Token无效），拦截器将返回JSON格式的错误响应，状态码为401（未授权）。
		// 这里没有使用异常来中断请求，而是通过写入响应体并返回false来告知Spring框架请求不应继续处理。
		PrintWriter writer = null;
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		try {
		    writer = response.getWriter();
		    writer.print(JSONObject.toJSONString(R.error(401, "请先登录")));
		} finally {
		    if(writer != null){
		        writer.close();
		    }
		}
//				throw new EIException("请先登录", 401);
		return false;
    }
}

package com.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.ConfigEntity;
import com.entity.EIException;
import com.service.ConfigService;
import com.utils.R;

/**
 * 上传文件映射表 提供文件上传的接口
 */
@RestController
@RequestMapping("file")
@SuppressWarnings({"unchecked","rawtypes"})
public class FileController{
	@Autowired
    private ConfigService configService;
	/**
	 * 上传文件 用于处理文件上传请求
	 */
	@RequestMapping("/upload")
	public R upload(@RequestParam("file") MultipartFile file,String type) throws Exception {
		if (file.isEmpty()) {
			throw new EIException("上传文件不能为空");
		}
		// 获取上传文件的扩展名
		String fileExt = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
		// 获取类路径下的静态资源目录
		File path = new File(ResourceUtils.getURL("classpath:static").getPath());
		// 如果静态资源目录不存在，则使用当前工作目录
		if(!path.exists()) {
		    path = new File("");
		}
		// 定义文件上传的目标目录
		File upload = new File(path.getAbsolutePath(),"/upload/");
		// 如果目标目录不存在，则创建它
		if(!upload.exists()) {
		    upload.mkdirs();
		}
		// 生成新的文件名，格式为时间戳+扩展名
		String fileName = new Date().getTime()+"."+fileExt;
		// 定义文件上传的目标路径
		File dest = new File(upload.getAbsolutePath()+"/"+fileName);
		// 将上传的文件转移到目标路径
		file.transferTo(dest);
		// 如果文件类型为"1"，则更新配置信息
		if(StringUtils.isNotBlank(type) && type.equals("1")) {
			// 根据配置名称查询配置实体
			ConfigEntity configEntity = configService.selectOne(new EntityWrapper<ConfigEntity>().eq("name", "faceFile"));
			// 如果配置实体不存在，则创建新的配置实体
			if(configEntity==null) {
				configEntity = new ConfigEntity();
				configEntity.setName("faceFile");
				configEntity.setValue(fileName);
			} else {
				// 否则更新现有配置实体的值
				configEntity.setValue(fileName);
			}
			// 调用配置信息服务的方法，插入或更新配置实体
			configService.insertOrUpdate(configEntity);
		}
		// 返回操作成功响应，并携带上传文件的名称
		return R.ok().put("file", fileName);
	}
	
	/**
	 * 下载文件 用于处理文件下载请求
	 */
	@IgnoreAuth
	@RequestMapping("/download")
	public ResponseEntity<byte[]> download(@RequestParam String fileName) {
		try {
			File path = new File(ResourceUtils.getURL("classpath:static").getPath());
			if(!path.exists()) {
			    path = new File("");
			}
			// 定义文件上传的目标目录
			File upload = new File(path.getAbsolutePath(),"/upload/");
			// 如果目标目录不存在，则创建它
			if(!upload.exists()) {
			    upload.mkdirs();
			}
			// 根据文件名构建要下载的文件的完整路径
			File file = new File(upload.getAbsolutePath()+"/"+fileName);
			if(file.exists()){
				/*if(!fileService.canRead(file, SessionManager.getSessionUser())){
					getResponse().sendError(403);
				}*/
				// 创建HTTP响应头
				HttpHeaders headers = new HttpHeaders();
				// 设置响应的内容类型为二进制流
			    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				// 设置响应的内容处置，指定文件名和内容方式为附件
			    headers.setContentDispositionFormData("attachment", fileName);
				// 创建包含文件字节数据、响应头和状态的HTTP响应实体
			    return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 如果发生异常或文件不存在，返回内部服务器错误状态的响应实体
		return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}

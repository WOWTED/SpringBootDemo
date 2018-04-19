package com.xiaour.spring.boot.controller;


import com.xiaour.spring.boot.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;

import com.xiaour.spring.boot.entity.UserInfo;
import com.xiaour.spring.boot.mapper.UserInfoMapper;
import com.xiaour.spring.boot.utils.JsonUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by xiaour on 2017/4/19.
 */
@RestController
@RequestMapping(value="/test")
public class TestCtrl {
	
	@Autowired
	private RedisService redisService;
	
	@Autowired  
    private UserInfoMapper userInfoMapper;  

    @RequestMapping(value="/index")
    public String index(){
        return "hello world";
    }
    
    /**
     * 向redis存储值
     * @param key
     * @param value
     * @return
     * @throws Exception
     */
    @RequestMapping("/set")  
    public String set(String key, String value) throws Exception{

        redisService.set(key, value);
        return "success";  
    }  
    
    /**
     * 获取redis中的值
     * @param key
     * @return
     */
    @RequestMapping("/get")  
    public String get(String key){  
        try {
			return redisService.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";  
    }  
    
    /**
     * 获取数据库中的用户
     * @param id
     * @return
     */
    @RequestMapping("/getUser/{id}")  
    public String get(@PathVariable("id")int id){
        try {
        	UserInfo user= userInfoMapper.selectByPrimaryKey(id);
			return JsonUtil.getJsonString(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";  
    }  
    

}

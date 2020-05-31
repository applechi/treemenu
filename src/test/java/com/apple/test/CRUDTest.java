package com.apple.test;

import com.apple.TestApplication;
import com.apple.entity.Menu;
import com.apple.mapper.MenuMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
* @program: apple_blog
*
* @description: 测试增删改查
*
* @author: Apple
*
* @create: 2019-07-28 11:16
**/
@RunWith(SpringRunner.class)
@SpringBootTest(classes= TestApplication.class)   // 指定启动类
public class CRUDTest {
    private ObjectMapper objectMapper = new ObjectMapper();
    @Resource
    private MenuMapper   menuMapper;
    @Test
    public void testInsert() throws JsonProcessingException {
        List<Menu> menuTreeList = menuMapper.getMenuTreeList(0);
        String jsonStr = objectMapper.writeValueAsString(menuTreeList);
        //for (Menu menu : menuTreeList) {
        //    System.out.println("***Apple Boy***"+menu.toString());
        //}
        System.out.println(jsonStr);
    }
    
}

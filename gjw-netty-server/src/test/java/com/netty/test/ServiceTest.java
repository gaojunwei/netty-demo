package com.netty.test;

import com.alibaba.fastjson.JSON;
import com.gjw.demo.dao.domain.User;
import com.gjw.demo.dao.mapper.UserMapper;
import com.gjw.netty.demo.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: gaojunwei
 * @Date: 2019/4/4 15:47
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ServiceTest {

    @Autowired
    private UserMapper userMapper;
    @Test
    public void test001(){
        User user = userMapper.selectByPrimaryKey(1);
        System.out.println(JSON.toJSONString(user));
    }
}
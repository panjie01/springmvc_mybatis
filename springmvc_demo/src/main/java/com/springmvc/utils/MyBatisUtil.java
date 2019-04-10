package com.springmvc.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.util.Properties;


public class MyBatisUtil { //对应的测试文件在test/java/test/mybatis_test
    private static SqlSessionFactory factory;
    private MyBatisUtil(){}
    static{
        try{
            //添加读取配置文件
            Properties p = new Properties();
            p.load(Resources.getResourceAsStream("jdbc.properties"));
            factory = new SqlSessionFactoryBuilder()
                    .build(Resources.getResourceAsStream("mybatis/mybatis-config.xml"),p);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static SqlSession getSqlSession() {
        return factory.openSession();
    }
}

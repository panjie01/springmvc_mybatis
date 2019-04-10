package test;

import com.springmvc.entity.User;
import com.springmvc.mapper.UserMapper;
import com.springmvc.utils.MyBatisUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class mybatis_test {

    @Test
    public void test01() throws IOException {

        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis/mybatis-config.xml"));
        SqlSession session = factory.openSession();
        User user = new User();
        user.setUsername("小猪");
        user.setPassword("123");
        user.setAge(19);

        session.insert("com.springmvc.mapper.UserMapper.add",user);
        session.commit();
        session.close();
    }
    @Test
    public void test02() throws IOException {

        SqlSession session = MyBatisUtil.getSqlSession();
        User user = new User();
        user.setId(4);
        user.setUsername("xiaozhuzhu");
        user.setPassword("12345");
        user.setAge(20);

        session.update("com.springmvc.mapper.UserMapper.update",user);
        session.commit();
        session.close();
    }

    @Test
    public void test03() throws IOException {

        SqlSession session = MyBatisUtil.getSqlSession();

//        ResultHandler resultHandler = new ObjectWrapperResultHandler() ;
        User user = session.selectOne("com.springmvc.mapper.UserMapper.queryById",4);
        System.out.println(user);
        session.commit();
        session.close();
    }

    @Test
    public void test_mapper_add() throws IOException {
        User user = new User();

        user.setUsername("小猪");
        user.setPassword("12345");
        user.setAge(21);

        SqlSession session = MyBatisUtil.getSqlSession();

        UserMapper mapper = session.getMapper(UserMapper.class);
        mapper.add(user);

        session.commit();
        session.close();
    }

    @Test
    public void test_mapper_query() throws IOException {


        SqlSession session = MyBatisUtil.getSqlSession();

        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = mapper.queryById(7);
        System.out.println(user);

        session.commit();
        session.close();
    }

    @Test
    public void test_queryByKeyword() throws IOException {


        SqlSession session = MyBatisUtil.getSqlSession();

        UserMapper mapper = session.getMapper(UserMapper.class);
        HashMap<String,Object> map = new HashMap<>();
        map.put("keyword","xiao");
        map.put("age",20);
        User user = mapper.queryByKeyword(map); //返回一条数据
        System.out.println(user);

        session.commit();
        session.close();
    }

    @Test
    public void test_queryByAges() throws IOException {


        SqlSession session = MyBatisUtil.getSqlSession();

        UserMapper mapper = session.getMapper(UserMapper.class);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(21);
        list.add(20);
        List<User> userList = mapper.queryByAges(list); //返回一条数据
        System.out.println(userList);

        session.commit();
        session.close();
    }
}

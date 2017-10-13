package cn.mybatis.dao.test;

import cn.mybatis.dao.CustomDao;
import cn.mybatis.entity.EmpEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试dao层的工作
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationcontext.xml"})
public class CustomDaoTest {
    //注入持久化接口对象
    @Autowired
    CustomDao empEntityMapper;

    //###############################--常用查询--###############################
    //增加
    @Test
    public void addEmp() {
       EmpEntity emp = new EmpEntity("xiaomi" ,"1","xiaomi@qq.com");
       empEntityMapper.addEmp(emp);
        System.out.println("--OK--");
    }

    //修改
    @Test
    public void updeteEmp() {
        EmpEntity emp = new EmpEntity(3 ,"xiaohong" ,"0","xiaohong@qq.com");
        empEntityMapper.updeteEmp(emp);
        System.out.println("--OK--");
    }

    //删除
    @Test
    public void deleteEmpId() {
        empEntityMapper.deleteEmpId(4);
        System.out.println("--OK--");
    }

    //增加之后返回主键
    @Test
    public void addEmpReturnId(){
        EmpEntity emp = new EmpEntity("car" ,"1","car@qq.com");
        empEntityMapper.addEmpReturnId(emp);
        System.out.println(emp);
        System.out.println("--OK--");
    }

    //###############################--参数处理--###############################
    //###单个参数：
    //根据empName查询
    @Test
    public void selectBy_empName(){
        EmpEntity emp = empEntityMapper.gitEmpBy_empName("Tom");
        System.out.println(emp);
    }

    // ###多个参数：
    //根据empId和empName查询
    @Test
    public void gitEmpBy_empIdANDempName(){
        EmpEntity emp = empEntityMapper.gitEmpBy_empIdANDempName(1 ,"Tom");
        System.out.println(emp);
    }

    //利用map封装参数进行查询
    @Test
    public void gitEmpByMap(){
        Map<String ,Object> map = new HashMap<>();
        map.put("empId" , 1);
        map.put("empName" , "Tom");
        EmpEntity emp = empEntityMapper.gitEmpByMap(map);
        System.out.println(emp);
    }

    //返回一条记录的map；key就是列名，值就是对应的值
    @Test
    public void getEmpByIdReturnMap(){
        Map<String ,Object> map = empEntityMapper.getEmpByIdReturnMap(1);
        System.out.println(map);
    }

    //多条记录封装一个map：Map<String,Employee>:键是这条记录的主键，值是记录封装后的javaBean
    @Test
    public void getEmpByLastNameLikeReturnMap(){
        Map<String,EmpEntity> map = empEntityMapper.getEmpByLastNameLikeReturnMap("%T%");
        System.out.println(map);

    }
}

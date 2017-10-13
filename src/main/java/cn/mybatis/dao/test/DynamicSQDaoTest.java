package cn.mybatis.dao.test;

import cn.mybatis.dao.DynamicSQLDao;
import cn.mybatis.entity.EmpEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试dao层的工作
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationcontext.xml"})
public class DynamicSQDaoTest {
    //注入持久化接口对象
    @Autowired
    DynamicSQLDao dynamicSQLDao;

    //携带了哪个字段查询条件就带上这个字段的值
    @Test
    public void getEmpsByConditionIf() {
        EmpEntity emp = new EmpEntity();
        emp.setEmpId(1);
        List<EmpEntity> list = dynamicSQLDao.getEmpsByConditionIf(emp);
        for (EmpEntity e : list){
            System.out.println(e);
        }

    }

    @Test
    public void getEmpsByConditionTrim() {
        EmpEntity emp = new EmpEntity();
        emp.setEmpId(1);
        List<EmpEntity> list = dynamicSQLDao.getEmpsByConditionTrim(emp);
        for (EmpEntity e : list){
            System.out.println(e);
        }

    }

    // 如果带了id就用id查，如果带了lastName就用lastName查;只会进入其中一个
    @Test
    public void getEmpsByConditionChoose() {
        EmpEntity emp = new EmpEntity();
        //emp.setEmpId(1);
        List<EmpEntity> list = dynamicSQLDao.getEmpsByConditionChoose(emp);
        for (EmpEntity e : list){
            System.out.println(e);
        }

    }

    //Set标签的使用
    @Test
    public void updateEmp(){
        EmpEntity emp = new EmpEntity();
        emp.setEmpId(1);
        emp.setEmpName("sunnyboy");
        dynamicSQLDao.updateEmp(emp);
    }

    //查询员工id'在给定集合中的
    @Test
    public void getEmpsByConditionForeach(){
       List<Integer> des = new ArrayList<>();
       des.add(1);
        des.add(2);
        des.add(3);
        List<EmpEntity> list = dynamicSQLDao.getEmpsByConditionForeach(des);
        for (EmpEntity e : list){
            System.out.println(e);
        }
    }

    //实行批量保存
    @Test
    public void addEmps(){
        List<EmpEntity> emps = new ArrayList<>();
        emps.add(new EmpEntity("Tom1", "0", "Tom1@qq.com"));
        emps.add(new EmpEntity("Tom2", "1", "Tom2@qq.com"));
        emps.add(new EmpEntity("Tom3", "0", "Tom3@qq.com"));
        emps.add(new EmpEntity("Tom4", "1", "Tom4@qq.com"));
        dynamicSQLDao.addEmps(emps);
    }
}

package cn.mybatis.dao.test;

import cn.mybatis.dao.CacheMechanismDao;
import cn.mybatis.entity.EmpEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试dao层的工作
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationcontext.xml"})
public class CacheMechanismDaoTest {
    //注入持久化接口对象
    @Autowired
    CacheMechanismDao cacheMechanismDao1;
    @Autowired
    CacheMechanismDao cacheMechanismDao2;

    //========>一级缓存:根据empName查询
    @Test
    public void selectBy_empName(){
        EmpEntity emp1 = cacheMechanismDao1.gitEmpBy_empName("sunnyboy");
        System.out.println(emp1);
        //###############

        EmpEntity emp2 = cacheMechanismDao2.gitEmpBy_empName("sunnyboy");
        System.out.println(emp2);
    }

    //========>第三方缓存:根据empName查询
    @Test
    public void gitEmpBy_empName3(){
        EmpEntity emp1 = cacheMechanismDao1.gitEmpBy_empName("sunnyboy");
        System.out.println(emp1);
        //###############
        System.out.println("====================================================================");
        EmpEntity emp2 = cacheMechanismDao2.gitEmpBy_empName("sunnyboy");
        System.out.println(emp2);
    }


}

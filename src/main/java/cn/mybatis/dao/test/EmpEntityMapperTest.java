package cn.mybatis.dao.test;
import org.apache.ibatis.io.Resources;
import cn.mybatis.dao.EmpEntityMapper;
import cn.mybatis.entity.EmpEntity;
import cn.mybatis.entity.EmpEntityExample;
import cn.mybatis.entity.EmpEntityExample.Criteria;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.io.IOException;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.InputStream;
import java.util.List;

/**
 * 测试dao层的工作
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationcontext.xml"})
public class EmpEntityMapperTest {
    //注入持久化接口对象
    @Autowired
    EmpEntityMapper empEntityMapper;

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "batch-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 实现多条件查询
     */
    @Test
    public void testMyBatis3() {
        //创建一个Criteria，这个Criteria就是拼装查询条件
        EmpEntityExample example = new EmpEntityExample();
        //创建一个Criteria，这个Criteria就是拼装查询条件
        Criteria criteria1 = example.createCriteria();
        Criteria criteria2 = example.createCriteria();
        criteria1.andEmpNameLike("%Tom2%");
        criteria2.andEmpEmailLike("%Tom2%");
        //添加关联
        example.or(criteria2);
        List<EmpEntity> list = empEntityMapper.selectByExample(example);
        for (EmpEntity employee : list) {
            System.out.println(employee);
        }
    }

    /**
     * 实现多条件分页查询
     */
    @Test
    public void testEmps() {
        Page<Object> page = PageHelper.startPage(1, 1);
        List<EmpEntity> list = empEntityMapper.selectByExample(null);
        PageInfo<EmpEntity> info = new PageInfo<>(list);
        System.out.println("当前页码："+info.getPageNum());
        System.out.println("总记录数："+info.getTotal());
        System.out.println("每页的记录数："+info.getPageSize());
        System.out.println("总页码："+info.getPages());
        System.out.println("是否第一页："+info.isIsFirstPage());
        System.out.println("连续显示的页码：");
        for (EmpEntity employee : info.getList()) {
            System.out.println(employee);
        }
    }


}

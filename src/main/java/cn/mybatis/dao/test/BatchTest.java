package cn.mybatis.dao.test;

import cn.mybatis.dao.EmpEntityMapper;
import cn.mybatis.entity.EmpEntity;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class BatchTest {

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "batch-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 批量操作
     */
    @Test
    public  void testBatch() throws IOException {
        // 1、获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        // 2、获取sqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        try{
            EmpEntityMapper mapper = openSession.getMapper(EmpEntityMapper.class);
            for (int i = 0; i < 100; i++) {
                mapper.addEmp(new EmpEntity("Tom"+i ,"1","Tom"+i+"@qq.com"));
            }
            openSession.commit();
        }finally{

            openSession.close();
        }
    }
}

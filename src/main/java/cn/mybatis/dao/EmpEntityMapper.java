package cn.mybatis.dao;

import cn.mybatis.entity.EmpEntity;
import cn.mybatis.entity.EmpEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 自动封装
 */
public interface EmpEntityMapper {
    long countByExample(EmpEntityExample example);

    int deleteByExample(EmpEntityExample example);

    int deleteByPrimaryKey(Integer empId);

    int insert(EmpEntity record);

    int insertSelective(EmpEntity record);

    List<EmpEntity> selectByExample(EmpEntityExample example);

    EmpEntity selectByPrimaryKey(Integer empId);

    int updateByExampleSelective(@Param("record") EmpEntity record, @Param("example") EmpEntityExample example);

    int updateByExample(@Param("record") EmpEntity record, @Param("example") EmpEntityExample example);

    int updateByPrimaryKeySelective(EmpEntity record);

    int updateByPrimaryKey(EmpEntity record);

    public void addEmp(EmpEntity empEntity);
}
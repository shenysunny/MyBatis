package cn.mybatis.dao;

import cn.mybatis.entity.EmpEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 动态SQL
 *
 */
public interface DynamicSQLDao {
    //携带了哪个字段查询条件就带上这个字段的值
    public List<EmpEntity> getEmpsByConditionIf(EmpEntity empEntity);
    public List<EmpEntity> getEmpsByConditionTrim(EmpEntity empEntity);

    // 如果带了id就用id查，如果带了lastName就用lastName查;只会进入其中一个
    public List<EmpEntity> getEmpsByConditionChoose(EmpEntity empEntity);

    //Set标签的使用
    public void updateEmp(EmpEntity empEntity);

    //查询员工id'在给定集合中的
    public List<EmpEntity> getEmpsByConditionForeach(@Param("ids")List<Integer> ids);

    //实行批量保存
    public void addEmps(@Param("emps")List<EmpEntity> emps);
}
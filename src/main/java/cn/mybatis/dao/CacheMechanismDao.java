package cn.mybatis.dao;

import cn.mybatis.entity.EmpEntity;

/**
 * 缓存机制
 */
public interface CacheMechanismDao {
  //========>一级缓存:根据empName查询
    public EmpEntity gitEmpBy_empName(String empName);

    //========>二级缓存:根据empName查询
    public EmpEntity gitEmpBy_empName2(String empName);

  //========>第三方缓存:根据empName查询
  public EmpEntity gitEmpBy_empName3(String empName);

}
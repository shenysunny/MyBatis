package cn.mybatis.dao;

import cn.mybatis.entity.EmpEntity;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 参数处理
 */
public interface CustomDao {
    //###############################--常用查询--###############################
    //增加
    public void addEmp(EmpEntity empEntity);

    //修改
    public void updeteEmp(EmpEntity empEntity);

    //删除
    public void deleteEmpId(Integer empId);

    //增加之后返回主键
    public void addEmpReturnId(EmpEntity empEntity);

    //###############################--参数处理--###############################
    //###单个参数：
    //根据empName查询
    public EmpEntity gitEmpBy_empName(String empName);

   // ###多个参数：
   //根据empId和empName查询
   public EmpEntity gitEmpBy_empIdANDempName(@Param("empId") Integer empId , @Param("empName")String empName);

    //利用map封装参数进行查询
    public EmpEntity gitEmpByMap(Map<String ,Object> map);

    //返回一条记录的map；key就是列名，值就是对应的值
    public Map<String, Object> getEmpByIdReturnMap(Integer id);

    //多条记录封装一个map：Map<String,Employee>:键是这条记录的主键，值是记录封装后的javaBean
    //@MapKey:告诉mybatis封装这个map的时候使用哪个属性作为map的key
    @MapKey("empName")
    public Map<String, EmpEntity> getEmpByLastNameLikeReturnMap(String empName);
}


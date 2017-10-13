package cn.mybatis.entity;

import java.io.Serializable;

public class EmpEntity  implements Serializable{
    private Integer empId;

    private String empName;

    private String empGender;

    private String empEmail;

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    public String getEmpGender() {
        return empGender;
    }

    public void setEmpGender(String empGender) {
        this.empGender = empGender == null ? null : empGender.trim();
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail == null ? null : empEmail.trim();
    }

    public EmpEntity(Integer empId, String empName, String empGender, String empEmail) {
        this.empId = empId;
        this.empName = empName;
        this.empGender = empGender;
        this.empEmail = empEmail;
    }

    public EmpEntity(String empName, String empGender, String empEmail) {
        this.empName = empName;
        this.empGender = empGender;
        this.empEmail = empEmail;
    }

    public EmpEntity() {
    }

    @Override
    public String toString() {
        return "EmpEntity{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", empGender='" + empGender + '\'' +
                ", empEmail='" + empEmail + '\'' +
                '}';
    }
}
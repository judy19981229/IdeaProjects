package entity;

public class dept {
    Integer deptNo;
    String deptName;
    String deptLoc;

    public dept(Integer deptNo, String deptName, String deptLoc) {
        this.deptNo = deptNo;
        this.deptName = deptName;
        this.deptLoc = deptLoc;
    }

    public dept() {
    }

    public Integer getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(Integer deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptLoc() {
        return deptLoc;
    }

    public void setDeptLoc(String deptLoc) {
        this.deptLoc = deptLoc;
    }
}

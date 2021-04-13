package workbench.entity;

public class studentsTestResultMap {
    Integer stuId;
    String stuName;
    String stuEmail;
    Integer stuAge;

    public studentsTestResultMap(Integer stuId, String stuName, String stuEmail, Integer stuAge) {
        this.stuId = stuId;
        this.stuName = stuName;
        this.stuEmail = stuEmail;
        this.stuAge = stuAge;
    }

    public studentsTestResultMap() {
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuEmail() {
        return stuEmail;
    }

    public void setStuEmail(String stuEmail) {
        this.stuEmail = stuEmail;
    }

    public Integer getStuAge() {
        return stuAge;
    }

    public void setStuAge(Integer stuAge) {
        this.stuAge = stuAge;
    }

    @Override
    public String toString() {
        return "studentsTestResultMap{" +
                "stuId=" + stuId +
                ", stuName='" + stuName + '\'' +
                ", stuEmail='" + stuEmail + '\'' +
                ", stuAge=" + stuAge +
                '}';
    }
}


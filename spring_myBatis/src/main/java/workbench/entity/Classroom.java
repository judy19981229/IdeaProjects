package workbench.entity;

public class Classroom {
    private String classId;
    private String className;

    public Classroom(String classId, String className) {
        this.classId = classId;
        this.className = className;
    }

    public Classroom() {
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "classId='" + classId + '\'' +
                ", className='" + className + '\'' +
                '}';
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}

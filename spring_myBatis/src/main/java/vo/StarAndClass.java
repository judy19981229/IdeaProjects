package vo;

public class StarAndClass {
    private String id;
    private String name;
    private Integer age;
    private String classId;
    private String className;

    public StarAndClass(String id, String name, Integer age, String classId, String className) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.classId = classId;
        this.className = className;
    }

    @Override
    public String toString() {
        return "StarAndClass{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", classId='" + classId + '\'' +
                ", className='" + className + '\'' +
                '}';
    }

    public StarAndClass() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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
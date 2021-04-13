package workbench.dao;

import workbench.entity.Student;

import java.util.List;

public interface StudentDao {
    public int insertStudent(Student student);
    public List<Student> selectStudent();
    public int countStudent(String name);
}

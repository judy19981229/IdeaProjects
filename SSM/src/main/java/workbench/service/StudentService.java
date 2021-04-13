package workbench.service;

import workbench.entity.Student;

import java.util.List;

public interface StudentService {
    public int insertStudent(Student student);
    public List<Student> selectStudent();
    public int countStudent(String name);
}

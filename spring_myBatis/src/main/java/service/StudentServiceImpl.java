package service;

import dao.StudentDao;
import entity.Student;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
@Component
public class StudentServiceImpl implements StudentService{
    @Resource(name="studentDao")
    private StudentDao studentDao;

    //使用set注入来给StudentDao赋值
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public int insertStudent(Student student) {
        int num= studentDao.insertStudent(student);
        return num;
    }
    @Override
    public List<Student> selectStudent() {
        List<Student> studentList=studentDao.selectStudents();
        return studentList;
    }
}

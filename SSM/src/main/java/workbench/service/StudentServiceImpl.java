package workbench.service;

import workbench.dao.StudentDao;
import workbench.entity.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{
    @Resource(name="studentDao")
    StudentDao studentDao;

    @Override
    public int insertStudent(Student student) {
        int result=studentDao.insertStudent(student);
        return result;
    }

    @Override
    public List<Student> selectStudent() {
        List<Student> list=studentDao.selectStudent();
        return list;
    }

    @Override
    public int countStudent(String name) {
        return studentDao.countStudent(name);
    }

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public StudentServiceImpl() {
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

}

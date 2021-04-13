package workbench.dao;

import workbench.entity.students;
import workbench.entity.studentsTestResultMap;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface studentsDao {
    List<students> findStudent();
    int insertStudent(students student);
    int deleteStudent(String id);
    int updateStudent1(@Param("myId") Integer id,
                             @Param("myName") String name,
                             @Param("myEmail") String email,
                             @Param("myAge") Integer age
                             );
    int updateStudent2(students student);
    int countStudent();
    Map<Object,Object> selectMap(String id);
    List<studentsTestResultMap> findStudent2(String age);
    List<students> findStudent3(students student);
    List<students> findStudent4(List list);
}

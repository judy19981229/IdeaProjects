import com.github.pagehelper.PageHelper;
import workbench.dao.studentsDao;
import workbench.entity.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import util.myBatisUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class testMybatis {
    //访问myBatis读取student数据
    //1.定义myBatis主配置文件的名称，从类路径的根开始（target/class开始）
    String config="myBatis.xml";
    //2.读取这个config文件
    InputStream in;
    {
        try {
            in = Resources.getResourceAsStream(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //3.创建SqlSessionFactoryBuilder对象
    SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
    //4.创建SqlSessionFactory对象
    SqlSessionFactory factory= builder.build(in);
    @Test
    public void testInsert() {
        //5.【重要】获取SqlSession对象，从SqlSessionFactory中获取SqlSession
        SqlSession sqlSession= factory.openSession();
        //6.【重要】指定执行的sql语句的标识。 sql映射文件中的namespace+"."+标签的id值
        String sqlId="workbench.dao.studentsDao"+"."+"insertStudents";
        //7.执行sql语句，通过sqlId找到语句
        students student =new students(1004,"赵六","zhaoliu@sina.com",16);
        int result=sqlSession.insert(sqlId,student);
        System.out.println("result="+result);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void testDelete(){
        //5.【重要】获取SqlSession对象，从SqlSessionFactory中获取SqlSession
        SqlSession sqlSession= factory.openSession();
        //6.【重要】指定执行的sql语句的标识。 sql映射文件中的namespace+"."+标签的id值
        String sqlId="workbench.dao.studentsDao"+"."+"deleteStudents";
        //7.执行sql语句，通过sqlId找到语句
        int result=sqlSession.delete(sqlId,1004+"");
        System.out.println("result="+result);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void testSelect(){
        //5.【重要】获取SqlSession对象，从SqlSessionFactory中获取SqlSession
        SqlSession sqlSession= factory.openSession();
        //6.【重要】指定执行的sql语句的标识。 sql映射文件中的namespace+"."+标签的id值
        String sqlId="workbench.dao.studentsDao"+"."+"findStudents";
        //7.执行sql语句，通过sqlId找到语句
        List<students> list=sqlSession.selectList(sqlId);
        for(students stu:list){
            System.out.println(stu.toString());
        }
        sqlSession.close();
    }
    @Test
    public void testSelect3(){
        //使用动态代理 SqlSession.getMapper(dao接口),getMapper能获得dao接口对应的实现类对象
        SqlSession sqlSession= myBatisUtil.getSqlSession();
        studentsDao dao=sqlSession.getMapper(studentsDao.class);
        List<students> list=dao.findStudent();
        for(students student:list){
            System.out.println(student);
        }
        sqlSession.close();
    }

    @Test
    public void testUpdate1(){
        SqlSession sqlSession=myBatisUtil.getSqlSession();
        studentsDao dao=sqlSession.getMapper(studentsDao.class);
        int result=dao.updateStudent1(1004,"mike","mike@163.com",20);
        if(result==1){
            System.out.println("更新成功");
        }
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void testUpdate2(){
        SqlSession sqlSession=myBatisUtil.getSqlSession();
        studentsDao dao=sqlSession.getMapper(studentsDao.class);
        students student=new students(1004,"mike","mike@163.com",20);
        int result=dao.updateStudent2(student);
        if(result==1){
            System.out.println("更新成功");
        }
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void countStudent(){
        SqlSession sqlSession=myBatisUtil.getSqlSession();
        studentsDao dao=sqlSession.getMapper(studentsDao.class);
        int result=dao.countStudent();
        System.out.println("result="+result);
    }
    @Test
    public void selectMap(){
        SqlSession sqlSession=myBatisUtil.getSqlSession();
        studentsDao dao=sqlSession.getMapper(studentsDao.class);
        Map<Object,Object> map=dao.selectMap(1004+"");
        System.out.println("map="+map);
    }
    @Test
    public void selectResultMap(){
        SqlSession sqlSession=myBatisUtil.getSqlSession();
        studentsDao dao=sqlSession.getMapper(studentsDao.class);
        List<studentsTestResultMap> list=dao.findStudent2("2%");
        for(studentsTestResultMap student:list){
            System.out.println(student.toString());
        }
    }
    @Test
    public void selectStudentByWhere(){
        SqlSession sqlSession=myBatisUtil.getSqlSession();
        studentsDao dao=sqlSession.getMapper(studentsDao.class);
        students student=new students();
        student.setName("mike");
        student.setAge(20);
        List<students> list=dao.findStudent3(student);
        for(students stu:list){
            System.out.println(stu.toString());
        }
    }
    @Test
    public void selectStudentByList(){
        SqlSession sqlSession=myBatisUtil.getSqlSession();
        studentsDao dao=sqlSession.getMapper(studentsDao.class);
        List idList=new ArrayList();
        idList.add(1001);
        idList.add(1002);
        idList.add(1003);
        idList.add(1004);
        PageHelper.startPage(1,3);
        List<students> stuList=dao.findStudent4(idList);
        for(students stu:stuList){
            System.out.println(stu.toString());
        }
    }

}

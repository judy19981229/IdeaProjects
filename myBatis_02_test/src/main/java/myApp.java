import entity.students;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import util.myBatisUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class myApp {
    public static void main(String[] args) {
        //获取SqlSession对象，利用myBatisUtil封装类中的getSession()方法
        SqlSession sqlSession= myBatisUtil.getSqlSession();
        //指定执行的sql语句的标识。 sql映射文件中的namespace+"."+标签的id值
        String sqlId="dao.studentsDao"+"."+"findStudents";
        //执行sql语句，通过sqlId找到语句
        List<students> list=sqlSession.selectList(sqlId);
        //输出结果
        for(students stu:list){
            System.out.println(stu.toString());
        }
        //关闭sqlSession对象
        sqlSession.close();
    }
}

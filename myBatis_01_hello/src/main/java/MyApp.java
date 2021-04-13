import workbench.entity.students;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyApp {
    public static void main(String[] args) throws IOException {
        //访问myBatis读取student数据
        //1.定义myBatis主配置文件的名称，从类路径的根开始（target/class开始）
        String config="myBatis.xml";
        //2.读取这个config文件
        InputStream in=Resources.getResourceAsStream(config);
        //3.创建SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        //4.创建SqlSessionFactory对象
        SqlSessionFactory factory= builder.build(in);
        //5.【重要】获取SqlSession对象，从SqlSessionFactory中获取SqlSession
        SqlSession sqlSession= factory.openSession();
        //6.【重要】指定执行的sql语句的标识。 sql映射文件中的namespace+"."+标签的id值
        String sqlId="workbench.settings.dao.studentsDao"+"."+"findStudents";
        //7.执行sql语句，通过sqlId找到语句
        List<students> list=sqlSession.selectList(sqlId);
        for(students stu:list){
            System.out.println(stu.toString());
        }
        sqlSession.close();
    }
}
